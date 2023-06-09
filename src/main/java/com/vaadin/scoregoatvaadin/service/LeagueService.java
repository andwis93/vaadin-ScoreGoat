package com.vaadin.scoregoatvaadin.service;

import com.vaadin.scoregoatvaadin.domain.LeagueLogos;
import com.vaadin.scoregoatvaadin.domain.Leagues;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
public class LeagueService {
    private final Map<Integer, String> list;

    public LeagueService(){
        this.list = leagues();
    }

    private Map<Integer, String> leagues () {
        Map<Integer, String> theList = new HashMap<>();
        theList.put(Leagues.CHAMPIONS_LEAGUE.getLeagueId(), LeagueLogos.CHAMPIONS_LEAGUE.getValue());
        theList.put(Leagues.PREMIER_LEAGUE.getLeagueId(), LeagueLogos.PREMIER_LEAGUE.getValue());
        theList.put(Leagues.LA_LIGA.getLeagueId(), LeagueLogos.LA_LIGA.getValue());
        theList.put(Leagues.MAJOR_LEAGUE_SOCCER.getLeagueId(), LeagueLogos.MAJOR_LEAGUE_SOCCER.getValue());
        return theList;
    }
}
