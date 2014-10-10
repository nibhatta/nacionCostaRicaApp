package com.nacion.android.nacioncostarica.home.listAdapter;

import com.nacion.android.nacioncostarica.main.MainView;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomeListForTabletPresenterImpl implements HomeListPresenter{
    private MainView mainView;

    public HomeListForTabletPresenterImpl(MainView argMainView) {
        this.mainView = mainView;
    }

    @Override
    public void startContextActivity(String argSectionTitle, int argArticleId) {
        mainView.showContentActivityFromViewHolder(argSectionTitle, argArticleId);
    }
}
