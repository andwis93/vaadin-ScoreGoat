package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.scoregoatvaadin.domain.AccountDto;
import com.vaadin.scoregoatvaadin.domain.Names;
import com.vaadin.scoregoatvaadin.domain.TeamValues;
import com.vaadin.scoregoatvaadin.domain.UserRespondDto;
import com.vaadin.scoregoatvaadin.view.manager.ElementManager;
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
    private final ChangePasswordView changePasswordView;
    private final ElementManager setElements = new ElementManager();

    public AccountView(MainView mainView) {
        this.mainView = mainView;
        this.changePasswordView = mainView.getChangePasswordView();
        setSizeUndefined();
        setHeight("350em");
        getStyle().set("background", TeamValues.MAIN_COLOR.getValues());

        add(
                setElements()
        );

        update.addClickListener(event -> updateExecute());
        accept.addClickListener(event -> acceptExecution());
        changePassword.addClickListener(event -> changePasswordExecute());
        close.addClickListener(event -> mainView.remove(this));
    }

    private void changePasswordExecute() {
        mainView.remove(this);
        mainView.add(mainView.setChangePasswordView());
    }

    private VerticalLayout setElements() {
        VerticalLayout vl = new VerticalLayout();
        setElements.setTextElements(name);
        name.setEnabled(false);
        setElements.setEmailElement(email);
        email.setEnabled(false);
        setElements.setPasswordElement(password);
        password.setEnabled(false);
        setElements.setSideMenuButtonAccept(update);
        update.setEnabled(true);
        setElements.setSideMenuButtonNotEnable(accept);
        accept.setEnabled(false);
        setElements.setSideMenuButtonAccept(changePassword);
        setElements.setSideMenuButtonClose(close);
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
        setElements.setSideMenuButtonNotEnable(update);
        setElements.setSideMenuButtonAccept(accept);
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

        mainView.getToolBarView().getUserLabel().setText(userRespondDto.getUserName());
        mainView.getUser().setName(userRespondDto.getUserName());
        mainView.getUser().setEmail(userRespondDto.getEmail());
        mainView.getUser().setId(userRespondDto.getId());

        setElements.setSideMenuButtonAccept(update);
        setElements.setSideMenuButtonNotEnable(accept);
        name.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);
        update.setEnabled(true);
        accept.setEnabled(false);

        setElements.setInfoLabel(info);
        info.setText(userRespondDto.getRespond());
    }

    public AccountView setAccountView() {
        return mainView.getAccountView();
    }

    public void acceptExecution(){
        mainView.add(setAccountView());
        if (mainView.getUser().getId() != null) {
            if (!name.getValue().equals("") && !email.getValue().equals("") && !password.getValue().equals("")) {
                changeExecution();
            } else {
                setElements.setInfoErrorLabel(info);
                info.setText("Please enter values in all fields!");
            }
        }
    }
}
