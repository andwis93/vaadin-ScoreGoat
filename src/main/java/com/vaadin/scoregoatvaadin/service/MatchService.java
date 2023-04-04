package com.vaadin.scoregoatvaadin.service;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.Match;
import com.vaadin.scoregoatvaadin.view.MainView;
import com.vaadin.scoregoatvaadin.view.MatchView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MatchService {
    private List<Match> matches = new ArrayList<>();
    private MainView mainView;

    public MatchService(MainView mainView) {
        this.mainView = mainView;
    }

    private List<Match> provideMatches(int leagueId) {
        return mainView.getFacade().fetchMatchesByLeagueId(leagueId);
    }

    public VerticalLayout createLayout(int leagueId) {
        VerticalLayout vl = new VerticalLayout();
        matches = provideMatches(leagueId);
        for(Match match : matches) {
            vl.add(new MatchView(match));
        }
        return vl;
    }
}
