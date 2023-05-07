package com.vaadin.scoregoatvaadin.service.elements;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TeamMatchService {

    public void setVerticalLayout(VerticalLayout vl) {
        vl.setSizeUndefined();
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
    }
}
