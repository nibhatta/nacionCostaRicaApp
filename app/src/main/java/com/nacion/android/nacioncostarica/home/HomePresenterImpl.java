package com.nacion.android.nacioncostarica.home;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomePresenterImpl implements HomePresenter{
    private HomeView homeView;

    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
    }
}
