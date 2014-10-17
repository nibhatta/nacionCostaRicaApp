package com.nacion.android.nacioncostarica.main;

import android.widget.ListView;

import com.nacion.android.nacioncostarica.fonts.Fonts;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public interface MainView {
    void showRightSubMenu();
    void showLeftSubMenu();
    void updateViewFromModel();
    void showContentActivityFromViewHolder(String argSection, int argArticleId);
    void showVideoActivityFromViewHolder();
    Fonts getFontsFromChildrenViews();
}
