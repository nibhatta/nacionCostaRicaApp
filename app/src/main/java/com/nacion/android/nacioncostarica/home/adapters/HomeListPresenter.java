package com.nacion.android.nacioncostarica.home.adapters;

import com.nacion.android.nacioncostarica.fonts.Fonts;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public interface HomeListPresenter {
    void startContextActivity(String argSectionTitle, int argArticleId);
    void startVideoActivity();
    Fonts getFonts();
}
