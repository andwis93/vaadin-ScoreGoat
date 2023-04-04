package com.vaadin.scoregoatvaadin.view.manager;

import com.vaadin.scoregoatvaadin.domain.User;
import com.vaadin.scoregoatvaadin.domain.UserRespondDto;

public class UserManager {
    public User setUser(UserRespondDto respond) {
        return new User(respond.getId(), respond.getUserName(), respond.getEmail());
    }
}
