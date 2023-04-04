package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.scoregoatvaadin.domain.*;
import com.vaadin.scoregoatvaadin.view.manager.ElementManager;
import com.vaadin.scoregoatvaadin.view.manager.UserManager;
import java.util.Objects;

public class LogInView extends VerticalLayout {
    private final TextField name = new TextField(Names.NAME_TEXT_FIELD.getValue());
    private final PasswordField password = new PasswordField(Names.PASSWORD.getValue());
    private final Button logIn = new Button(Names.LOG_IN.getValue());
    private final Button cancel = new Button(Names.CANCEL.getValue());
    private final Button signUp = new Button(Names.SIGN_UP.getValue());
    private final Label info = new Label();
    private final ElementManager elements = new ElementManager();
    private final UserManager userManager = new UserManager();
    private final MainView mainView;
    private final ToolBarView toolBarView;

    public LogInView(MainView mainView) {
        this.mainView = mainView;
        this.toolBarView = mainView.getToolBarView();
        setSizeUndefined();
        getStyle().set("background", TeamValues.MAIN_COLOR.getValues());
        setHeight("350em");
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
                    elements.setInfoErrorLabel(info);
                }
            } else {
                info.setText("Error occur. Couldn't logIn user!");
            }
        }
    }

    private VerticalLayout setElements() {
        VerticalLayout vl = new VerticalLayout();
        elements.setTextElements(name);
        elements.setPasswordElement(password);
        elements.setSideMenuButtonAccept(logIn);
        elements.setSideMenuButtonClose(cancel);
        elements.setSideMenuButtonAccept(signUp);
        elements.setInfoErrorLabel(info);
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
        vl.getStyle().set("background", TeamValues.WHITE.getValues());
        return vl;
    }

    private void logInExecution(UserRespondDto respond) {
        mainView.setUser(userManager.setUser(respond));
        mainView.remove(this);
        toolBarView.getUserLabel().setText(respond.getUserName());
        toolBarView.getLogButton().setText(Names.LOG_OUT.getValue());
        toolBarView.getYourAccount().setEnabled(true);
        info.setText(respond.getRespond());
        elements.setGeneralButtonWarningColor(toolBarView.getLogButton());
        elements.setGeneralButton(mainView.getToolBarView().getYourAccount());
        elements.setInfoLabel(info);
    }

    public void logInExecute() {
        mainView.add(setLogInView());
    }

    public LogInView setLogInView() {
        return mainView.getLoginView();
    }
}
