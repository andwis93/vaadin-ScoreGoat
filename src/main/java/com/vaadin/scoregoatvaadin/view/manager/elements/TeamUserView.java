package com.vaadin.scoregoatvaadin.view.manager.elements;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class TeamUserView {

    public void setMainLayout(VerticalLayout hl) {
        hl.getStyle().set("background", TeamValues.MAIN_COLOR.getValues());
    }

    public void setGeneralBtn(Button button) {
        button.setSizeUndefined();
        button.getStyle().set("color", TeamValues.BLACK.getValues());
        button.getStyle().set("background", TeamValues.USER_BUTTON.getValues());
        button.getStyle().set("font-size", TeamValues.PX_14.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }

    public void setUserLabel(Label label) {
        label.getStyle().set("color",TeamValues.USER_NAME.getValues());
        label.getStyle().set("font-size",TeamValues.PX_14.getValues());
    }

    public void setGeneraNotEnableBtn(Button button) {
        button.setSizeUndefined();
        button.getStyle().set("color", TeamValues.BLACK.getValues());
        button.getStyle().set("background", TeamValues.SILVER.getValues());
        button.getStyle().set("font-size", TeamValues.PX_14.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }
}
