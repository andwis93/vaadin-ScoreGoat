package com.vaadin.scoregoatvaadin.service;

import com.vaadin.scoregoatvaadin.domain.UserPredictionDto;
import com.vaadin.scoregoatvaadin.view.MainView;
import lombok.Getter;
import java.util.*;

@Getter
public class PredictionService {
    private final List<UserPredictionDto> predictions;
    private final MainView mainView;

    public PredictionService(MainView mainView) {
        this.mainView = mainView;
        if (mainView.getUser() != null && mainView.getUser().getId() != null) {
            this.predictions = mainView.getFacade().fetchUserPredictions(mainView.getUser().getId(), mainView.getLeagueId());
        } else {
            this.predictions = new ArrayList<>();
        }
    }
}
