package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.scoregoatvaadin.domain.*;
import com.vaadin.scoregoatvaadin.facade.ScoreGoatFacade;
import com.vaadin.scoregoatvaadin.view.manager.ElementManager;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@PreserveOnRefresh
@UIScope
@Component
@Route
@PageTitle("ScoreGoat")
public class MainView extends HorizontalLayout {
    @Autowired
    private ScoreGoatFacade facade;
    private final LeftBarView leftBar = new LeftBarView(this);
    private final ToolBarView toolBarView = new ToolBarView(this);
    private final HorizontalLayout matchesLayout = new HorizontalLayout();
    private final VerticalLayout mainContent = new VerticalLayout();
    private LogInView loginView = new LogInView(this);
    private SignUpView signUpView = new SignUpView(this);
    private AccountView accountView = new AccountView(this);
    private ChangePasswordView changePasswordView = new ChangePasswordView(this);
    private final ElementManager elements = new ElementManager();

    private User user;

    public MainView() {
        setSizeFull();
        setSpacing(false);
        add(
                leftBar,
                setMainContent()
        );
    }

    private VerticalLayout setMainContent() {
        VerticalLayout vlToCenter = new VerticalLayout(matchesLayout);
        vlToCenter.setAlignItems(Alignment.CENTER);
        mainContent.add(toolBarView, vlToCenter);
        mainContent.setSizeFull();
        mainContent.setPadding(false);
        mainContent.getStyle().set("background", TeamValues.DARK_GRAY.getValues());
        mainContent.setHeight("350em");
        return mainContent;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LogInView setLogInView(){
     return this.loginView = new LogInView(this);
    }
    public SignUpView setSignUpView(){
       return this.signUpView = new SignUpView(this);
    }
    public AccountView setAccountView(){
      return this.accountView = new AccountView(this);
    }
    public ChangePasswordView setChangePasswordView(){
       return this.changePasswordView = new ChangePasswordView(this);
    }

}