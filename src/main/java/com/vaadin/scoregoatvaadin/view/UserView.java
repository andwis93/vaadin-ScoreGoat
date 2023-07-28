package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.Names;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamUserView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserView extends VerticalLayout {
    private NativeLabel userLabel = new NativeLabel(Names.USER_LABEL.getValue());
    private Button logButton = new Button(Names.LOG_IN.getValue());
    private Button yourAccount = new Button(Names.YOUR_ACCOUNT.getValue());
    private final MainView mainView;
    private TeamUserView team = new TeamUserView();

    public UserView(MainView mainView) {
        this.mainView = mainView;
        team.setMainLayout(this);
        team.setGeneralBtn(logButton);
        team.setUserLabel(userLabel);
        team.setGeneraNotEnableBtn(yourAccount);
        setAlignItems(Alignment.CENTER);
        setSizeUndefined();
        yourAccount.setEnabled(false);
        add(
                userLabel,
                logButton,
                yourAccount
        );

        logButton.addClickListener(event -> {
            removeViews();
            mainView.getLeftBar().getAccountLayout().remove(mainView.getChangePasswordView());
            logButtonExecute();
        });

        yourAccount.addClickListener(event -> {
            removeViews();
            mainView.getLeftBar().getAccountLayout().add(mainView.setAccountView());
            mainView.getAccountView().getName().setValue(mainView.getUser().getName());
            mainView.getAccountView().getEmail().setValue(mainView.getUser().getEmail());

        });
    }

    public void logOutExecute() {
        userLabel.setText(Names.USER_LABEL.getValue());
        logButton.setText(Names.LOG_IN.getValue());
        team.setGeneralBtn(logButton);
        team.setGeneraNotEnableBtn(yourAccount);
        yourAccount.setEnabled(false);
        mainView.setUser(null);
        mainView.getDoubleLayout().removeAll();
        mainView.getMatchList().getMatchList().clear();
        mainView.getMatchList().setLeagueId(0);
    }

    public void logButtonExecute() {
        if (logButton.getText().equals(Names.LOG_IN.getValue())) {
            mainView.setLogInView();
            mainView.getLoginView().logInExecute();
            mainView.getLeftBar().add(mainView.getLeftBar().getAccountLayout());
        } else {
            logOutExecute();
        }
    }
    private void removeViews() {
        mainView.getLeftBar().getAccountLayout().remove(mainView.getLoginView());
        mainView.getLeftBar().getAccountLayout().remove(mainView.getSignUpView());
        mainView.getLeftBar().getAccountLayout().remove(mainView.getAccountView());
        mainView.getLeftBar().getAccountLayout().remove(mainView.getChangePasswordView());
    }
}
