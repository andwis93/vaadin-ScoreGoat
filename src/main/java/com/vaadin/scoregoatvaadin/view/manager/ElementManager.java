package com.vaadin.scoregoatvaadin.view.manager;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class ElementManager {
    public void setTextElements(TextField text) {
        text.setWidth(TeamValues.EM_16.getValues());
    }

    public void setEmailElement(EmailField email) {
        email.setWidth(TeamValues.EM_16.getValues());
    }

    public void setPasswordElement(PasswordField password) {
        password.setWidth(TeamValues.EM_16.getValues());
    }

    public void setGeneralButton(Button button) {
        button.setSizeUndefined();
        button.getStyle().set("color", TeamValues.WHITE.getValues());
        button.getStyle().set("background", TeamValues.MAIN_COLOR.getValues());
        button.getStyle().set("font-size", TeamValues.PX_18.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }

    public void setGeneralButtonWarningColor(Button button) {
        button.setSizeUndefined();
        button.getStyle().set("color", TeamValues.WHITE.getValues());
        button.getStyle().set("background", TeamValues.DARK_SALMON.getValues());
        button.getStyle().set("font-size", TeamValues.PX_18.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }

    public void setGeneralButtonNotEnable(Button button) {
        button.setSizeUndefined();
        button.getStyle().set("color", TeamValues.MAIN_COLOR.getValues());
        button.getStyle().set("background", TeamValues.SILVER.getValues());
        button.getStyle().set("font-size", TeamValues.PX_18.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }

    public void setSideMenuButtonAccept(Button button) {
        button.setWidth(TeamValues.EM_14.getValues());
        button.setHeight(TeamValues.EM_3.getValues());
        button.getStyle().set("color", TeamValues.WHITE.getValues());
        button.getStyle().set("background", TeamValues.MAIN_COLOR.getValues());
        button.getStyle().set("font-size", TeamValues.PX_18.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }

    public void setSideMenuButtonNotEnable(Button button) {
        button.setWidth(TeamValues.EM_14.getValues());
        button.setHeight(TeamValues.EM_3.getValues());
        button.getStyle().set("color", TeamValues.MAIN_COLOR.getValues());
        button.getStyle().set("background", TeamValues.LIGHT_GREY.getValues());
        button.getStyle().set("font-size", TeamValues.PX_18.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
        button.setEnabled(false);
    }

    public void setSideMenuButtonClose(Button button) {
        button.setWidth(TeamValues.EM_14.getValues());
        button.setHeight(TeamValues.EM_3.getValues());
        button.getStyle().set("color", TeamValues.WHITE.getValues());
        button.getStyle().set("background", TeamValues.DARK_SALMON.getValues());
        button.getStyle().set("font-size", TeamValues.PX_18.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }

    public void setInfoLabel(Label label) {
        label.setWidth(TeamValues.EM_16.getValues());
        label.getStyle().set("color", TeamValues.BLACK.getValues());
    }

    public void setInfoErrorLabel(Label label) {
        label.setWidth(TeamValues.EM_16.getValues());
        label.getStyle().set("color", TeamValues.RED.getValues());
    }



    public void setUserLabel(Label label) {
        label.getStyle().set("font-size",TeamValues.PX_18.getValues());
        label.getStyle().set("color", TeamValues.BLACK.getValues());
        label.getStyle().set("font-weight", TeamValues.BOLD.getValues());
    }

    public void setPositionLabel(Label label) {
        label.getElement().getStyle().set("font-size", TeamValues.PX_32.getValues());
        label.getElement().getStyle().set("font-weight", TeamValues.BOLD.getValues());
    }


 }

