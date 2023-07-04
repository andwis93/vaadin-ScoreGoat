package com.vaadin.scoregoatvaadin.view.manager.elements;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class TeamUserRankView {
    public void setRankingButton(Button button) {
        button.setWidth(TeamValues.EM_6.getValues());
        button.setHeight(TeamValues.EM_3.getValues());
        button.getStyle().set("font-size", TeamValues.PX_18.getValues());
        button.getStyle().set("color", TeamValues.WHITE.getValues());
        button.getStyle().set("background",  TeamValues.USER_BUTTON.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }

    public void setUserRankLabel(NativeLabel label) {
        label.getStyle().set("font-size", TeamValues.PX_16.getValues());
        label.getStyle().set("color", TeamValues.WHITE.getValues());
    }

    public void setTitleRankLabel(NativeLabel label) {
        label.getStyle().set("font-size", TeamValues.PX_12.getValues());
        label.getStyle().set("color", TeamValues.ORANGE.getValues());
    }

    public void setMainLayout(HorizontalLayout hl) {
        hl.setAlignItems(FlexComponent.Alignment.CENTER);
        hl.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        hl.setSpacing(false);
        hl.setMargin(false);
        hl.setPadding(false);
        hl.setWidth(TeamValues.EM_39.getValues());
    }

    public void setImgLayout(HorizontalLayout hl) {
        hl.setWidth(TeamValues.EM_8.getValues());
        hl.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        hl.setAlignItems(FlexComponent.Alignment.CENTER);
    }

    public void setPlaceLayout(VerticalLayout vl) {
        vl.setPadding(false);
        vl.setSpacing(false);
        vl.setWidth(TeamValues.EM_12.getValues());
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        vl.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
      //  vl.getStyle().set("background", TeamValues.SILVER.getValues());
    }

    public void setRankingLayout(VerticalLayout vl) {
        vl.setWidth(TeamValues.EM_12.getValues());
        vl.setSpacing(false);
        vl.setMargin(false);
        vl.setPadding(false);
        vl.setAlignItems(FlexComponent.Alignment.END);
    }
}
