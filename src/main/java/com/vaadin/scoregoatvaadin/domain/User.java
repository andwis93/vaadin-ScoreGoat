package com.vaadin.scoregoatvaadin.domain;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String email;
    private Long points;
    private Long place;

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
