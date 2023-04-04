package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LeagueLogos {
    LA_LIGA("LaLiga.png"),
    PREMIER_LEAGUE("PremierLeague.png"),
    CHAMPIONS_LEAGUE("ChampionsLeague.png");

    private final String value;
}
