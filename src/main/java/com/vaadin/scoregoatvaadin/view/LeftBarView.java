package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
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
    private ImageManager image = new ImageManager();
    private DoubleLayoutService doubleLayoutService;
    private TeamLeftBarView team = new TeamLeftBarView();
    private LeagueButtonService service = LeagueButtonService.getInstance();

    public LeftBarView(MainView mainView) {
        this.mainView = mainView;
        this.doubleLayoutService = new DoubleLayoutService(mainView);
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
        VerticalLayout vl = new VerticalLayout();
        for (LeagueButton button:service.getList()) {
            vl.add(setButton(button.getButton(), button.getLogo()));
        }
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        HorizontalLayout hl = new HorizontalLayout(vl);
        team.setLayoutH(hl);
        return hl;
    }

    public void leagueButtonClick(int leagueId, DoubleLayout doubleLayout) {
        doubleLayout.removeAll();
        doubleLayout.setSpacing(false);
        doubleLayout.add(doubleLayoutService.setLeftLayout(leagueId), doubleLayoutService.setRightLayout(leagueId));
    }

    private VerticalLayout setButton(Button button, String leagueLogo) {
        VerticalLayout vl = new VerticalLayout(button);
        team.setLeagueBtn(button);
        button.setIcon(image.setImage(leagueLogo));
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
        return vl;
    }
}
