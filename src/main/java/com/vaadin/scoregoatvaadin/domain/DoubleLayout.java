package com.vaadin.scoregoatvaadin.domain;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.scoregoatvaadin.view.MainView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoubleLayout extends HorizontalLayout {
    private VerticalLayout left;
    private Scroller right;
    private MainView mainView;

    public DoubleLayout(MainView mainView) {
        this.mainView = mainView;
        this.left = new VerticalLayout();
        this.right = new Scroller();
        this.left.setSizeUndefined();
        this.right.setSizeUndefined();
    }
}
