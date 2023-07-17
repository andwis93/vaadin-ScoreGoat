package com.vaadin.scoregoatvaadin.facade;

import com.vaadin.scoregoatvaadin.client.ScoreGoatClient;
import com.vaadin.scoregoatvaadin.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ScoreGoatFacade {
    private final ScoreGoatClient scoreGoatClient;

    @Autowired
    public ScoreGoatFacade(ScoreGoatClient scoreGoatClient) {
        this.scoreGoatClient = scoreGoatClient;
    }

    public UserRespondDto signUp(UserDto userDto){
        return scoreGoatClient.createUser(userDto);
    }

    public UserRespondDto logIn(UserDto userDto) {
        return scoreGoatClient.logIn(userDto);
    }

    public boolean changePassword(PasswordDto passwordDto) {
        return scoreGoatClient.changePassword(passwordDto);
    }

    public UserRespondDto changeAccountValues(AccountDto accountDto) {
        return scoreGoatClient.changeAccountValues(accountDto);
    }

    public List<Match> fetchMatchesByLeagueId(Long userId, int leagueId) {
        return scoreGoatClient.getMatchesByLeagueId(userId, leagueId);
    }

    public NotificationRespond saveUserPredictions(PredictionDto predictionDto) {
        return scoreGoatClient.saveUserPredictions(predictionDto);
    }

    public List<UserPredictionDto> fetchUserPredictions(Long userId, int leagueId) {
        return scoreGoatClient.getUserPredictions(userId, leagueId);
    }

    public UserRespondDto deleteUser(UserDto userDto) {
        return scoreGoatClient.deleteUser(userDto);
    }

    public List<RankingDto> getRankingList(int leagueId) {
        return scoreGoatClient.getRankingList(leagueId);
    }

    public RankingDto fetchRankingDto(Long userId, int leagueId) {
        return scoreGoatClient.getRanking(userId, leagueId);
    }
}
