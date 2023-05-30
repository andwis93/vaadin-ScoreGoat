package com.vaadin.scoregoatvaadin.service.elements;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class TeamDoubleLayoutService {
    public void setSaveButton(Button button) {
        button.setWidth(TeamValues.EM_20.getValues());
        button.setHeight(TeamValues.EM_2.getValues());
        button.getStyle().set("font-size", TeamValues.PX_24.getValues());
        button.getStyle().set("color", TeamValues.WHITE.getValues());
        button.getStyle().set("background",  TeamValues.USER_BUTTON.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }

    public void setRightLayout(VerticalLayout layout){
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.getStyle().set("background", TeamValues.DOUBLE_LAYOUT.getValues());
        layout.setWidth(TeamValues.EM_40.getValues());
        layout.setHeight(TeamValues.EM_40.getValues());
    }

    public void setLeftLayout(VerticalLayout layout){
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.getStyle().set("background", TeamValues.DOUBLE_LAYOUT.getValues());
        layout.setWidth(TeamValues.EM_42.getValues());
        layout.setHeight(TeamValues.EM_40.getValues());
    }
}
