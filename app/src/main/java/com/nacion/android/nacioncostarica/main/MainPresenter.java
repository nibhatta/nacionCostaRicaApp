package com.nacion.android.nacioncostarica.main;

import com.nacion.android.nacioncostarica.models.Site;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public interface MainPresenter {
    Site getSite();
    void setSite(Site argSite);
    void run();
}
