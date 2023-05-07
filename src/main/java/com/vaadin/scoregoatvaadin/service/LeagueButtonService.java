package com.vaadin.scoregoatvaadin.service;

import com.vaadin.scoregoatvaadin.domain.LeagueButton;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class LeagueButtonService {
    private final List<LeagueButton> list;
    private static LeagueButtonService leagueButtonService;
    private final LeagueService leagueService = new LeagueService();

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
        List<LeagueButton> theList = new ArrayList<>();
        for(Map.Entry<Integer, String> league:leagueService.getList().entrySet()) {
            theList.add(new LeagueButton(league.getKey(),league.getValue()));
        }
        return theList;
    }
}
