package com.vaadin.scoregoatvaadin.service;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.NotificationTypes;
import com.vaadin.scoregoatvaadin.domain.Match;
import com.vaadin.scoregoatvaadin.domain.Messages;
import com.vaadin.scoregoatvaadin.domain.NotificationRespond;
import com.vaadin.scoregoatvaadin.domain.PredictionDto;
import com.vaadin.scoregoatvaadin.service.elements.TeamMatchService;
import com.vaadin.scoregoatvaadin.view.MainView;
import com.vaadin.scoregoatvaadin.view.MatchView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Service
public class MatchService {
    private List<Match> matches = new ArrayList<>();
    private TeamMatchService team = new TeamMatchService();
    private MainView mainView;


    public MatchService(MainView mainView) {
        this.mainView = mainView;
    }

    private List<Match> provideMatches(Long userId, int leagueId) {
        return mainView.getFacade().fetchMatchesByLeagueId(userId, leagueId);
    }

    public VerticalLayout createLayout(int leagueId) {
        VerticalLayout vl = new VerticalLayout();
        Map<Long,String> matchList = new HashMap<>();
        Long userId = Optional.ofNullable(mainView.getUser()).isPresent() ? mainView.getUser().getId() : 0;
        matches = provideMatches(userId, leagueId);
        mainView.getMatchList().setLeagueId(leagueId);
        for(Match match : matches) {
            matchList.put(match.getFixtureId(),"");
            vl.add(new MatchView(match, mainView));
        }
        team.setVerticalLayout(vl);
        mainView.getMatchList().setMatchList(matchList);
        return vl;
    }

    public void saveExecution(){
        NotificationService notification = new NotificationService();
        if ((mainView.getUser() != null) && (mainView.getUser().getId() != null)) {
            PredictionDto predictionDto = new PredictionDto(mainView.getUser().getId(), mainView.getMatchList().getLeagueId(),
                    mainView.getMatchList().getMatchList());
            NotificationRespond respond = mainView.getFacade().saveUserPredictions(predictionDto);
            if (respond.isLoggedIn()) {
                mainView.getLeftBar().leagueButtonClick(mainView.getMatchList().getLeagueId(), mainView.getDoubleLayout());
                if (respond.getType().equals(NotificationTypes.SUCCESS.getType())) {
                    notification.good(respond.getMessage());
                } else {
                    notification.bad(respond.getMessage());
                }
            } else {
                mainView.getLeftBar().getUserView().logOutExecute();
            }
            } else {
                notification.bad(Messages.SAVE_EXECUTION_NOT_SAVE.getMessage());

        }
    }
}
