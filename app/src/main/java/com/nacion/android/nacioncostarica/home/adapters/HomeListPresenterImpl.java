package com.nacion.android.nacioncostarica.home.adapters;

import com.nacion.android.nacioncostarica.main.MainView;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomeListPresenterImpl implements HomeListPresenter{
    private MainView mainView;

    public HomeListPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void startContextActivity(String argSectionTitle, int argArticleId) {
        mainView.showContentActivityFromViewHolder(argSectionTitle, argArticleId);
    }

    @Override
    public void startVideoActivity() {
        mainView.showVideoActivityFromViewHolder();
    }
}
