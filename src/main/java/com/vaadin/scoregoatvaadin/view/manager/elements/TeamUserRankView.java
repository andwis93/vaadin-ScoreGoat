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
        label.getStyle().set("font-size", TeamValues.PX_10.getValues());
        label.getStyle().set("color", TeamValues.USER_BUTTON.getValues());
    }

    public void setMainLayout(VerticalLayout vl) {
        vl.setHorizontalComponentAlignment(FlexComponent.Alignment.BASELINE);
    }

    public void setImgLayout(HorizontalLayout hl) {
        hl.setWidth(TeamValues.EM_16.getValues());
        hl.setVerticalComponentAlignment(FlexComponent.Alignment.START);
        hl.setAlignItems(FlexComponent.Alignment.CENTER);
    }

    public void setPlaceLayout(VerticalLayout vl) {
        vl.setPadding(false);
        vl.setSpacing(false);
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        vl.setSizeUndefined();
    }

    public void setRankingLayout(HorizontalLayout hl) {
        hl.setSizeUndefined();
        hl.setVerticalComponentAlignment(FlexComponent.Alignment.START);
        hl.setAlignItems(FlexComponent.Alignment.CENTER);
    }
}
