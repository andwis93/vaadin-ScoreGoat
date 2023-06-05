package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Result {
    UNSET("unset"),
    HOME("home"),
    AWAY("away"),
    DRAW("draw");

    private final String result;
}
