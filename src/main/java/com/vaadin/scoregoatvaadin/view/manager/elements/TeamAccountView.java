package com.vaadin.scoregoatvaadin.view.manager.elements;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class TeamAccountView {

    public void setMineLayout(VerticalLayout vl) {
        vl.getStyle().set("background", TeamValues.ACCOUNT_LAYOUTS.getValues());
        vl.setSizeUndefined();
    }

    public void setText(TextField text) {
        text.setWidth(TeamValues.EM_16.getValues());
    }

    public void setEmail(EmailField email) {
        email.setWidth(TeamValues.EM_16.getValues());
    }

    public void setPassword(PasswordField password) {
        password.setWidth(TeamValues.EM_16.getValues());
    }

    public void setAcceptBtn(Button button) {
        button.setWidth(TeamValues.EM_14.getValues());
        button.setHeight(TeamValues.EM_3.getValues());
        button.getStyle().set("color", TeamValues.WHITE.getValues());
        button.getStyle().set("background", TeamValues.MAIN_COLOR.getValues());
        button.getStyle().set("font-size", TeamValues.PX_18.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }

    public void setNotEnableBtn(Button button) {
        button.setWidth(TeamValues.EM_14.getValues());
        button.setHeight(TeamValues.EM_3.getValues());
        button.getStyle().set("color", TeamValues.MAIN_COLOR.getValues());
        button.getStyle().set("background", TeamValues.SILVER.getValues());
        button.getStyle().set("font-size", TeamValues.PX_18.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
        button.setEnabled(false);
    }

    public void setCloseBtn(Button button) {
        button.setWidth(TeamValues.EM_14.getValues());
        button.setHeight(TeamValues.EM_2.getValues());
        button.getStyle().set("color", TeamValues.WHITE.getValues());
        button.getStyle().set("background", TeamValues.DARK_SALMON.getValues());
        button.getStyle().set("font-size", TeamValues.PX_18.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }

    public void setInfoLabel(Label label) {
        label.setWidth(TeamValues.EM_16.getValues());
        label.getStyle().set("color", TeamValues.BLACK.getValues());
    }

    public void setInfoLabelError(Label label) {
        label.setWidth(TeamValues.EM_16.getValues());
        label.getStyle().set("color", TeamValues.RED.getValues());
    }
}
