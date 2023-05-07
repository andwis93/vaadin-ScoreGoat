package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Messages {
    LEFT_BAR_VIEW_LEAGUE_BTN("No matches for the next 10 days for this league"),
    LOG_IN_VIEW_LOG_IN_ERROR("Error occur. Couldn't logIn user!"),
    PASSWORD_CHANGE_ERROR("Error occur. Couldn't change password!"),
    SIGN_UP_USER_NOT_CREATED("Error occur. Couldn't create new user!"),
    SIGN_UP_PASSWORD_DIFFERENT("Repeated password is different. Please enter same password twice to finalize signing in."),
    NOT_LOG_IN("Please Log in!"),
    SAVE_EXECUTION_NOT_SAVE("Couldn't save user predictions. Please make sure you are logged in and try again"),
    ACCOUNT_VIEW_ENTER_VALUE("Please enter values in all fields!");

    private final String message;
}
