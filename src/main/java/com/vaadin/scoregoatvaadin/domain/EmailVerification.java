package com.vaadin.scoregoatvaadin.domain;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class EmailVerification {
    private UserDto userDto;
    private String code;
    private int counter;

    public EmailVerification() {
        this.counter = 0;
    }

    public void addToCounter(){
        this.counter++;
    }
}
