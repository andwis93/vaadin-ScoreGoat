package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.LeagueButton;
import com.vaadin.scoregoatvaadin.domain.TeamValues;
import com.vaadin.scoregoatvaadin.domain.DoubleLayout;
import com.vaadin.scoregoatvaadin.service.LeagueButtonService;
import com.vaadin.scoregoatvaadin.service.DoubleLayoutService;
import com.vaadin.scoregoatvaadin.view.manager.ImageManager;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamLeftBarView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeftBarView extends HorizontalLayout{
    private final MainView mainView;
    private final UserView userView;
    private EmailVerificationView emailVerificationView;
    private final HorizontalLayout accountLayout = new HorizontalLayout();
    private ImageManager image = new ImageManager();
    private DoubleLayoutService doubleLayoutService;
    private TeamLeftBarView team = new TeamLeftBarView();
    private LeagueButtonService service = LeagueButtonService.getInstance();

    public LeftBarView(MainView mainView) {
        this.mainView = mainView;
        this.userView = new UserView(mainView);
        this.doubleLayoutService = new DoubleLayoutService(mainView);
        this.setSpacing(false);
        add(
                setLeaguesElements()
        );
        for (LeagueButton button:service.getList()) {
            button.getButton().addClickListener(event -> {
                leagueButtonClick(button.getId(), mainView.getDoubleLayout());
            });
        }
    }

    private HorizontalLayout setLeaguesElements() {
        VerticalLayout vl = new VerticalLayout(userView);
        vl.setAlignItems(Alignment.CENTER);
        VerticalLayout vlButtons = new VerticalLayout();
        for (LeagueButton button:service.getList()) {
            vlButtons.add(setButton(button.getButton(), button.getLogo()));
        }
        vlButtons.setAlignItems(Alignment.CENTER);
        vl.add(vlButtons);
        HorizontalLayout hl = new HorizontalLayout(vl);
        team.setLayoutH(hl);
        return hl;
    }

    public void leagueButtonClick(int leagueId, DoubleLayout doubleLayout) {
        mainView.setLeagueId(leagueId);
        doubleLayout.removeAll();
        doubleLayout.setSpacing(false);
        doubleLayout.add(doubleLayoutService.setLeftLayout(leagueId), doubleLayoutService.setRightLayout(mainView));
    }

    private VerticalLayout setButton(Button button, String leagueLogo) {
        VerticalLayout vl = new VerticalLayout(button);
        team.setLeagueBtn(button);
        button.setIcon(image.setLeagueLogo(leagueLogo));
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
        return vl;
    }
}
