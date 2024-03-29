package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.SerializableBiConsumer;
import com.vaadin.scoregoatvaadin.domain.RankingDto;
import com.vaadin.scoregoatvaadin.domain.TeamValues;
import com.vaadin.scoregoatvaadin.service.LeagueService;
import com.vaadin.scoregoatvaadin.view.manager.ImageManager;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamRankingView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingView {
    private static MainView mainView;
    private LeagueService service = new LeagueService();
    private ImageManager manager = new ImageManager();
    private TeamRankingView team = new TeamRankingView();

    public RankingView(MainView mainView) {
        RankingView.mainView = mainView;
    }

    public VerticalLayout ratingExecution(int leagueId) {
        VerticalLayout vl = new VerticalLayout();
        VerticalLayout imgLayout = new VerticalLayout();
        Image image = manager.setLeagueLogo(service.getList().get(leagueId));
        team.setImgLayout(imgLayout);
        imgLayout.add(image);

        Grid<RankingDto> grid = new Grid<>(RankingDto.class, false);
        grid.setItems(mainView.getFacade().getRankingList(leagueId));
        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        grid.getStyle().set("font-size", TeamValues.PX_10.getValues());

        grid.addColumn(createRankRenderer()).setWidth(TeamValues.EM_12.getValues()).setHeader(" Rank").setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER).setSortable(true).setComparator(RankingDto::getRank);
        grid.addColumn(createUserNameRenderer()).setWidth(TeamValues.EM_12.getValues()).setHeader(" User Name").setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER).setSortable(true).setComparator(RankingDto::getUserName);
        grid.addColumn(createPointsRenderer()).setWidth(TeamValues.EM_12.getValues()).setHeader(" Points").setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER).setSortable(true).setComparator(RankingDto::getPoints);
        grid.addColumn(createCounterRenderer()).setWidth(TeamValues.EM_12.getValues()).setHeader(" 1st Place Counter").setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER).setSortable(true).setComparator(RankingDto::getCounter);
        grid.setSizeUndefined();
        vl.add(imgLayout, grid);
        vl.setSizeFull();
        return vl;
    }

    private static final SerializableBiConsumer<Span, RankingDto> createRankingColumn = (
            span, rankingDto) -> {
        String theme = TeamValues.BLACK.getValues();
        if (mainView.getUser() != null) {
            if (rankingDto.getUserName().equals(mainView.getUser().getName())) {
                theme = TeamValues.LIGHT_BLUE.getValues();
            }
        }
        span.getElement().getStyle().set("color", theme);
        span.getElement().getStyle().set("font-size", TeamValues.PX_18.getValues());
        span.getElement().getStyle().set("font-weight", TeamValues.BOLD.getValues());
        span.setText(rankingDto.getRank());
    };

    private static ComponentRenderer<Span, RankingDto> createRankRenderer() {
        return new ComponentRenderer<>(Span::new, createRankingColumn);
    }

    private static final SerializableBiConsumer<Span, RankingDto> createUserNameColumn = (
            span, graduationDto) -> {
        String theme = TeamValues.BLACK.getValues();
        if (mainView.getUser() != null) {
            if (graduationDto.getUserName().equals(mainView.getUser().getName())) {
                theme = TeamValues.LIGHT_BLUE.getValues();
            }
        }
        span.getElement().getStyle().set("color", theme);
        span.getElement().getStyle().set("font-size", TeamValues.PX_18.getValues());
        span.getElement().getStyle().set("font-weight", TeamValues.NORMAL.getValues());
        span.setText(graduationDto.getUserName());
    };
    private static ComponentRenderer<Span, RankingDto> createUserNameRenderer() {
        return new ComponentRenderer<>(Span::new, createUserNameColumn);
    }

    private static final SerializableBiConsumer<Span, RankingDto> createPointsColumn = (
            span, rankingDto) -> {
        String theme = TeamValues.BLACK.getValues();;
        if (mainView.getUser() != null) {
            if (rankingDto.getUserName().equals(mainView.getUser().getName())) {
                theme = TeamValues.LIGHT_BLUE.getValues();
            }
        }
        span.getElement().getStyle().set("color", theme);
        span.getElement().getStyle().set("font-size", TeamValues.PX_18.getValues());
        span.getElement().getStyle().set("font-weight", TeamValues.NORMAL.getValues());
        span.setText(rankingDto.getPoints());
    };

    private static ComponentRenderer<Span, RankingDto> createPointsRenderer() {
        return new ComponentRenderer<>(Span::new, createPointsColumn);
    }

    private static final SerializableBiConsumer<Span, RankingDto> createCounterColumn = (
            span, rankingDto) -> {
        String theme = TeamValues.BLACK.getValues();;
        if (mainView.getUser() != null) {
            if (rankingDto.getUserName().equals(mainView.getUser().getName())) {
                theme = TeamValues.LIGHT_BLUE.getValues();
            }
        }
        span.getElement().getStyle().set("color", theme);
        span.getElement().getStyle().set("font-size", TeamValues.PX_18.getValues());
        span.getElement().getStyle().set("font-weight", TeamValues.NORMAL.getValues());
        span.setText(Integer.toString(rankingDto.getCounter()));
    };

    private static ComponentRenderer<Span, RankingDto> createCounterRenderer() {
        return new ComponentRenderer<>(Span::new, createCounterColumn);
    }
}
