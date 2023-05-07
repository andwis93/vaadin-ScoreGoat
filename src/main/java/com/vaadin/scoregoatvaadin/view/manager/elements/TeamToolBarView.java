package com.vaadin.scoregoatvaadin.view.manager.elements;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class TeamToolBarView {

    public void setMainLayout(HorizontalLayout hl) {
        hl.getStyle().set("background", TeamValues.TOP_MENU.getValues());
    }

    public void setPosition(Label label) {
        label.getElement().getStyle().set("font-size", TeamValues.PX_32.getValues());
        label.getElement().getStyle().set("font-weight", TeamValues.BOLD.getValues());
    }

    public void setGeneralBtn(Button button) {
        button.setSizeUndefined();
        button.getStyle().set("color", TeamValues.WHITE.getValues());
        button.getStyle().set("background", TeamValues.MAIN_COLOR.getValues());
        button.getStyle().set("font-size", TeamValues.PX_18.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }

    public void setUserLabel(Label label) {
        label.getStyle().set("font-size",TeamValues.PX_18.getValues());
        label.getStyle().set("color", TeamValues.BLACK.getValues());
        label.getStyle().set("font-weight", TeamValues.BOLD.getValues());
    }

    public void setGeneraNotEnableBtn(Button button) {
        button.setSizeUndefined();
        button.getStyle().set("color", TeamValues.MAIN_COLOR.getValues());
        button.getStyle().set("background", TeamValues.SILVER.getValues());
        button.getStyle().set("font-size", TeamValues.PX_18.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }
}
