package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.scoregoatvaadin.domain.*;
import com.vaadin.scoregoatvaadin.service.NotificationService;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamAccountView;
import lombok.Getter;

@Getter
public class AccountView extends VerticalLayout {
    private final TextField name = new TextField(Names.USER_LABEL.getValue());
    private final EmailField email = new EmailField(Names.EMAIL.getValue());
    private final PasswordField password = new PasswordField(Names.PASSWORD.getValue());
    private final Button update = new Button(Names.UPDATE.getValue());
    private final Button accept = new Button(Names.ACCEPT.getValue());
    private final Button changePassword = new Button(Names.CHANGE_PASSWORD.getValue());
    private final Button close = new Button(Names.CLOSE.getValue());
    private final Button delete = new Button(Names.DELETE.getValue());
    private final MainView mainView;
    private final PasswordView changePasswordView;
    private final TeamAccountView team = new TeamAccountView();

    public AccountView(MainView mainView) {
        this.mainView = mainView;
        this.changePasswordView = mainView.getChangePasswordView();
        team.setMineLayout(this);

        add(
                setElements()
        );

        update.addClickListener(event -> updateExecute());
        accept.addClickListener(event -> acceptExecution());
        changePassword.addClickListener(event -> changePasswordExecute());
        close.addClickListener(event -> mainView.getAccountLayout().remove(this));
        delete.addClickListener(event -> deleteExecution());
    }

    private void changePasswordExecute() {
        mainView.getAccountLayout().remove(this);
        mainView.getAccountLayout().add(mainView.setChangePasswordView());
    }

    private void deleteExecution() {
        mainView.getAccountLayout().remove(this);
        mainView.getAccountLayout().add(mainView.setDeleteView());
    }

    private VerticalLayout setElements() {
        VerticalLayout vl = new VerticalLayout();
        team.setText(name);
        name.setEnabled(false);
        team.setEmail(email);
        email.setEnabled(false);
        team.setPassword(password);
        password.setEnabled(false);
        team.setGeneralBtn(update);
        update.setEnabled(true);
        team.setNotEnableBtn(accept);
        accept.setEnabled(false);
        team.setGeneralBtn(changePassword);
        team.setCloseBtn(close);
        team.setDeleteBtn(delete);
        update.addClickShortcut(Key.ENTER);
        accept.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
        vl.add(
                name,
                email,
                password,
                update,
                accept,
                changePassword,
                close,
                delete
        );
        vl.getStyle().set("background", TeamValues.WHITE.getValues());
        return vl;
    }

    private void updateExecute() {
        team.setNotEnableBtn(update);
        team.setAcceptBtn(accept);
        name.setEnabled(true);
        email.setEnabled(true);
        password.setEnabled(true);
        update.setEnabled(false);
        accept.setEnabled(true);
    }

    private void changeExecution() {
        NotificationService notification = new NotificationService();
        AccountDto accountDto = new AccountDto();
        accountDto.setUserId(mainView.getUser().getId());
        accountDto.setUserName(name.getValue());
        accountDto.setEmail(email.getValue());
        accountDto.setPassword(password.getValue());

        UserRespondDto respond = mainView.getFacade().changeAccountValues(accountDto);
        mainView.getLeftBar().getUserView().getUserLabel().setText(accountDto.getUserName());
        mainView.getUser().setName(accountDto.getUserName());
        mainView.getUser().setEmail(accountDto.getEmail());
        mainView.getUser().setId(accountDto.getUserId());

        team.setGeneralBtn(update);
        team.setNotEnableBtn(accept);
        name.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);
        update.setEnabled(true);
        accept.setEnabled(false);
        if (respond.getNotificationType().equals(NotificationTypes.SUCCESS.getType())) {
            notification.good(respond.getRespond());
        } else {
            if (respond.getNotificationType().equals(NotificationTypes.ERROR.getType())) {
                notification.bad(respond.getRespond());
            } else {
                notification.neutral(respond.getRespond());
            }
        }
    }

    public AccountView setAccountView() {
        return mainView.getAccountView();
    }

    public void acceptExecution(){
        NotificationService notification = new NotificationService();
        mainView.getAccountLayout().add(setAccountView());
        if (mainView.getUser().getId() != null) {
            if (!name.getValue().equals("") && !email.getValue().equals("") && !password.getValue().equals("")) {
                changeExecution();
            } else {
                notification.neutral(Messages.ACCOUNT_VIEW_ENTER_VALUE.getMessage());
            }
        }
    }
}
