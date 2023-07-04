package com.vaadin.scoregoatvaadin.service;

import com.vaadin.scoregoatvaadin.domain.RankingDto;
import com.vaadin.scoregoatvaadin.domain.UserRankDto;
import com.vaadin.scoregoatvaadin.view.MainView;
import lombok.Getter;

@Getter
public class UserRankingService {
    private final UserRankDto userRankDto;

    public UserRankingService(MainView mainView) {
        if (mainView.getUser() != null && mainView.getUser().getId() != null) {
            this.userRankDto = mainView.getFacade().fetchRankingDto(mainView.getUser().getId(), mainView.getLeagueId());
        } else {
            this.userRankDto = new UserRankDto(new RankingDto("0", "", "0", 0), 0);
        }
    }
}

