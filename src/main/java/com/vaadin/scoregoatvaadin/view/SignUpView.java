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
import com.vaadin.scoregoatvaadin.view.manager.ElementManager;
import com.vaadin.scoregoatvaadin.view.manager.UserManager;
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
    private final ElementManager elements = new ElementManager();
    private final MainView mainView;
    private final ToolBarView toolBarView;

    public SignUpView(MainView mainView) {
        this.mainView = mainView;
        this.toolBarView = mainView.getToolBarView();
        setSizeUndefined();
        setHeight("350em");
        getStyle().set("background", TeamValues.MAIN_COLOR.getValues());

        create.addClickListener(event -> signIn());
        close.addClickListener(event -> mainView.remove(this));

        add(
                setElements()
        );
    }
    private VerticalLayout setElements() {
        VerticalLayout vl = new VerticalLayout();
        elements.setTextElements(name);
        elements.setEmailElement(email);
        elements.setPasswordElement(password);
        elements.setPasswordElement(repeatPassword);
        elements.setSideMenuButtonAccept(create);
        elements.setSideMenuButtonClose(close);
        elements.setInfoErrorLabel(info);
        vl.add (
                name,
                email,
                password,
                repeatPassword,
                create,
                close,
                info
        );
        vl.getStyle().set("background", TeamValues.WHITE.getValues());
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
                info.setText("Error occur. Couldn't create new user!");
            }
        }else {
            info.setText("Repeated password is different. Please enter same password twice to finalize signing in.");
        }
    }

    private void signInExecute(UserRespondDto respond){
        mainView.remove(this);
        Notification notification = Notification.show(respond.getRespond());
        notification.setPosition(Notification.Position.MIDDLE);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }
}
