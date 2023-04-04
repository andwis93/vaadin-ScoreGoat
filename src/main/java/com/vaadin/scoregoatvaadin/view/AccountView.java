package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.scoregoatvaadin.domain.Names;
import com.vaadin.scoregoatvaadin.domain.TeamValues;
import com.vaadin.scoregoatvaadin.view.manager.ElementManager;
import lombok.Getter;

@Getter
public class AccountView extends VerticalLayout {
    private final TextField name = new TextField(Names.USER_LABEL.getValue());
    private final EmailField email = new EmailField(Names.EMAIL.getValue());
    private final Button update = new Button(Names.UPDATE.getValue());
    private final Button accept = new Button(Names.ACCEPT.getValue());
    private final Button changePassword = new Button(Names.CHANGE_PASSWORD.getValue());
    private final Button close = new Button(Names.CLOSE.getValue());
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
        accept.addClickListener(event -> acceptExecute());
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
                update,
                accept,
                changePassword,
                close
        );
        vl.getStyle().set("background", TeamValues.WHITE.getValues());
        return vl;
    }

    private void updateExecute() {
        setElements.setSideMenuButtonNotEnable(update);
        setElements.setSideMenuButtonAccept(accept);
        name.setEnabled(true);
        email.setEnabled(true);
        update.setEnabled(false);
        accept.setEnabled(true);
    }

    private void acceptExecute() {
        setElements.setSideMenuButtonAccept(update);
        setElements.setSideMenuButtonNotEnable(accept);
        name.setEnabled(false);
        email.setEnabled(false);
        update.setEnabled(true);
        accept.setEnabled(false);
    }

    public AccountView setAccountView() {
        return mainView.getAccountView();
    }

    public void accountExecute(){
        mainView.add(setAccountView());
        if (mainView.getUser().getId() != null) {
            mainView.getAccountView().getName().setValue(mainView.getUser().getName());
            mainView.getAccountView().getEmail().setValue(mainView.getUser().getEmail());
        }
    }
}
