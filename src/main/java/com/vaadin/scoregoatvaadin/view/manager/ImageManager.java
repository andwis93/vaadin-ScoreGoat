package com.vaadin.scoregoatvaadin.view.manager;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class ImageManager {

    public Image setImage(String dir) {
        Image img = getImage(dir);
        img.setHeight(TeamValues.EM_6.getValues());
        return img;
    }

    public Image getImage(String dir) {
        StreamResource imageResource = new StreamResource(dir,
                () -> getClass().getResourceAsStream("/" + dir));
        return new Image(imageResource, null);
    }

    public Image setTeamLogo( String dir) {
        Image img = new Image(dir, "TeamLogos");
        img.setWidth(TeamValues.EM_3.getValues());
        img.setHeight(TeamValues.EM_3.getValues());
        return img;
    }
}
