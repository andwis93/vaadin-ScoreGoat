package com.vaadin.scoregoatvaadin.service;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.Messages;
import com.vaadin.scoregoatvaadin.domain.TeamValues;
import com.vaadin.scoregoatvaadin.service.elements.TeamDoubleLayoutService;
import com.vaadin.scoregoatvaadin.view.RankingView;
import com.vaadin.scoregoatvaadin.view.MainView;
import com.vaadin.scoregoatvaadin.view.UserPredictionView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoubleLayoutService {
    private UserPredictionView userPredictionView;
    private RankingView rankingView;
    private MatchService matchService;
    private TeamDoubleLayoutService team = new TeamDoubleLayoutService();
    private Button save = new Button("SAVE");

    public DoubleLayoutService(MainView mainView) {
        this.matchService = new MatchService(mainView);
        this.userPredictionView = new UserPredictionView(mainView);
        this.rankingView = new RankingView(mainView);
        team.setSaveButton(save);
        save.addClickListener(event -> matchService.saveExecution());
    }

    public VerticalLayout setRightLayout(MainView mainView){
        VerticalLayout predictionLayout;
        try {
            predictionLayout = userPredictionView.predictionExecution(mainView);
        } catch (Exception ex) {
            predictionLayout = new VerticalLayout();
        }
        team.setRightLayout(predictionLayout);
        return predictionLayout;
    }

    public VerticalLayout setLeftLayout(int leagueId){
        VerticalLayout fixturesLayout = new VerticalLayout();
        team.setLeftLayout(fixturesLayout);
        VerticalLayout matchLayout;
        Section section;
        try {
            matchLayout = matchService.createLayout(leagueId);
        } catch (Exception ex) {
            matchLayout = new VerticalLayout(
                    new NativeLabel(Messages.LEFT_BAR_VIEW_LEAGUE_BTN.getMessage()));
        }
        section = new Section(matchLayout);
        Scroller scroller = new Scroller(section);
        scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
        scroller.getStyle()
                .set("border-bottom", "1px solid var(--lumo-contrast-20pct)")
                .set("padding", "var(--lumo-space-m)");
        scroller.setHeight(TeamValues.EM_55.getValues());
        fixturesLayout.add(scroller, save);
        return fixturesLayout;
    }
}
