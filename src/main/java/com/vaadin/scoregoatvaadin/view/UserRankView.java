package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.Icon;
import com.vaadin.scoregoatvaadin.service.RankingService;
import com.vaadin.scoregoatvaadin.view.manager.ImageManager;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamUserRankView;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRankView extends HorizontalLayout {
    private RankingService rankingService;
    private Image img;
    private NativeLabel place = new NativeLabel("187687767");
    private NativeLabel points = new NativeLabel("234537834569");
    private NativeLabel placeTitle = new NativeLabel("Place");
    private NativeLabel pointsTitle = new NativeLabel("Points");
    private Button ranking = new Button("Ranking");
    private TeamUserRankView team = new TeamUserRankView();
    private ImageManager imageManager = new ImageManager();

    public UserRankView(MainView mainView) {
        img = imageManager.setIcon(Icon.CARTMAN.getIcon());
        setLabels();
        add(
                setUserRankLayout()
        );
        this.rankingService = new RankingService(mainView);
        team.setRankingButton(ranking);
        ranking.addClickListener(event -> {
            rankingService.ratingButtonClick(mainView.getLeagueId(), mainView.getDoubleLayout());
        });
    }

    public VerticalLayout setUserRankLayout() {
        VerticalLayout vl = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();

        HorizontalLayout hlImg = new HorizontalLayout(img);
        team.setImgLayout(hlImg);

        VerticalLayout vlPlace = new VerticalLayout(placeTitle, place, pointsTitle, points);
        team.setPlaceLayout(vlPlace);

        HorizontalLayout hlRanking = new HorizontalLayout(ranking);
        team.setRankingLayout(hlRanking);

        hl.add(hlImg, vlPlace, hlRanking);
        vl.add(hl);
        team.setMainLayout(vl);
        return vl;
    }
    public void setLabels() {
        team.setUserRankLabel(place);
        team.setUserRankLabel(points);
        team.setTitleRankLabel(placeTitle);
        team.setTitleRankLabel(pointsTitle);

    }
}
