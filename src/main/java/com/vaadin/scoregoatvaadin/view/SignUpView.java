package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.scoregoatvaadin.domain.*;
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
    private final Label info = new Label();
    private final TeamSignUpView team = new TeamSignUpView();
    private final MainView mainView;


    public SignUpView(MainView mainView) {
        this.mainView = mainView;

        setSizeUndefined();
        team.setMineLayout(this);

        create.addClickListener(event -> signIn());
        close.addClickListener(event -> mainView.getAccountLayout().remove(this));

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
        team.setInfoLabelError(info);
        vl.add (
                name,
                email,
                password,
                repeatPassword,
                create,
                close,
                info
        );
        team.setSecondaryLayout(vl);
        return vl;
    }

    public void signIn(){
        if (password.getValue().equals(repeatPassword.getValue())) {
            UserDto userDto = new UserDto(name.getValue(), email.getValue(), password.getValue());
            UserRespondDto respond = mainView.getFacade().signUp(userDto);
            if (respond != null) {
                if (respond.isLogIn()) {
                    signInExecute(respond);
                } else {
                    info.setText(respond.getRespond());
                }
            }else {
                info.setText(Messages.SIGN_UP_USER_NOT_CREATED.getMessage());
            }
        }else {
            info.setText(Messages.SIGN_UP_PASSWORD_DIFFERENT.getMessage());
        }
    }

    private void signInExecute(UserRespondDto respond){
        mainView.getAccountLayout().remove(this);
        Notification notification = Notification.show(respond.getRespond());
        notification.setPosition(Notification.Position.MIDDLE);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }
}
