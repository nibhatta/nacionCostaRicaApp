package com.nacion.android.nacioncostarica.views.content;

import android.content.Context;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;

/**
 * Created by Gustavo Matarrita on 10/10/2014.
 */
public interface ContentView{
    //void showRightSubMenu();
    void goToLeftSubMenu();
    void backLeftMenu();
    void addItemToMainMenu(int position);
    void removeItemFromMainMenu(int position);
    void removeItemFromMainMenu(String name);
    //void showVideoActivityFromViewHolder();
    void goToSection(int boardId);
    void addMenuToNotification(String name);
    void removeMenuFromNotification(String name);
    Fonts getFontsFromChildrenViews();
    Context getContext();
}
