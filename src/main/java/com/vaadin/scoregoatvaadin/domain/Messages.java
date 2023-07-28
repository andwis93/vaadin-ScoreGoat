package com.vaadin.scoregoatvaadin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Messages {
    LEFT_BAR_VIEW_LEAGUE_BTN("No matches for the next 10 days for this league"),
    PASSWORD_CHANGE_ERROR("Error. Couldn't change password!"),
    SIGN_UP_USER_NOT_CREATED("Error occur. Couldn't create new user!"),
    SIGN_UP_PASSWORD_DIFFERENT("Repeated password is different. Please enter same password twice to finalize signing in."),
    SAVE_EXECUTION_NOT_SAVE("Couldn't save user predictions. Please make sure you are logged in and try again"),
    ACCOUNT_VIEW_ENTER_VALUE("Please enter values in all fields!"),
    ACCOUNT_UPDATED_OK("Account was updated successfully"),
    ACCOUNT_UPDATED_BAD("Error. Couldn't updated account"),
    PASSWORD_CHANGE_OK("Password was changed successfully"),
    SAVE_PREDICTIONS_BAD("Something Went Wrong - couldn't save predictions"),
    FILL_ALL_FIELDS("Please, fill in all fields correctly"),
    LOG_IN_BAD("Error. Couldn't log in user"),
    PASSWORD_RESET_ERROR("Something went wrong. Couldn't reset password"),
    USER_DELETED_OK("User was deleted successfully");

    private final String message;
}
