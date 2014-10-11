package com.nacion.android.nacioncostarica;

import android.app.Application;

import com.nacion.android.nacioncostarica.models.Site;

/**
 * Created by Gustavo Matarrita on 10/10/2014.
 */
public class Globals extends Application{
    private Site site;

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
