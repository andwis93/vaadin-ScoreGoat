package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.function.SerializableBiConsumer;
import com.vaadin.scoregoatvaadin.domain.TeamValues;
import com.vaadin.scoregoatvaadin.domain.UserPredictionDto;
import com.vaadin.scoregoatvaadin.service.LeagueService;
import com.vaadin.scoregoatvaadin.service.PredictionService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPredictionView {
    private PredictionService predictionService;
    private LeagueService leagueService = new LeagueService();
    private MainView mainView;

    public UserPredictionView(MainView mainView) {
        this.mainView = mainView;
    }

    public VerticalLayout predictionExecution(int leagueId) {
        VerticalLayout vl = new VerticalLayout();
        setPredictionService(leagueId);

        Grid<UserPredictionDto> grid = new Grid<>(UserPredictionDto.class, false);
        grid.setItems(predictionService.getPredictions());
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.getStyle().set("font-size", TeamValues.PX_12.getValues());
        grid.getStyle().set("font-weight", TeamValues.BOLD.getValues());

        grid.addColumn(createHomeLogo()).setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(createHomeTeamRenderer()).setAutoWidth(true).setHeader("HOME TEAM").setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER).setSortable(true).setComparator(UserPredictionDto::getHomeTeam);
        grid.addColumn(UserPredictionDto::getHomeGoal).setAutoWidth(true).setFlexGrow(0).setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(createDateTime()).setAutoWidth(true).setHeader("DATE").setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER).setSortable(true).setComparator(UserPredictionDto::getDate);
        grid.addColumn(UserPredictionDto::getAwayGoal).setAutoWidth(true).setFlexGrow(0).setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(createAwayTeamRenderer()).setAutoWidth(true).setHeader("AWAY TEAM").setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER).setSortable(true).setComparator(UserPredictionDto::getAwayTeam);
        grid.addColumn(createAwayLogo()).setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(createResultRenderer()).setFlexGrow(0).setTextAlign(ColumnTextAlign.CENTER).
                setSortable(true).setComparator(UserPredictionDto::getResult);
        grid.setSizeFull();
        vl.add(grid);
        return vl;
    }

    private static Renderer<UserPredictionDto> createHomeLogo() {
        return LitRenderer.<UserPredictionDto> of(
                        "<vaadin-vertical-layout style=\"align-items: center;\" theme=\"spacing\">"
                                + "<vaadin-avatar img=\"${item.homeLogo}\" alt=\"User avatar\"></vaadin-avatar>")
                .withProperty("homeLogo", UserPredictionDto::getHomeLogo);
    }

    private static Renderer<UserPredictionDto> createDateTime() {
        return LitRenderer.<UserPredictionDto> of(
                        "<vaadin-vertical-layout style=\"align-items: center;\" >"
                                + "    <span> ${item.date}</span>"
                                + "    <span> ${item.time}</span>")
                .withProperty("date", UserPredictionDto::getDate)
                .withProperty("time", UserPredictionDto::getTime);
    }

    private static Renderer<UserPredictionDto> createAwayLogo() {
        return LitRenderer.<UserPredictionDto> of(
                        "<vaadin-vertical-layout style=\"align-items: center;\" theme=\"spacing\">"
                                + "<vaadin-avatar img=\"${item.awayLogo}\" alt=\"User avatar\"></vaadin-avatar>")
                .withProperty("awayLogo", UserPredictionDto::getAwayLogo);
    }
    private void setPredictionService(int leagueId) {
        this.predictionService = new PredictionService(mainView, leagueId);
    }

    private static final SerializableBiConsumer<Span, UserPredictionDto> userPredictionUpdateHomeTeam = (
            span, userPredictionsDto) -> {
        String prediction = userPredictionsDto.getPrediction();
        String theme;
        String style;
        switch (prediction) {
            case "home" -> {
                theme = String.format("badge %s", "success");
                style = TeamValues.BOLD.getValues();
            }
            case "away" -> {
                theme = String.format("badge %s", "error");
                style = TeamValues.NORMAL.getValues();
            }
            default -> {
                theme = String.format("badge %s", "contrast");
                style = TeamValues.BOLD.getValues();
            }
        }
        span.getElement().setAttribute("theme", theme);
        span.getElement().getStyle().set("font-size", TeamValues.PX_14.getValues());
        span.getElement().getStyle().set("font-weight", style);
        span.setText(userPredictionsDto.getHomeTeam());
    };

    private static ComponentRenderer<Span, UserPredictionDto> createHomeTeamRenderer() {
        return new ComponentRenderer<>(Span::new, userPredictionUpdateHomeTeam);
    }

    private static final SerializableBiConsumer<Span, UserPredictionDto> userPredictionUpdateAwayTeam = (
            span, userPredictionsDto) -> {
        String prediction = userPredictionsDto.getPrediction();
        String theme;
        String style;
        switch (prediction) {
            case "home" -> {
                theme = String.format("badge %s", "error");
                style = TeamValues.NORMAL.getValues();
            }
            case "away" -> {
                theme = String.format("badge %s", "success");
                style = TeamValues.BOLD.getValues();
            }
            default -> {
                theme = String.format("badge %s", "contrast");
                style = TeamValues.BOLD.getValues();
            }
        }
        span.getElement().setAttribute("theme", theme);
        span.getElement().getStyle().set("font-size", TeamValues.PX_14.getValues());
        span.getElement().getStyle().set("font-weight", style);
        span.setText(userPredictionsDto.getAwayTeam());
    };

    private static ComponentRenderer<Span, UserPredictionDto> createAwayTeamRenderer() {
        return new ComponentRenderer<>(Span::new, userPredictionUpdateAwayTeam);
    }

    private static final SerializableBiConsumer<Span, UserPredictionDto> userPredictionResult = (
            span, userPredictionsDto) -> {
        String theme;
        String sign;
        if (userPredictionsDto.getResult() > 0) {
            theme = String.format("badge %s", "success");
            sign = "+";
        } else if (userPredictionsDto.getResult() < 0) {
            theme = String.format("badge %s", "error");
            sign = "";
        } else {
            theme = String.format("badge %s", "contrast");
            sign = "";
        }
        span.getElement().setAttribute("theme", theme);
        span.getElement().getStyle().set("font-size", TeamValues.PX_24.getValues());
        span.getElement().getStyle().set("font-weight", TeamValues.BOLD.getValues());
        span.setText(sign + userPredictionsDto.getResult());
    };

    private static ComponentRenderer<Span, UserPredictionDto> createResultRenderer() {
        return new ComponentRenderer<>(Span::new, userPredictionResult);
    }
}
