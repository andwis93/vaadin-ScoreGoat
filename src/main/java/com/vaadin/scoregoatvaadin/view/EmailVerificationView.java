package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.scoregoatvaadin.domain.*;
import com.vaadin.scoregoatvaadin.service.NotificationService;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamEmailVerificationView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailVerificationView extends VerticalLayout {
    private final TextField verificationCode = new TextField(Names.VERIFICATION_CODE.getValue());
    private final Button accept = new Button(Names.ACCEPT.getValue());
    private final Button cancel = new Button(Names.CANCEL.getValue());
    private final TeamEmailVerificationView team = new TeamEmailVerificationView();
    private final EmailVerification emailVerification = new EmailVerification();
    private MainView mainView;
    private int counter = 0;


    public EmailVerificationView(MainView mainView) {
        this.mainView = mainView;
        setSizeUndefined();
        team.setMainLayout(this);
        add(
                setElements()
        );
        accept.addClickListener(event -> verifyCode(mainView));
        cancel.addClickListener(event -> mainView.getLeftBarView().remove(this));
    }

    private VerticalLayout setElements() {
        VerticalLayout vl = new VerticalLayout();
        team.setSecondaryLayout(vl);
        team.setCode(verificationCode);
        team.setAcceptBtn(accept);
        team.setCloseBtn(cancel);
        accept.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        vl.add(
                verificationCode,
                accept,
                cancel
        );
        return vl;
    }

    private void verifyCode(MainView mainView) {
        if (counter < 3) {
            NotificationService notification = new NotificationService();
            if (verificationCode.getValue() != null) {
                if (verificationCode.getValue().equals(emailVerification.getCode())) {
                    UserDto userDto = new UserDto(emailVerification.getUserDto().getName(), emailVerification.getUserDto().getEmail(),
                            emailVerification.getUserDto().getPassword());
                    UserRespondDto respond = mainView.getFacade().signUp(userDto);
                    if (respond != null) {
                        if (respond.isLogIn()) {
                            signInExecution(respond);
                        } else {
                            notification.bad(respond.getRespond());
                            if (emailVerification.getCounter() > 3) {
                                mainView.getLeftBarView().remove(this);
                            } else {
                                emailVerification.addToCounter();
                            }
                        }
                    } else {
                        notification.bad(Messages.SIGN_UP_USER_NOT_CREATED.getMessage());
                    }
                } else {
                    notification.bad(Messages.CODE_DIFFERENT.getMessage());
                    counter++;
                }
            } else {
                notification.bad(Messages.CODE_DIFFERENT.getMessage());
                counter++;
            }
        } else {
            toManyAttempts();
        }
}

    private void signInExecution(UserRespondDto respond){
        NotificationService notification = new NotificationService();
        notification.good(respond.getRespond());
        mainView.getLeftBarView().remove(this);
    }

    private void toManyAttempts() {
        NotificationService notification = new NotificationService();
        notification.neutral(Messages.TOO_MANY_ATTEMPTS.getMessage());
        mainView.getLeftBarView().remove(this);
    }
}