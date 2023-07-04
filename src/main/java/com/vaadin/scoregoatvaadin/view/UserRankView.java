package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.service.RankingService;
import com.vaadin.scoregoatvaadin.service.UserRankingService;
import com.vaadin.scoregoatvaadin.view.manager.EmojiManager;
import com.vaadin.scoregoatvaadin.view.manager.ImageManager;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamUserRankView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRankView extends HorizontalLayout {
    private RankingService rankingService;
    private UserRankingService userRankingService;
    private MainView mainView;
    private Image img;
    private NativeLabel place = new NativeLabel();
    private NativeLabel points = new NativeLabel();
    private NativeLabel placeTitle = new NativeLabel("Position  :");
    private NativeLabel pointsTitle = new NativeLabel("Points : ");
    private Button ranking = new Button("Ranking");
    private TeamUserRankView team = new TeamUserRankView();
    private ImageManager imageManager = new ImageManager();
    private EmojiManager emojiManager = new EmojiManager();

    public UserRankView(MainView mainView) {
        this.mainView = mainView;
        this.rankingService = new RankingService(mainView);
        setLabels();
        add(
                setUserRankLayout()
        );
        team.setRankingButton(ranking);
        ranking.addClickListener(event -> {
            rankingService.ratingButtonClick(mainView.getLeagueId(), mainView.getDoubleLayout());
        });
    }

    public HorizontalLayout setUserRankLayout() {
        setUserRankingService();
        setRank(userRankingService);
        HorizontalLayout hl = new HorizontalLayout();

        HorizontalLayout hlImg = new HorizontalLayout(img);
        team.setImgLayout(hlImg);

        HorizontalLayout hlPlace = new HorizontalLayout(placeTitle, place);
        HorizontalLayout hlPoints = new HorizontalLayout(pointsTitle, points);

        hlPlace.setAlignItems(Alignment.CENTER);
        hlPoints.setAlignItems(Alignment.CENTER);

        VerticalLayout vlPlace = new VerticalLayout(hlPlace, hlPoints);
        team.setPlaceLayout(vlPlace);

        VerticalLayout vlRanking = new VerticalLayout(ranking);
        team.setRankingLayout(vlRanking);

        hl.add(hlImg, vlPlace, vlRanking);
        hl.setAlignItems(Alignment.CENTER);
        hl.setVerticalComponentAlignment(Alignment.CENTER);
        return hl;
    }

    private void setUserRankingService() {
        this.userRankingService = new UserRankingService(mainView);
    }

    private void setRank(UserRankingService service) {
        img = imageManager.setIcon(emojiManager.getEmojiList().get(service.getUserRankDto().getRankingDto().getStatus()));
        place.add(service.getUserRankDto().getRankingDto().getRank() + " / " + service.getUserRankDto().getRankingSize());
        points.add(service.getUserRankDto().getRankingDto().getPoints());
    }

    private void setLabels() {
        team.setUserRankLabel(place);
        team.setUserRankLabel(points);
        team.setTitleRankLabel(placeTitle);
        team.setTitleRankLabel(pointsTitle);
    }
}
