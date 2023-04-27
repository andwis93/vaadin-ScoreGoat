package com.vaadin.scoregoatvaadin.facade;

import com.vaadin.scoregoatvaadin.client.ScoreGoatClient;
import com.vaadin.scoregoatvaadin.domain.*;
import com.vaadin.scoregoatvaadin.service.MatchFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ScoreGoatFacade {
    private final ScoreGoatClient scoreGoatClient;
    private final MatchFilterService filterService;

    @Autowired
    public ScoreGoatFacade(ScoreGoatClient scoreGoatClient, MatchFilterService filterService) {
        this.scoreGoatClient = scoreGoatClient;
        this.filterService = filterService;
    }

    public UserRespondDto signUp(UserDto userDto){
        return scoreGoatClient.createUser(userDto);
    }

    public UserRespondDto logIn(UserParamDto userParam) {
        return scoreGoatClient.logIn(userParam);
    }

    public UserRespondDto changePassword(PasswordDto passwordDto) {
        return scoreGoatClient.changePassword(passwordDto);
    }

    public UserRespondDto changeAccountValues(AccountDto accountDto) {
        return scoreGoatClient.changeAccountValues(accountDto);
    }

    public List<Match> fetchMatchesByLeagueId(Long userId, int leagueId) {
        return filterService.notPlayed(scoreGoatClient.getMatchesByLeagueId(userId, leagueId));
    }

    public NotificationRespond saveUserPredictions(PredictionDto predictionDto) {
        return scoreGoatClient.saveUserPredictions(filterService.onlySelectedMatches(predictionDto));
    }
}
