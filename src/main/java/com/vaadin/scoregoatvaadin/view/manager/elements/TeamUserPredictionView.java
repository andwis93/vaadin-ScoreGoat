package com.vaadin.scoregoatvaadin.view.manager.elements;

import com.vaadin.flow.component.button.Button;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class TeamUserPredictionView {
    public void setRankingButton(Button button) {
        button.setWidth(TeamValues.EM_20.getValues());
        button.setHeight(TeamValues.EM_2.getValues());
        button.getStyle().set("font-size", TeamValues.PX_24.getValues());
        button.getStyle().set("color", TeamValues.WHITE.getValues());
        button.getStyle().set("background",  TeamValues.USER_BUTTON.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }
}
