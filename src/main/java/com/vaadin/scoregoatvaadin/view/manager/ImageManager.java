package com.vaadin.scoregoatvaadin.view.manager;

import com.vaadin.flow.component.html.Image;
import com.vaadin.scoregoatvaadin.domain.Icon;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class ImageManager {

    public Image setTeamLogo(String dir) {
        Image img = new Image(dir, Icon.ERROR.getIcon());
        img.setWidth(TeamValues.EM_2.getValues());
        img.setHeight(TeamValues.EM_2.getValues());
        return img;
    }

    public Image setLeagueLogo(String dir) {
        Image img = new Image(dir, Icon.ERROR.getIcon());
        img.setWidth(TeamValues.EM_3_5.getValues());
        img.setHeight(TeamValues.EM_3_5.getValues());
        return img;
    }
    public Image setIcon(String dir) {
        Image img = new Image(dir, Icon.ERROR.getIcon());
        img.setWidth(TeamValues.EM_3_5.getValues());
        img.setHeight(TeamValues.EM_3_5.getValues());
        return img;
    }
}
