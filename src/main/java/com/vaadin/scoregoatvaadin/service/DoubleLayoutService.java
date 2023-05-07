package com.vaadin.scoregoatvaadin.service;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.Messages;
import com.vaadin.scoregoatvaadin.domain.TeamValues;
import com.vaadin.scoregoatvaadin.service.elements.TeamDoubleLayoutService;
import com.vaadin.scoregoatvaadin.view.MainView;
import com.vaadin.scoregoatvaadin.view.UserPredictionView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoubleLayoutService {
    private UserPredictionView userPredictionView;
    private MatchService matchService;
    private TeamDoubleLayoutService team = new TeamDoubleLayoutService();
    private Button save = new Button("SAVE");

    public DoubleLayoutService(MainView mainView) {
        this.matchService = new MatchService(mainView);
        this.userPredictionView = new UserPredictionView(mainView);
        team.setSaveButton(save);
        save.addClickListener(event -> matchService.saveExecution());
    }

    public VerticalLayout setRightLayout(int leagueId){
        VerticalLayout predictionLayout;
        try {
            predictionLayout = userPredictionView.predictionExecution(leagueId);
        } catch (Exception ex) {
            predictionLayout = new VerticalLayout();
        }
        team.setRightLayout(predictionLayout);
        return predictionLayout;
    }

    public VerticalLayout setLeftLayout(int leagueId){
        VerticalLayout centerLayout = new VerticalLayout();
        team.setLeftLayout(centerLayout);
        VerticalLayout matchLayout;
        Section section;
        try {
            matchLayout = matchService.createLayout(leagueId);
        } catch (Exception ex) {
            matchLayout = new VerticalLayout(
                    new Label(Messages.LEFT_BAR_VIEW_LEAGUE_BTN.getMessage()));
        }
        section = new Section(matchLayout);
        Scroller scroller = new Scroller(section);
        scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
        scroller.getStyle()
                .set("border-bottom", "1px solid var(--lumo-contrast-20pct)")
                .set("padding", "var(--lumo-space-m)");
        scroller.setHeight(TeamValues.EM_55.getValues());
        centerLayout.add(scroller, save);
        return centerLayout;
    }
}
