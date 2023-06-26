package com.vaadin.scoregoatvaadin.view.manager.elements;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class TeamRankingView {

    public void setImgLayout(VerticalLayout layout) {
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.getStyle().set("background", TeamValues.WHITE.getValues());
    }
}
