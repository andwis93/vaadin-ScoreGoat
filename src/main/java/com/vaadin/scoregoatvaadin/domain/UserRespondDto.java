package com.vaadin.scoregoatvaadin.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRespondDto {
    private Long id;
    private String userName;
    private String email;
    private boolean isLogIn = false;
    private String respond;
    private String oldPassword;
    private String newPassword;
    private String repeatPassword;

    public UserRespondDto(Long id, String respond, String oldPassword, String newPassword, String repeatPassword) {
        this.id = id;
        this.respond = respond;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.repeatPassword = repeatPassword;
    }
}
