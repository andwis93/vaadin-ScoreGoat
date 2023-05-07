package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.scoregoatvaadin.domain.*;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamPasswordView;
import java.util.Objects;

public class PasswordView extends VerticalLayout {
    private final PasswordField oldPassword = new PasswordField(Names.OLD_PASSWORD.getValue());
    private final PasswordField newPassword = new PasswordField(Names.NEW_PASSWORD.getValue());
    private final PasswordField repeatPassword = new PasswordField(Names.REPEAT_PASSWORD.getValue());
    private final Button accept = new Button(Names.ACCEPT.getValue());
    private final Button cancel = new Button(Names.CANCEL.getValue());
    private final Label info = new Label();
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
        cancel.addClickListener(event -> mainView.remove(this));
    }
    private VerticalLayout setElements() {
        VerticalLayout vl = new VerticalLayout();
        team.setPassword(oldPassword);
        team.setPassword(newPassword);
        team.setPassword(repeatPassword);
        team.setAcceptBtn(accept);
        team.setCloseBtn(cancel);
        team.setInfoLabelError(info);
        accept.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        vl.add(
                oldPassword,
                newPassword,
                repeatPassword,
                accept,
                cancel,
                info);
        team.setSecondaryLayout(vl);
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
                info.setText(Messages.PASSWORD_CHANGE_ERROR.getMessage());
                team.setInfoLabelError(info);
            }
        }
    }

    private void changePasswordExecution(UserRespondDto respond) {
        info.setText(respond.getRespond());
        team.setInfoLabel(info);
        this.setVisible(false);
    }

}
