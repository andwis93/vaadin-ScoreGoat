package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.LeagueButton;
import com.vaadin.scoregoatvaadin.domain.TeamValues;
import com.vaadin.scoregoatvaadin.service.LeagueButtonService;
import com.vaadin.scoregoatvaadin.service.MatchService;
import com.vaadin.scoregoatvaadin.view.manager.ImageManager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeftBarView extends HorizontalLayout{
    private final MainView mainView;
    private ImageManager image = new ImageManager();
    private MatchService matchService;
    private LeagueButtonService service = LeagueButtonService.getInstance();

    public LeftBarView(MainView mainView) {
        this.mainView = mainView;
        this.matchService = new MatchService(mainView);
        add(
                setLeaguesElements()
        );
        for (LeagueButton button:service.getList()) {
            button.getButton().addClickListener(event -> {
            leagueButtonClick(button.getId(), mainView.getMatchesLayout());
             });
        }

    }

    public HorizontalLayout setLeaguesElements() {
        VerticalLayout vl = new VerticalLayout();
                for (LeagueButton button:service.getList()) {
                    vl.add(setButton(button.getButton(), button.getLogo()));
        }
        vl.setAlignItems(FlexComponent.Alignment.CENTER);
        HorizontalLayout hl = new HorizontalLayout(vl);
        hl.getStyle().set("background", TeamValues.MAIN_COLOR.getValues());
        hl.setHeight("350em");
        return hl;
    }

    public void leagueButtonClick(int leagueId, HorizontalLayout hl) {
        VerticalLayout matchLayout;
        try {
            matchLayout = matchService.createLayout(leagueId);
        } catch (Exception ex) {
            matchLayout = new VerticalLayout(
                    new Label("No matches for the next 10 days for this league"));
        }
        hl.removeAll();
        hl.add(matchLayout);
    }

    public VerticalLayout setButton(Button button, String leagueLogo) {
        VerticalLayout vl = new VerticalLayout(button);
        setLeagueButtons(button);
        button.setIcon(image.setImage(leagueLogo));
        button.getStyle().set("cursor", TeamValues.POINTER.getValues());
        return vl;
    }

    public void setLeagueButtons(Button button){
        button.setWidth(TeamValues.EM_6.getValues());
        button.setHeight(TeamValues.EM_7.getValues());
        button.getStyle().set("background", TeamValues.WHITE.getValues());
    }
}
