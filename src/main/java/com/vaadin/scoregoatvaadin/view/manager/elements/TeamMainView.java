package com.vaadin.scoregoatvaadin.view.manager.elements;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class TeamMainView {

    public void setMainLayout(VerticalLayout vl) {
        vl.getStyle().set("background", TeamValues.CENTER_BACKGROUND.getValues());
        vl.setAlignSelf(FlexComponent.Alignment.CENTER);
        vl.setSizeFull();
    }
}
