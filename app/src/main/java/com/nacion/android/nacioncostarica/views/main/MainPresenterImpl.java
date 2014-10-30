package com.nacion.android.nacioncostarica.views.main;

import android.content.Context;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;
import com.nacion.android.nacioncostarica.models.Article;
import com.nacion.android.nacioncostarica.models.ArticleContentItemList;
import com.nacion.android.nacioncostarica.models.Site;
import com.nacion.android.nacioncostarica.views.content.ContentView;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private Site site;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void setSite(Site argSite) {
        site = argSite;
    }

    @Override
    public void updateView() {
        mainView.updateViewFromModel();
    }

    @Override
    public void goToLeftSubMenuFromMenuOnTouchListener() {
        mainView.goToLeftSubMenu();
    }

    @Override
    public void backLeftMenuFromMenuOnClickListener() {
        mainView.backLeftMenu();
    }

    @Override
    public void addItemToMainMenuFromSubMenuView(int position){
        mainView.addItemToMainMenu(position);
    }

    @Override
    public void removeItemFromMainMenuSubMenuView(int position){
        mainView.removeItemFromMainMenu(position);
    }

    @Override
    public void removeItemFromMainMenuSubMenuView(String name){
        mainView.removeItemFromMainMenu(name);
    }

    @Override
    public void goToSectionFromAdapter(int boardId) {
        mainView.goToSection(boardId);
    }

    @Override
    public void addMenuToNotificationsFromMenuView(String name) {
        mainView.addMenuToNotification(name);
    }

    @Override
    public void removeMenuFromNotificationsFromMenuView(String name) {
        mainView.removeMenuFromNotification(name);
    }

    @Override
    public Context getContextFromMainActivity() {
        return mainView.getContext();
    }
}
