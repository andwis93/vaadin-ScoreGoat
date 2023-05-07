package com.vaadin.scoregoatvaadin.view;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.scoregoatvaadin.domain.*;
import com.vaadin.scoregoatvaadin.facade.ScoreGoatFacade;
import com.vaadin.scoregoatvaadin.view.manager.elements.TeamMainView;
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
    private final TeamMainView team = new TeamMainView();
    private final LeftBarView leftBar = new LeftBarView(this);
    private final HorizontalLayout accountLayout = new HorizontalLayout();
    private final HorizontalLayout toolbar = new HorizontalLayout();

    private final DoubleLayout doubleLayout = new DoubleLayout(this);
    private final VerticalLayout mainContent = new VerticalLayout();
    private LogInView loginView = new LogInView(this);
    private SignUpView signUpView = new SignUpView(this);
    private AccountView accountView = new AccountView(this);
    private PasswordView changePasswordView = new PasswordView(this);

    private final MatchList matchList = new MatchList();
    private User user;

    public MainView() {
        setSizeFull();
        setSpacing(false);
        add(
                leftBar,
                accountLayout,
                setMainContent()
        );
    }

    private VerticalLayout setMainContent() {
        doubleLayout.setMaxHeight(TeamValues.EM_48.getValues());
        mainContent.add(toolbar, doubleLayout);
        mainContent.setAlignItems(Alignment.CENTER);
        mainContent.setMargin(false);
        mainContent.setPadding(false);
        mainContent.setSizeFull();
        team.setMainLayout(mainContent);
        return mainContent;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setLogInView(){
        this.loginView = new LogInView(this);
    }
    public SignUpView setSignUpView(){
       return this.signUpView = new SignUpView(this);
    }
    public AccountView setAccountView(){
      return this.accountView = new AccountView(this);
    }
    public PasswordView setChangePasswordView(){
       return this.changePasswordView = new PasswordView(this);
    }
}