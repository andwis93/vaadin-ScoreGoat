package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Names {
    USER_LABEL("User Name"),
    YOUR_ACCOUNT("Your account"),
    EMAIL("Email"),
    LOG_IN("Log in"),
    LOG_OUT("Log out"),
    SIGN_UP("Sign Up"),
    NAME_TEXT_FIELD("User Name or Email"),
    PASSWORD("Password"),
    CANCEL("Cancel"),
    OLD_PASSWORD("Old Password"),
    NEW_PASSWORD("New Password"),
    REPEAT_PASSWORD("Repeat Password"),
    ACCEPT("OK"),
    CLOSE("Close"),
    UPDATE("Update"),
    CHANGE_PASSWORD("Change password"),
    DELETE("DELETE");

    private final String value;
}
