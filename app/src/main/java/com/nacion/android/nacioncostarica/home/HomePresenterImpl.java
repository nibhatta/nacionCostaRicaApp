package com.nacion.android.nacioncostarica.home;

import com.nacion.android.nacioncostarica.main.MainView;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomePresenterImpl implements HomePresenter{
    private MainView mainView;

    public HomePresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }
}
