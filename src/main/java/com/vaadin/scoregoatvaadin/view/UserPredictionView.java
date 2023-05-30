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
        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        grid.getStyle().set("font-size", TeamValues.PX_10.getValues());

        grid.addColumn(createHomeLogo()).setWidth(TeamValues.EM_6.getValues()).setFlexGrow(0);
        grid.addColumn(createHomeTeamRenderer()).setWidth(TeamValues.EM_12.getValues()).setHeader(" HOME").setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER).setSortable(true).setComparator(UserPredictionDto::getHomeTeam);
        grid.addColumn(UserPredictionDto::getHomeGoal).setAutoWidth(true).setFlexGrow(0).setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(createDateTime()).setAutoWidth(true).setHeader("DATE").setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER).setSortable(true).setComparator(UserPredictionDto::getDate);
        grid.addColumn(UserPredictionDto::getAwayGoal).setAutoWidth(true).setFlexGrow(0).setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(createAwayTeamRenderer()).setWidth(TeamValues.EM_12.getValues()).setHeader(" AWAY").setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER).setSortable(true).setComparator(UserPredictionDto::getAwayTeam);
        grid.addColumn(createAwayLogo()).setWidth(TeamValues.EM_5.getValues()).setFlexGrow(0);
        grid.addColumn(createResultRenderer()).setWidth(TeamValues.EM_6.getValues()).setFlexGrow(0).setTextAlign(ColumnTextAlign.CENTER).
                setSortable(true).setComparator(UserPredictionDto::getPoints);
        grid.setSizeFull();
        vl.add(grid);
        return vl;
    }

    private static Renderer<UserPredictionDto> createHomeLogo() {
        return LitRenderer.<UserPredictionDto> of(
                        "<vaadin-vertical-layout style=\"align-items: center;\" >"
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
        int result = userPredictionsDto.getResult();
        String theme;
        String style;
        String size;
        if (result > 0) {
            switch (result) {
                case 1 -> {
                    if (prediction.equals("home")) {
                        theme = TeamValues.GREEN_GOOD_ACCEPT.getValues();
                        style = TeamValues.BOLD.getValues();
                        size = TeamValues.PX_14.getValues();
                    } else {
                        theme = TeamValues.BLACK.getValues();
                        style = TeamValues.NORMAL.getValues();
                        size = TeamValues.PX_12.getValues();
                    }
                }
                case 2 -> {
                    style = TeamValues.NORMAL.getValues();
                    size = TeamValues.PX_12.getValues();
                    if (prediction.equals("home")) {
                        theme = TeamValues.RED_BAD_WARNING.getValues();
                    } else {
                        theme = TeamValues.BLACK.getValues();
                    }
                }
                case 3 -> {
                    style = TeamValues.BOLD.getValues();
                    size = TeamValues.PX_14.getValues();
                    if (prediction.equals("draw")) {
                        theme = TeamValues.GREEN_GOOD_ACCEPT.getValues();
                    } else {
                        if (prediction.equals("home")) {
                            theme = TeamValues.RED_BAD_WARNING.getValues();
                        } else {
                            theme = TeamValues.BLACK.getValues();
                        }
                    }
                }
                default -> {
                    theme = TeamValues.BLACK.getValues();
                    style = TeamValues.NORMAL.getValues();
                    size = TeamValues.PX_12.getValues();
                }
            }
        } else {
            switch (prediction) {
                case "home", "draw" -> {
                    theme = TeamValues.BLUE.getValues();
                    style = TeamValues.BOLD.getValues();
                    size = TeamValues.PX_14.getValues();

                }
                case "away" -> {
                    theme = TeamValues.BLACK.getValues();
                    style = TeamValues.NORMAL.getValues();
                    size = TeamValues.PX_12.getValues();
                }
                default -> {
                    theme = TeamValues.BLACK.getValues();
                    size = TeamValues.PX_12.getValues();
                    style = TeamValues.NORMAL.getValues();
                }
            }
        }
        span.getElement().getStyle().set("color", theme);
        span.getElement().getStyle().set("font-size", size);
        span.getElement().getStyle().set("font-weight", style);
        span.getElement().getStyle().set("theme", "wrap-cell-content");
        span.setText(userPredictionsDto.getHomeTeam());
    };

    private static ComponentRenderer<Span, UserPredictionDto> createHomeTeamRenderer() {
        return new ComponentRenderer<>(Span::new, userPredictionUpdateHomeTeam);
    }

    private static final SerializableBiConsumer<Span, UserPredictionDto> userPredictionUpdateAwayTeam = (
            span, userPredictionsDto) -> {
        String prediction = userPredictionsDto.getPrediction();
        int result = userPredictionsDto.getResult();
        String theme;
        String style;
        String size;
        if (result > 0) {
            switch (result) {
                case 2 -> {
                    if (prediction.equals("away")) {
                        theme = TeamValues.GREEN_GOOD_ACCEPT.getValues();
                        style = TeamValues.BOLD.getValues();
                        size = TeamValues.PX_14.getValues();
                    } else {
                        theme = TeamValues.BLACK.getValues();
                        style = TeamValues.NORMAL.getValues();
                        size = TeamValues.PX_12.getValues();
                    }
                }
                case 1 -> {
                    style = TeamValues.NORMAL.getValues();
                    size = TeamValues.PX_12.getValues();
                    if (prediction.equals("away")) {
                        theme = TeamValues.RED_BAD_WARNING.getValues();
                    } else {
                        theme = TeamValues.BLACK.getValues();
                    }
                }
                case 3 -> {
                    style = TeamValues.BOLD.getValues();
                    size = TeamValues.PX_14.getValues();
                    if (prediction.equals("draw")) {
                        theme = TeamValues.GREEN_GOOD_ACCEPT.getValues();
                    } else {
                        if (prediction.equals("away")) {
                            theme = TeamValues.RED_BAD_WARNING.getValues();
                        } else {
                            theme = TeamValues.BLACK.getValues();
                        }
                    }
                }
                default -> {
                    theme = TeamValues.BLACK.getValues();
                    size = TeamValues.PX_12.getValues();
                    style = TeamValues.NORMAL.getValues();
                }
            }
        } else {
            switch (prediction) {
                case "home" -> {
                        theme = TeamValues.BLACK.getValues();
                        style = TeamValues.NORMAL.getValues();
                        size = TeamValues.PX_12.getValues();
                }
                case "away", "draw" -> {
                    theme = TeamValues.BLUE.getValues();
                    style = TeamValues.BOLD.getValues();
                    size = TeamValues.PX_14.getValues();
                }
                default -> {
                    theme = TeamValues.BLACK.getValues();
                    size = TeamValues.PX_12.getValues();
                    style = TeamValues.NORMAL.getValues();
                }
            }
        }
        span.getElement().getStyle().set("color", theme);
        span.getElement().getStyle().set("font-size", size);
        span.getElement().getStyle().set("font-weight", style);
        span.getElement().getStyle().set("theme", "wrap-cell-content");
        span.setText(userPredictionsDto.getAwayTeam());
    };

    private static ComponentRenderer<Span, UserPredictionDto> createAwayTeamRenderer() {
        return new ComponentRenderer<>(Span::new, userPredictionUpdateAwayTeam);
    }

    private static final SerializableBiConsumer<Span, UserPredictionDto> userPredictionResult = (
            span, userPredictionsDto) -> {
        String theme;
        String sign;
        if (userPredictionsDto.getPoints() > 0) {
            theme = TeamValues.GREEN_GOOD_ACCEPT.getValues();
            sign = "+";
        } else if (userPredictionsDto.getPoints() < 0) {
            theme = TeamValues.RED_BAD_WARNING.getValues();
            sign = "";
        } else {
            theme = TeamValues.BLACK.getValues();
            sign = "";
        }
        span.getElement().getStyle().set("color", theme);
        span.getElement().getStyle().set("font-size", TeamValues.PX_18.getValues());
        span.getElement().getStyle().set("font-weight", TeamValues.BOLD.getValues());
        span.setText(sign + userPredictionsDto.getPoints());
    };

    private static ComponentRenderer<Span, UserPredictionDto> createResultRenderer() {
        return new ComponentRenderer<>(Span::new, userPredictionResult);
    }
}
