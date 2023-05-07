package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.scoregoatvaadin.domain.*;
import com.vaadin.scoregoatvaadin.view.manager.UserManager;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamLogInView;
import java.util.Objects;

public class LogInView extends VerticalLayout {
    private final TextField name = new TextField(Names.NAME_TEXT_FIELD.getValue());
    private final PasswordField password = new PasswordField(Names.PASSWORD.getValue());
    private final Button logIn = new Button(Names.LOG_IN.getValue());
    private final Button cancel = new Button(Names.CANCEL.getValue());
    private final Button signUp = new Button(Names.SIGN_UP.getValue());
    private final Label info = new Label();
    private final TeamLogInView team = new TeamLogInView();
    private final UserManager userManager = new UserManager();
    private final MainView mainView;
    private final ToolBarView toolBarView;

    public LogInView(MainView mainView) {
        this.mainView = mainView;
        this.toolBarView = mainView.getToolBarView();
        setSizeUndefined();
        team.setMainLayout(this);

        add(
                setElements()
        );

        logIn.addClickListener(event -> {
            logIn();
        });
        cancel.addClickListener(event -> mainView.remove(this));
        signUp.addClickListener(event -> {
            mainView.remove(this);
            mainView.add(mainView.setSignUpView());
        });
    }

    private void logIn(){
        if (!Objects.equals(name.getValue(), "") && !Objects.equals(password.getValue(), "")) {
            UserRespondDto respond = mainView.getFacade().logIn(new UserParamDto(name.getValue(), password.getValue()));
            if (respond != null) {
                if (respond.isLogIn()) {
                    logInExecution(respond);
                } else {
                    info.setText(respond.getRespond());
                    team.setInfoLabelError(info);
                }
            } else {
                info.setText(Messages.LOG_IN_VIEW_LOG_IN_ERROR.getMessage());
            }
        }
    }

    private VerticalLayout setElements() {
        VerticalLayout vl = new VerticalLayout();
        team.setText(name);
        team.setPassword(password);
        team.setAcceptBtn(logIn);
        team.setCloseBtn(cancel);
        team.setAcceptBtn(signUp);
        team.setInfoLabelError(info);
        logIn.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        vl.add(
                name,
                password,
                logIn,
                cancel,
                signUp,
                info
        );
        team.setSecondaryLayout(vl);
        return vl;
    }

    private void logInExecution(UserRespondDto respond) {
        mainView.setUser(userManager.setUser(respond));
        mainView.remove(this);
        mainView.getDoubleLayout().removeAll();
        toolBarView.getUserLabel().setText(respond.getUserName());
        toolBarView.getLogButton().setText(Names.LOG_OUT.getValue());
        toolBarView.getYourAccount().setEnabled(true);
        info.setText(respond.getRespond());
        team.setGeneralBtnWarningColor(toolBarView.getLogButton());
        team.setGeneralBtn(mainView.getToolBarView().getYourAccount());
        team.setInfoLabel(info);
    }

    public void logInExecute() {
        mainView.add(setLogInView());
    }

    public LogInView setLogInView() {

        return mainView.getLoginView();
    }
}
