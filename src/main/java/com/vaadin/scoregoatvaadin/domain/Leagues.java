package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Leagues {
    PREMIER_LEAGUE(39),
    LA_LIGA(140),
    CHAMPIONS_LEAGUE(2);

    private final int leagueId;
}
