package com.vaadin.scoregoatvaadin.service;

import com.vaadin.scoregoatvaadin.domain.LeagueButton;
import com.vaadin.scoregoatvaadin.domain.LeagueLogos;
import com.vaadin.scoregoatvaadin.domain.Leagues;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LeagueButtonService {
    private final List<LeagueButton> list;
    private static LeagueButtonService leagueButtonService;

    public LeagueButtonService(){
        this.list = buttons();
    }

    public static LeagueButtonService getInstance() {
        if(leagueButtonService == null) {
            leagueButtonService = new LeagueButtonService();
        }
        return leagueButtonService;
    }

    private List<LeagueButton> buttons () {
        List<LeagueButton> list = new ArrayList<>();
        list.add(new LeagueButton(Leagues.PREMIER_LEAGUE.getLeagueId(), LeagueLogos.PREMIER_LEAGUE.getValue()));
        list.add(new LeagueButton(Leagues.LA_LIGA.getLeagueId(), LeagueLogos.LA_LIGA.getValue()));
        list.add(new LeagueButton(Leagues.CHAMPIONS_LEAGUE.getLeagueId(), LeagueLogos.CHAMPIONS_LEAGUE.getValue()));
        return list;
    }
}
