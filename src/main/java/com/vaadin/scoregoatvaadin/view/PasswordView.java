package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.scoregoatvaadin.domain.*;
import com.vaadin.scoregoatvaadin.service.NotificationService;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamPasswordView;
import java.util.Objects;

public class PasswordView extends VerticalLayout {
    private final PasswordField oldPassword = new PasswordField(Names.OLD_PASSWORD.getValue());
    private final PasswordField newPassword = new PasswordField(Names.NEW_PASSWORD.getValue());
    private final PasswordField repeatPassword = new PasswordField(Names.REPEAT_PASSWORD.getValue());
    private final Button accept = new Button(Names.ACCEPT.getValue());
    private final Button cancel = new Button(Names.CANCEL.getValue());
    private final TeamPasswordView team = new TeamPasswordView();
    private final MainView mainView;

    public PasswordView(MainView mainView) {
        this.mainView = mainView;
        setSizeUndefined();
        team.setMainLayout(this);
        add(
                setElements()
        );
        accept.addClickListener(event -> changePassword());
        cancel.addClickListener(event -> mainView.getLeftBarView().getAccountLayout().remove(this));
    }
    private VerticalLayout setElements() {
        VerticalLayout vl = new VerticalLayout();
        team.setPassword(oldPassword);
        team.setPassword(newPassword);
        team.setPassword(repeatPassword);
        team.setAcceptBtn(accept);
        team.setCloseBtn(cancel);
        team.setSecondaryLayout(vl);
        accept.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        vl.add(
                oldPassword,
                newPassword,
                repeatPassword,
                accept,
                cancel
        );
        return vl;
    }

    private void changePassword(){
        NotificationService notification = new NotificationService();
        if (!Objects.equals(oldPassword.getValue(), "") && !Objects.equals(newPassword.getValue(), "")
                && !Objects.equals(repeatPassword.getValue(), "") &&
                Objects.equals(newPassword.getValue(), repeatPassword.getValue())) {
            boolean respond = mainView.getFacade().changePassword(new PasswordDto(mainView.getUser().getId(),
                    oldPassword.getValue(), newPassword.getValue(), repeatPassword.getValue()));
            if (respond) {
                changePasswordExecution();
            } else {
                notification.bad(Messages.PASSWORD_CHANGE_ERROR.getMessage());
            }
        } else {
            notification.neutral(Messages.FILL_ALL_FIELDS.getMessage());
        }
    }

    private void changePasswordExecution() {
        NotificationService notification = new NotificationService();
        notification.good(Messages.PASSWORD_CHANGE_OK.getMessage());
        mainView.getLeftBarView().getAccountLayout().remove(this);
    }
}
