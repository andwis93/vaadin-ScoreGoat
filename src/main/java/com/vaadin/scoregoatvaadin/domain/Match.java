package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Match {
    private Long id;
    private int leagueId;
    private Long fixtureId;
    private String date;
    private String time;
    private String status;
    private String elapsed;
    private String homeTeam;
    private String homeLogo;
    private boolean homeWinner;
    private String awayTeam;
    private String awayLogo;
    private boolean awayWinner;
    private int homeGoals;
    private int awayGoals;
}
