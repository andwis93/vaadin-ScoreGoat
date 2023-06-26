package com.vaadin.scoregoatvaadin.service;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.DoubleLayout;
import com.vaadin.scoregoatvaadin.service.elements.TeamRankingService;
import com.vaadin.scoregoatvaadin.view.MainView;
import com.vaadin.scoregoatvaadin.view.RankingView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingService {
    private RankingView rankingView;
    private MainView mainView;
    private TeamRankingService team = new TeamRankingService();

    public RankingService(MainView mainView) {
        this.mainView = mainView;
        this.rankingView = new RankingView(mainView);
    }

    public void ratingButtonClick(int leagueId, DoubleLayout doubleLayout) {
        doubleLayout.removeAll();
        doubleLayout.setSpacing(false);
        doubleLayout.add(setRightLayout(leagueId));
    }

    public VerticalLayout setRightLayout(int leagueId){
        VerticalLayout predictionLayout;
        try {
            predictionLayout = rankingView.ratingExecution(leagueId);
        } catch (Exception ex) {
            predictionLayout = new VerticalLayout();
        }
        team.setRightLayout(predictionLayout);
        return predictionLayout;
    }
}
