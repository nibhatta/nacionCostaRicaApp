package com.nacion.android.nacioncostarica.views.home.adapters;

import android.content.Context;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;
import com.nacion.android.nacioncostarica.views.main.MainView;

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

    @Override
    public void startVideoActivity() {
        mainView.showVideoActivityFromViewHolder();
    }

    @Override
    public Fonts getFonts() {
        return mainView.getFontsFromChildrenViews();
    }

    @Override
    public Context getContextFromMainActivity() {
        return mainView.getContext();
    }
}
