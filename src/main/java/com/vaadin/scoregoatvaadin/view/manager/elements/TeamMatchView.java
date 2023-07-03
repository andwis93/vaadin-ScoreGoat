package com.vaadin.scoregoatvaadin.view.manager.elements;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.TeamValues;

public class TeamMatchView {

    public void setMatchLayout(VerticalLayout vl) {
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        vl.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        vl.setHeight(TeamValues.EM_3_5.getValues());
        vl.setPadding(false);
        vl.setMargin(false);
    }
    public void setMainLayout(HorizontalLayout hl) {
        hl.setAlignItems(FlexComponent.Alignment.CENTER);
        hl.getStyle().set("background", TeamValues.DOUBLE_LAYOUT.getValues());
        hl.setSizeFull();
    }

    public void setCenterLayout(VerticalLayout vl) {
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        vl.setWidth(TeamValues.EM_5.getValues());
        vl.setHeight(TeamValues.EM_2.getValues());
        vl.getStyle().set("background", TeamValues.DOUBLE_LAYOUT.getValues());
        vl.setSpacing(false);
        vl.setPadding(false);
    }

    public void setTeamLayout(HorizontalLayout hl) {
        hl.setAlignItems(FlexComponent.Alignment.CENTER);
        hl.setPadding(false);
    }

    public void setTeamButton(Button button, String team){
        button.setText(team);
        button.setWidth(TeamValues.EM_12.getValues());
        button.setHeight(TeamValues.EM_3.getValues());
        button.getStyle().set("color", TeamValues.BLACK.getValues());
        button.getStyle().set("font-size", TeamValues.PX_14.getValues());
        button.getStyle().set("font-weight", TeamValues.BOLD.getValues());
        button.getStyle().set("background", TeamValues.TEAM_BUTTON.getValues());
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }

    public void setData(NativeLabel label) {
        label.getElement().getStyle().set("color", TeamValues.ORANGE.getValues());
        label.getElement().getStyle().set("font-size", TeamValues.PX_12.getValues());
    }

    public void setTime(NativeLabel label) {
        label.getElement().getStyle().set("color", TeamValues.WHITE.getValues());
        label.getElement().getStyle().set("font-size", TeamValues.PX_12.getValues());
        label.getElement().getStyle().set("font-weight", TeamValues.BOLD.getValues());
    }
}
