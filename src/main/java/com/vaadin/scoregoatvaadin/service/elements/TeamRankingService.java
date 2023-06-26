package com.vaadin.scoregoatvaadin.service.elements;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class TeamRankingService {
    public void setRightLayout(VerticalLayout layout){
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.getStyle().set("background", TeamValues.DOUBLE_LAYOUT.getValues());
        layout.setWidth(TeamValues.EM_26.getValues());
        layout.setHeight(TeamValues.EM_40.getValues());
    }

}
