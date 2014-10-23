package com.nacion.android.nacioncostarica.main;

import com.nacion.android.nacioncostarica.models.Site;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class MainPresenterImpl implements MainPresenter{
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
}
