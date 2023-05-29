package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NotificationTypes {
    ERROR("error"),
    SUCCESS("success");

    private final String type;
}
