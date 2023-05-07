package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.Match;
import com.vaadin.scoregoatvaadin.domain.TeamValues;
import com.vaadin.scoregoatvaadin.view.manager.ImageManager;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamMatchView;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class MatchView extends VerticalLayout {
    private TeamMatchView team = new TeamMatchView();
    private Long fixtureId;
    private Label date = new Label();
    private Label time = new Label();
    private Button home = new Button();
    private Button away = new Button();
    private ImageManager imgMng = new ImageManager();
    private String predictions = "";

    public MatchView(Match match, MainView mainView) {
        setSpacing(false);
        setPadding(false);
        setMargin(false);
        fixtureId = match.getFixtureId();

        HorizontalLayout homeTeamLayout = new HorizontalLayout();
        team.setTeamButton(home, match.getHomeTeam());
        homeTeamLayout.add(imgMng.setTeamLogo(match.getHomeLogo()), home);
        team.setTeamLayout(homeTeamLayout);

        HorizontalLayout awayTeamLayout = new HorizontalLayout();
        team.setTeamButton(away, match.getAwayTeam());
        awayTeamLayout.add(away, imgMng.setTeamLogo(match.getAwayLogo()));
        team.setTeamLayout(awayTeamLayout);

        date.setText(match.getDate());
        time.setText(match.getTime());

        setDataTimeElements();

        VerticalLayout centerLayout = new VerticalLayout();
        centerLayout.add(date, time);
        team.setCenterLayout(centerLayout);

        HorizontalLayout maineLayout = new HorizontalLayout();
        maineLayout.add(homeTeamLayout, centerLayout, awayTeamLayout);
        team.setMainLayout(maineLayout);

        add(maineLayout);
        team.setMatchLayout(this);

        home.addClickListener(event -> homeButtonClick(mainView.getMatchList().getMatchList()));
        away.addClickListener(event -> awayButtonClick(mainView.getMatchList().getMatchList()));
    }

    private void setDataTimeElements() {
        team.setData(date);
        team.setTime(time);
    }

    private void homeButtonClick (Map<Long,String> matchList) {
        switch (predictions) {
            case "" -> {
                home.getStyle().set("background", TeamValues. LIGHT_BLUE.getValues());
                predictions = "home";
            }
            case "home" -> {
                home.getStyle().set("background", TeamValues.TEAM_BUTTON.getValues());
                predictions = "";
            }
            case "away" -> {
                home.getStyle().set("background", TeamValues.ORANGE.getValues());
                away.getStyle().set("background", TeamValues.ORANGE.getValues());
                predictions = "draw";
            }
            case "draw" -> {
                home.getStyle().set("background", TeamValues. LIGHT_BLUE.getValues());
                away.getStyle().set("background", TeamValues.TEAM_BUTTON.getValues());
                predictions = "home";
            }
            default -> {
                home.getStyle().set("background", TeamValues.TEAM_BUTTON.getValues());
                away.getStyle().set("background", TeamValues.TEAM_BUTTON.getValues());
                predictions = "";
            }
        }
        matchList.put(fixtureId, predictions);
    }

    private void awayButtonClick (Map<Long,String> matchList) {
        switch (predictions) {
            case "" -> {
                away.getStyle().set("background", TeamValues. LIGHT_BLUE.getValues());
                predictions = "away";
            }
            case "away" -> {
                away.getStyle().set("background", TeamValues.TEAM_BUTTON.getValues());
                predictions = "";
            }
            case "home" -> {
                away.getStyle().set("background", TeamValues.ORANGE.getValues());
                home.getStyle().set("background", TeamValues.ORANGE.getValues());
                predictions = "draw";
            }
            case "draw" -> {
                away.getStyle().set("background", TeamValues.LIGHT_BLUE.getValues());
                home.getStyle().set("background", TeamValues.TEAM_BUTTON.getValues());
                predictions = "away";
            }
            default -> {
                home.getStyle().set("background", TeamValues.TEAM_BUTTON.getValues());
                predictions = "";
            }
        }
        matchList.put(fixtureId, predictions);
    }
}
