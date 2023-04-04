package com.vaadin.scoregoatvaadin.facade;

import com.vaadin.scoregoatvaadin.client.ScoreGoatClient;
import com.vaadin.scoregoatvaadin.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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

    public UserRespondDto logIn(UserParamDto userParam) {
        return scoreGoatClient.logIn(userParam);
    }

    public UserRespondDto changePassword(PasswordDto passwordDto) {
        return scoreGoatClient.changePassword(passwordDto);
    }

    public List<Match> fetchMatchesByLeagueId(int leagueId) {
        return scoreGoatClient.getMatchesByLeagueId(leagueId);
    }
}
