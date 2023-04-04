package com.vaadin.scoregoatvaadin.domain;

import com.vaadin.flow.component.button.Button;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeagueButton {
    private Button button;
    private int id;
    private String logo;

    public LeagueButton( int id, String logo) {
        this.button = new Button();
        this.id = id;
        this.logo = logo;
    }
}
