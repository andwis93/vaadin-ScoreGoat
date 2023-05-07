package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.scoregoatvaadin.domain.*;
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
    private final Label info = new Label();
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
    }

    private void changePasswordExecute() {
        mainView.getAccountLayout().remove(this);
        mainView.getAccountLayout().add(mainView.setChangePasswordView());
    }

    private VerticalLayout setElements() {
        VerticalLayout vl = new VerticalLayout();
        team.setText(name);
        name.setEnabled(false);
        team.setEmail(email);
        email.setEnabled(false);
        team.setPassword(password);
        password.setEnabled(false);
        team.setAcceptBtn(update);
        update.setEnabled(true);
        team.setNotEnableBtn(accept);
        accept.setEnabled(false);
        team.setAcceptBtn(changePassword);
        team.setCloseBtn(close);
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
                info
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
        AccountDto accountDto = new AccountDto();
        accountDto.setUserId(mainView.getUser().getId());
        accountDto.setUserName(name.getValue());
        accountDto.setEmail(email.getValue());
        accountDto.setPassword(password.getValue());

        UserRespondDto userRespondDto = mainView.getFacade().changeAccountValues(accountDto);

        mainView.getLeftBar().getUserView().getUserLabel().setText(userRespondDto.getUserName());
        mainView.getUser().setName(userRespondDto.getUserName());
        mainView.getUser().setEmail(userRespondDto.getEmail());
        mainView.getUser().setId(userRespondDto.getId());

        team.setAcceptBtn(update);
        team.setNotEnableBtn(accept);
        name.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);
        update.setEnabled(true);
        accept.setEnabled(false);

        team.setInfoLabel(info);
        info.setText(userRespondDto.getRespond());
    }

    public AccountView setAccountView() {
        return mainView.getAccountView();
    }

    public void acceptExecution(){
        mainView.getAccountLayout().add(setAccountView());
        if (mainView.getUser().getId() != null) {
            if (!name.getValue().equals("") && !email.getValue().equals("") && !password.getValue().equals("")) {
                changeExecution();
            } else {
                team.setInfoLabelError(info);
                info.setText(Messages.ACCOUNT_VIEW_ENTER_VALUE.getMessage());
            }
        }
    }
}
