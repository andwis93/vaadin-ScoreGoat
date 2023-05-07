package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.Names;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamUserView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserView extends VerticalLayout {
    private Label position = new Label("0");
    private Label userLabel = new Label(Names.USER_LABEL.getValue());
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
        add(
                userLabel,
                logButton,
                yourAccount
        );

        logButton.addClickListener(event -> {
            removeViews();
            mainView.getAccountLayout().remove(mainView.getChangePasswordView());
            logButtonExecute();
        });

        yourAccount.addClickListener(event -> {
            removeViews();
            mainView.getAccountLayout().add(mainView.setAccountView());
            mainView.getAccountView().getName().setValue(mainView.getUser().getName());
            mainView.getAccountView().getEmail().setValue(mainView.getUser().getEmail());

        });
    }

    public void logOutExecute() {
        userLabel.setText(Names.USER_LABEL.getValue());
        logButton.setText(Names.LOG_IN.getValue());
        team.setGeneralBtn(logButton);
        team.setGeneraNotEnableBtn(yourAccount);
        mainView.setUser(null);
    }

    public void logButtonExecute() {
        if (logButton.getText().equals(Names.LOG_IN.getValue())) {
            mainView.setLogInView();
            mainView.getLoginView().logInExecute();
        } else {
            logOutExecute();
        }
    }
    private void removeViews() {
        mainView.getAccountLayout().remove(mainView.getLoginView());
        mainView.getAccountLayout().remove(mainView.getSignUpView());
        mainView.getAccountLayout().remove(mainView.getAccountView());
        mainView.getAccountLayout().remove(mainView.getChangePasswordView());
    }
}
