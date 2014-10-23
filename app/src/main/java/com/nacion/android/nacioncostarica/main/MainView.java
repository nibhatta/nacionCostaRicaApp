package com.nacion.android.nacioncostarica.main;

import android.widget.ListView;

import com.nacion.android.nacioncostarica.fonts.Fonts;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public interface MainView {
    void showRightSubMenu();
    void goToLeftSubMenu();
    void backLeftMenu();
    void addItemToMainMenu(int position);
    void removeItemFromMainMenu(int position);
    void removeItemFromMainMenu(String name);

    void updateViewFromModel();
    void showContentActivityFromViewHolder(String section, int articleId);
    void showVideoActivityFromViewHolder();
    Fonts getFontsFromChildrenViews();
}
