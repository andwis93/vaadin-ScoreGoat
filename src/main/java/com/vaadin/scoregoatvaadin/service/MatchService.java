package com.vaadin.scoregoatvaadin.service;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.Match;
import com.vaadin.scoregoatvaadin.domain.NotificationRespond;
import com.vaadin.scoregoatvaadin.domain.PredictionDto;
import com.vaadin.scoregoatvaadin.domain.TeamValues;
import com.vaadin.scoregoatvaadin.view.MainView;
import com.vaadin.scoregoatvaadin.view.MatchView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Service
public class MatchService {
    private List<Match> matches = new ArrayList<>();
    private MainView mainView;
    private Button save = new Button("SAVE");

    public MatchService(MainView mainView) {
        this.mainView = mainView;
        save.addClickListener(event -> saveExecution());
    }

    private List<Match> provideMatches(Long userId, int leagueId) {
        return mainView.getFacade().fetchMatchesByLeagueId(userId, leagueId);
    }

    public VerticalLayout createLayout(int leagueId) {
        VerticalLayout vl = new VerticalLayout();
        Map<Long,String> matchList = new HashMap<>();
        setSaveButton();
        vl.add(save);
        Long userId = Optional.ofNullable(mainView.getUser()).isPresent() ? mainView.getUser().getId() : 0;
        matches = provideMatches(userId, leagueId);
        mainView.getMatchList().setLeagueId(leagueId);
        for(Match match : matches) {
            matchList.put(match.getId(),"");
            vl.add(new MatchView(match, mainView));
        }
        mainView.getMatchList().setMatchList(matchList);
        return vl;
    }

    public void setSaveButton() {
        save.setWidthFull();
        save.setHeight("80px");
        save.getStyle().set("font-size", TeamValues.PX_36.getValues());
        save.getStyle().set("color", TeamValues.WHITE.getValues());
        save.getStyle().set("background",  TeamValues.SAVE_BUTTON.getValues());
        save.getStyle().set("cursor", TeamValues.POINTER.getValues());
    }

    public void saveExecution(){
        NotificationSelection selection = new NotificationSelection();
        if ((mainView.getUser() != null) && (mainView.getUser().getId() != null)) {
            PredictionDto predictionDto = new PredictionDto(mainView.getUser().getId(),
                    mainView.getMatchList().getMatchList());
            NotificationRespond respond = mainView.getFacade().saveUserPredictions(predictionDto);
            mainView.getLeftBar().leagueButtonClick(mainView.getMatchList().getLeagueId(), mainView.getMatchesLayout());
            Notification notification = Notification.show(respond.getMessage());
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addThemeVariants(selection.selectVariant(respond.getType()));

        } else {
            Notification notification = Notification.show("Couldn't save user predictions. Please make sure you are logged in and try again");
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}
