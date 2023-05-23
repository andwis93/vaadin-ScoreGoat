package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LeagueLogos {
    LA_LIGA("https://media.api-sports.io/football/leagues/140.png"),
    PREMIER_LEAGUE("https://media.api-sports.io/football/leagues/39.png"),
    CHAMPIONS_LEAGUE("https://media.api-sports.io/football/leagues/2.png"),
    MAJOR_LEAGUE_SOCCER("https://media-1.api-sports.io/football/leagues/253.png");

    private final String value;
}
