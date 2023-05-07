package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MatchStatus {
    NOT_STARTED("Not Started"),
    FINISHED("Match Finished");

    private final String status;
}
