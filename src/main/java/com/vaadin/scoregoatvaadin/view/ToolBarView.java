package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.domain.Names;
import com.vaadin.scoregoatvaadin.domain.TeamValues;
import com.vaadin.scoregoatvaadin.view.manager.ElementManager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToolBarView extends HorizontalLayout {
    private Label position = new Label("0");
    private Label userLabel = new Label(Names.USER_LABEL.getValue());
    private Button logButton = new Button(Names.LOG_IN.getValue());
    private Button yourAccount = new Button(Names.YOUR_ACCOUNT.getValue());
    private final MainView mainView;
    private ElementManager elements = new ElementManager();

    public ToolBarView(MainView mainView) {
        this.mainView = mainView;
        setWidthFull();
        add(
                setToolbar()
        );
        logButton.addClickListener(event -> {
            mainView.remove(mainView.getSignUpView());
            mainView.remove(mainView.getSignUpView());
            mainView.remove(mainView.getAccountView());
            mainView.remove(mainView.getChangePasswordView());
            logButtonExecute();
        });
        yourAccount.addClickListener(event ->mainView.add(mainView.setAccountView()));
    }

    private HorizontalLayout setToolbar() {
        HorizontalLayout main = new HorizontalLayout();
        HorizontalLayout startComponent = new HorizontalLayout();
        HorizontalLayout centerComponent = new HorizontalLayout(position);
        HorizontalLayout endComponent = new HorizontalLayout(setUserAccountElements());
        startComponent.setSizeFull();
        centerComponent.setSizeUndefined();
        centerComponent.setAlignItems(FlexComponent.Alignment.CENTER);
        endComponent.setWidthFull();
        main.setWidthFull();
        main.add(startComponent, centerComponent, endComponent);
        main.getStyle().set("background", TeamValues.LIGHT_GREY.getValues());
        elements.setPositionLabel(position);
        return main;
    }
    private HorizontalLayout setUserNameAndLogButton() {
        HorizontalLayout hl = new HorizontalLayout(userLabel, logButton);
        hl.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, userLabel, logButton);
        elements.setGeneralButton(logButton);
        elements.setUserLabel(userLabel);
        return hl;
    }

    private VerticalLayout setUserAccountElements() {
        VerticalLayout vl = new VerticalLayout(setUserNameAndLogButton(), yourAccount);
        vl.setAlignItems(FlexComponent.Alignment.END);
        elements.setGeneralButtonNotEnable(yourAccount);
        return vl;
    }

    public void logOutExecute() {
        userLabel.setText(Names.USER_LABEL.getValue());
        logButton.setText(Names.LOG_IN.getValue());
        elements.setGeneralButton(logButton);
        elements.setGeneralButtonNotEnable(yourAccount);
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
}
