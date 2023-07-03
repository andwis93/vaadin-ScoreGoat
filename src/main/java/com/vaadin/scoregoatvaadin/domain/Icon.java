package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Icon {
    CUP("https://img.icons8.com/doodle/48/000000/trophy--v1.png"),
    COOL("https://img.icons8.com/officel/40/cool.png"),
    CARTMAN("https://img.icons8.com/fluency/48/eric-cartman.png"),
    CRYBABY("https://img.icons8.com/officel/40/crying-baby.png"),
    HOMER("https://img.icons8.com/doodle/48/homer-simpson.png"),
    ERROR("https://img.icons8.com/dotty/80/jake.png");


    private final String icon;
}
