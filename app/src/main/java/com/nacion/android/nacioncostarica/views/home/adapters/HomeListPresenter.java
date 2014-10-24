package com.nacion.android.nacioncostarica.views.home.adapters;

import android.content.Context;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public interface HomeListPresenter {
    void startContextActivity(String argSectionTitle, int argArticleId);
    void startVideoActivity();
    Fonts getFonts();
    Context getContextFromMainActivity();
}
