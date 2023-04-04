package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.Match;
import com.vaadin.scoregoatvaadin.domain.TeamValues;
import com.vaadin.scoregoatvaadin.view.manager.ImageManager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchView extends VerticalLayout {
    private Label date = new Label();
    private Label time = new Label();
    private Label status = new Label();
    private Button home = new Button();
    private Button away = new Button();
    private ImageManager imgMng = new ImageManager();
    private char predictions = ' ';

    public MatchView(Match match) {
        setSpacing(false);
        HorizontalLayout maineLayout = new HorizontalLayout();
        HorizontalLayout homeTeamLayout = new HorizontalLayout();
        VerticalLayout centerLayout = new VerticalLayout();
        HorizontalLayout awayTeamLayout = new HorizontalLayout();

        setTeamButton(home, match.getHomeTeam());
        homeTeamLayout.add(imgMng.setTeamLogo(match.getHomeLogo()), home);
        homeTeamLayout.setAlignItems(Alignment.CENTER);
        VerticalLayout homeTeamVL = new VerticalLayout(homeTeamLayout);
        homeTeamVL.setWidth(TeamValues.EM_38.getValues());
        homeTeamVL.setAlignItems(Alignment.START);

        setTeamButton(away, match.getAwayTeam());
        awayTeamLayout.add(away, imgMng.setTeamLogo(match.getAwayLogo()));
        awayTeamLayout.setAlignItems(Alignment.CENTER);
        VerticalLayout awayTeamVL = new VerticalLayout(awayTeamLayout);
        awayTeamVL.setWidth(TeamValues.EM_38.getValues());
        awayTeamVL.setAlignItems(Alignment.END);

        date.setText(match.getDate());
        time.setText(match.getTime());
        status.setText(match.getStatus());

        setDataTimeElements();

        centerLayout.add(date, time, status);
        centerLayout.setAlignItems(Alignment.CENTER);
        centerLayout.setWidth(TeamValues.EM_19.getValues());
        centerLayout.setHeight(TeamValues.EM_8.getValues());
        centerLayout.getStyle().set("background", TeamValues.MAIN_COLOR.getValues());
        centerLayout.setSpacing(false);

        maineLayout.add(homeTeamVL, centerLayout, awayTeamVL);
        maineLayout.setAlignItems(Alignment.CENTER);
        maineLayout.getStyle().set("background", TeamValues.WHITE.getValues());

        add(maineLayout);
        getStyle().set("background", TeamValues.DARK_GRAY.getValues());

        home.addClickListener(event -> homeButtonClick());
        away.addClickListener(event -> awayButtonClick());
    }

    private void setTeamButton(Button button, String team){
        button.setText(team);
        button.setWidth(TeamValues.EM_12.getValues());
        button.setHeight(TeamValues.EM_3.getValues());
        button.getStyle().set("color", TeamValues.BLACK.getValues());
        button.getStyle().set("font-size", TeamValues.PX_36.getValues());
        button.getStyle().set("font-weight", TeamValues.BOLD.getValues());
        button.getStyle().set("background", TeamValues.WHITE.getValues());
    }

    private void setDataTimeElements() {
        date.getElement().getStyle().set("color", TeamValues.WHITE.getValues());
        date.getElement().getStyle().set("font-size", TeamValues.PX_18.getValues());

        time.getElement().getStyle().set("color", TeamValues.WHITE.getValues());
        time.getElement().getStyle().set("font-size", TeamValues.PX_24.getValues());
        time.getElement().getStyle().set("font-weight", TeamValues.BOLD.getValues());

        status.getElement().getStyle().set("color", TeamValues.ORANGE.getValues());
        status.getElement().getStyle().set("font-size", TeamValues.PX_14.getValues());
    }

    private void homeButtonClick () {
        switch (predictions) {
            case ' ' -> {
                home.getStyle().set("background", TeamValues.LIGHT_BLUE.getValues());
                predictions = 'H';
            }
            case 'H' -> {
                home.getStyle().set("background", TeamValues.WHITE.getValues());
                predictions = ' ';
            }
            case 'A' -> {
                home.getStyle().set("background", TeamValues.ORANGE.getValues());
                away.getStyle().set("background", TeamValues.ORANGE.getValues());
                predictions = 'D';
            }
            case 'D' -> {
                home.getStyle().set("background", TeamValues.LIGHT_BLUE.getValues());
                away.getStyle().set("background", TeamValues.WHITE.getValues());
                predictions = 'H';
            }
            default -> {
                home.getStyle().set("background", TeamValues.WHITE.getValues());
                away.getStyle().set("background", TeamValues.WHITE.getValues());

                predictions = ' ';
            }
        }
    }

    private void awayButtonClick () {
        switch (predictions) {
            case ' ' -> {
                away.getStyle().set("background", TeamValues.LIGHT_BLUE.getValues());
                predictions = 'A';
            }
            case 'A' -> {
                away.getStyle().set("background", TeamValues.WHITE.getValues());
                predictions = ' ';
            }
            case 'H' -> {
                away.getStyle().set("background", TeamValues.ORANGE.getValues());
                home.getStyle().set("background", TeamValues.ORANGE.getValues());
                predictions = 'D';
            }
            case 'D' -> {
                away.getStyle().set("background", TeamValues.LIGHT_BLUE.getValues());
                home.getStyle().set("background", TeamValues.WHITE.getValues());
                predictions = 'A';
            }
            default -> {
                home.getStyle().set("background", TeamValues.WHITE.getValues());
                away.getStyle().set("background", TeamValues.WHITE.getValues());
                predictions = ' ';
            }
        }
    }
}
