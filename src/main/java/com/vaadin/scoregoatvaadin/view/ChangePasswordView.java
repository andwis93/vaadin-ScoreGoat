package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.scoregoatvaadin.domain.Names;
import com.vaadin.scoregoatvaadin.domain.PasswordDto;
import com.vaadin.scoregoatvaadin.domain.TeamValues;
import com.vaadin.scoregoatvaadin.domain.UserRespondDto;
import com.vaadin.scoregoatvaadin.view.manager.ElementManager;
import java.util.Objects;

public class ChangePasswordView extends VerticalLayout {
    private final PasswordField oldPassword = new PasswordField(Names.OLD_PASSWORD.getValue());
    private final PasswordField newPassword = new PasswordField(Names.NEW_PASSWORD.getValue());
    private final PasswordField repeatPassword = new PasswordField(Names.REPEAT_PASSWORD.getValue());
    private final Button accept = new Button(Names.ACCEPT.getValue());
    private final Button cancel = new Button(Names.CANCEL.getValue());
    private final Label info = new Label();
    private final ElementManager elements = new ElementManager();
    private final MainView mainView;

    public ChangePasswordView(MainView mainView) {
        this.mainView = mainView;
        setSizeUndefined();
        setHeight("350em");
        getStyle().set("background", TeamValues.MAIN_COLOR.getValues());
        add(
                setElements()
        );
        accept.addClickListener(event -> changePassword());
        cancel.addClickListener(event -> mainView.remove(this));
    }
    private VerticalLayout setElements() {
        VerticalLayout vl = new VerticalLayout();
        elements.setPasswordElement(oldPassword);
        elements.setPasswordElement(newPassword);
        elements.setPasswordElement(repeatPassword);
        elements.setSideMenuButtonAccept(accept);
        elements.setSideMenuButtonClose(cancel);
        elements.setInfoErrorLabel(info);
        accept.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        vl.add(
                oldPassword,
                newPassword,
                repeatPassword,
                accept,
                cancel,
                info);
        vl.getStyle().set("background", TeamValues.WHITE.getValues());
        return vl;
    }

    private void changePassword(){
        if (!Objects.equals(oldPassword.getValue(), "") && !Objects.equals(newPassword.getValue(), "")
                && !Objects.equals(repeatPassword.getValue(), "")) {
            UserRespondDto respond = mainView.getFacade().changePassword(new PasswordDto(mainView.getUser().getId(),
                    oldPassword.getValue(), newPassword.getValue(), repeatPassword.getValue()));
            if (respond != null) {
                changePasswordExecution(respond);
            } else {
                info.setText("Error occur. Couldn't change password!");
                elements.setInfoErrorLabel(info);
            }
        }
    }
    private void changePasswordExecution(UserRespondDto respond) {
        info.setText(respond.getRespond());
        elements.setInfoLabel(info);
        this.setVisible(false);
    }
}
