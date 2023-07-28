package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.scoregoatvaadin.domain.*;
import com.vaadin.scoregoatvaadin.service.NotificationService;
import com.vaadin.scoregoatvaadin.view.manager.UserManager;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamLogInView;
import java.util.Objects;

public class LogInView extends VerticalLayout {
    private final TextField name = new TextField(Names.NAME_TEXT_FIELD.getValue());
    private final PasswordField password = new PasswordField(Names.PASSWORD.getValue());
    private final Button logIn = new Button(Names.LOG_IN.getValue());
    private final Button cancel = new Button(Names.CANCEL.getValue());
    private final Button signUp = new Button(Names.SIGN_UP.getValue());
    private final Button resetPassword = new Button(Names.RESET_PASSWORD.getValue());
    private final TeamLogInView team = new TeamLogInView();
    private final UserManager userManager = new UserManager();
    private final LeftBarView leftBarView;
    private final MainView mainView;


    public LogInView(MainView mainView) {
        this.mainView = mainView;
        this.leftBarView = mainView.getLeftBar();
        setSizeUndefined();
        team.setMainLayout(this);

        add(
                setElements()
        );

        logIn.addClickListener(event -> {
            logIn();
        });
        cancel.addClickListener(event -> mainView.getLeftBar().remove(mainView.getLeftBar().getAccountLayout()));
        signUp.addClickListener(event -> {
            mainView.getLeftBar().getAccountLayout().remove(this);
            mainView.getLeftBar().getAccountLayout().add(mainView.setSignUpView());
        });
        resetPassword.addClickListener(event -> {
            resetPasswordExecution();
        });
    }

    private void logIn(){
        NotificationService notification = new NotificationService();
        if (!Objects.equals(name.getValue(), "") && !Objects.equals(password.getValue(), "")) {
            UserRespondDto respond = mainView.getFacade().logIn(new UserDto(name.getValue(), password.getValue()));
            if (respond != null) {
                if (respond.isLogIn()) {
                    logInExecution(respond);
                } else {
                    notification.bad(respond.getRespond());
                }
            } else {
                notification.bad(Messages.LOG_IN_BAD.getMessage());
            }
        }else {
            notification.neutral(Messages.FILL_ALL_FIELDS.getMessage());
        }
    }

    private void resetPasswordExecution() {
        NotificationService notification = new NotificationService();
        if (!Objects.equals(name.getValue(), "")) {
            NotificationRespond respond = mainView.getFacade().resetPassword(name.getValue());
            if (respond.getType().equals(NotificationTypes.SUCCESS.getType())) {
                notification.good(respond.getMessage());
            } else {
                if (respond.getType().equals(NotificationTypes.ERROR.getType())) {
                    notification.bad(respond.getMessage());
                } else {
                    notification.neutral(respond.getMessage());
                }
            }
        } else {
            notification.neutral(Messages.FILL_ALL_FIELDS.getMessage());
        }
    }

    private VerticalLayout setElements() {
        VerticalLayout vl = new VerticalLayout();
        team.setText(name);
        team.setPassword(password);
        team.setAcceptBtn(logIn);
        team.setCloseBtn(cancel);
        team.setAcceptBtn(signUp);
        team.setResetPasswordBtn(resetPassword);
        logIn.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        vl.add(
                name,
                password,
                logIn,
                cancel,
                signUp,
                resetPassword
        );
        team.setSecondaryLayout(vl);
        return vl;
    }

    private void logInExecution(UserRespondDto respond) {
        NotificationService notification = new NotificationService();
        mainView.setUser(userManager.setUser(respond));
        mainView.getLeftBar().getAccountLayout().remove(this);
        mainView.getDoubleLayout().removeAll();
        leftBarView.getUserView().getUserLabel().setText(respond.getUserName());
        leftBarView.getUserView().getLogButton().setText(Names.LOG_OUT.getValue());
        leftBarView.getUserView().getYourAccount().setEnabled(true);
        team.setGeneralBtnWarningColor(leftBarView.getUserView().getLogButton());
        team.setGeneralBtn(leftBarView.getUserView().getYourAccount());
        notification.good(respond.getRespond());
    }

    public void logInExecute() {
        mainView.getLeftBar().getAccountLayout().add(setLogInView());
    }

    public LogInView setLogInView() {
        return mainView.getLoginView();
    }
}
