package com.nacion.android.nacioncostarica.views.main;

import android.content.Context;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;

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
    void goToSection(int boardId);
    Context getContext();
    void addMenuToNotification(String name);
    void removeMenuFromNotification(String name);
    Fonts getFontsFromChildrenViews();
}
