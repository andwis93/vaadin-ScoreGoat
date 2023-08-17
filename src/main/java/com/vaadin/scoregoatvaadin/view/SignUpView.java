package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.scoregoatvaadin.domain.*;
import com.vaadin.scoregoatvaadin.service.NotificationService;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamSignUpView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpView extends VerticalLayout {
    private final TextField name = new TextField(Names.USER_LABEL.getValue());
    private final EmailField email = new EmailField(Names.EMAIL.getValue());
    private final PasswordField password = new PasswordField(Names.PASSWORD.getValue());
    private final PasswordField repeatPassword = new PasswordField(Names.REPEAT_PASSWORD.getValue());
    private final Button create = new Button(Names.SIGN_UP.getValue());
    private final Button close = new Button(Names.CLOSE.getValue());
    private final TeamSignUpView team = new TeamSignUpView();
    private EmailVerificationView emailVerificationView;
    private final MainView mainView;


    public SignUpView(MainView mainView) {
        this.mainView = mainView;

        setSizeUndefined();
        team.setMineLayout(this);

        create.addClickListener(event -> signIn());
        close.addClickListener(event -> mainView.getLeftBarView().getAccountLayout().remove(this));

        add(
                setElements()
        );
    }
    private VerticalLayout setElements() {
        VerticalLayout vl = new VerticalLayout();
        team.setText(name);
        team.setEmail(email);
        team.setPassword(password);
        team.setPassword(repeatPassword);
        team.setAcceptBtn(create);
        team.setCloseBtn(close);
        vl.add (
                name,
                email,
                password,
                repeatPassword,
                create,
                close
        );
        team.setSecondaryLayout(vl);
        return vl;
    }

    public void signIn(){
        NotificationService notification = new NotificationService();
        UserRespondDto userRespondDto = mainView.getFacade().userVerification(name.getValue(), email.getValue());
        if (userRespondDto.getNotificationType().equals(NotificationTypes.SUCCESS.getType())) {
            if (password.getValue().equals(repeatPassword.getValue())) {
                this.emailVerificationView = new EmailVerificationView(mainView);
                emailVerificationView.getEmailVerification().setUserDto(new UserDto(name.getValue(), email.getValue(), password.getValue()));
                emailVerificationView.getEmailVerification().setCode(mainView.getFacade().getEmailVerificationCode(email.getValue()));
                mainView.getLeftBarView().add(emailVerificationView);
                mainView.getLeftBarView().getAccountLayout().remove(this);
                notification.good(Messages.CODE_INFO.getMessage());
            }else {
                notification.bad(Messages.SIGN_UP_PASSWORD_DIFFERENT.getMessage());
            }
        } else {
            notification.bad(userRespondDto.getRespond());
        }
    }
}
