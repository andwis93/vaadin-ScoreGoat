package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.scoregoatvaadin.domain.*;
import com.vaadin.scoregoatvaadin.service.NotificationService;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamDeleteView;

import java.util.Objects;

public class DeleteView extends VerticalLayout {
    private final NativeLabel head = new NativeLabel("Delete User?");
    private final PasswordField password = new PasswordField(Names.PASSWORD.getValue());
    private final Button accept = new Button(Names.ACCEPT.getValue());
    private final Button cancel = new Button(Names.CANCEL.getValue());
    private final TeamDeleteView team = new TeamDeleteView();
    private final MainView mainView;

    public DeleteView(MainView mainView) {
        this.mainView = mainView;
        setSizeUndefined();
        team.setMainLayout(this);
        add(
                setElements()
        );
        accept.addClickListener(event -> deleteUser());
        cancel.addClickListener(event -> mainView.getLeftBarView().getAccountLayout().remove(this));
    }
    private VerticalLayout setElements() {
        VerticalLayout vl = new VerticalLayout();
        team.setHeader(head);
        team.setAcceptBtn(accept);
        team.setCloseBtn(cancel);
        team.setSecondaryLayout(vl);
        accept.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        vl.add(
                head,
                password,
                accept,
                cancel
        );
        return vl;
    }

    private void deleteUser(){
        NotificationService notification = new NotificationService();
        if (!Objects.equals(password.getValue(), "")){
            UserRespondDto respond = mainView.getFacade().deleteUser(new UserDto(mainView.getUser().getId(),
                    password.getValue()));
            if (respond.getNotificationType().equals(NotificationTypes.SUCCESS.getType())) {
                deleteUserExecution();
                notification.good(respond.getRespond());
                mainView.getLeftBarView().getUserView().logOutExecute();
            } else {
                if (respond.getNotificationType().equals(NotificationTypes.ERROR.getType())) {
                    notification.bad(respond.getRespond());
                } else {
                    notification.neutral(respond.getRespond());
                }
            }
        } else {
            notification.bad(Messages.FILL_ALL_FIELDS.getMessage());
        }
    }

    private void deleteUserExecution() {
        mainView.getLeftBarView().getAccountLayout().remove(this);
    }
}
