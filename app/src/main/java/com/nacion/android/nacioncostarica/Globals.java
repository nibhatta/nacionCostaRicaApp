package com.nacion.android.nacioncostarica;

import android.app.Application;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;
import com.nacion.android.nacioncostarica.models.Site;
import com.nacion.android.nacioncostarica.views.main.MainPresenter;

/**
 * Created by Gustavo Matarrita on 10/10/2014.
 */
public class Globals extends Application{
    private Site site;
    private Fonts fonts;
    private MainPresenter mainPresenter;

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Fonts getFonts() {
        return fonts;
    }

    public void setFonts(Fonts fonts) {
        this.fonts = fonts;
    }

    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }
}
