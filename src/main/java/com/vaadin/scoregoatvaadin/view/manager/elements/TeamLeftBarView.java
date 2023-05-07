package com.vaadin.scoregoatvaadin.view.manager.elements;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class TeamLeftBarView {
    public void setLeagueBtn(Button button){
        button.setWidth(TeamValues.EM_6.getValues());
        button.setHeight(TeamValues.EM_7.getValues());
        button.getStyle().set("background", TeamValues.WHITE.getValues());
    }

    public void setLayoutH(HorizontalLayout hl) {
        hl.getStyle().set("background", TeamValues.MAIN_COLOR.getValues());
        hl.setSizeFull();
    }
}
