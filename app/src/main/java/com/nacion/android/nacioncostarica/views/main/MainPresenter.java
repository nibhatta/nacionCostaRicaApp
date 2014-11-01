package com.nacion.android.nacioncostarica.views.main;

import android.content.Context;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;
import com.nacion.android.nacioncostarica.models.ArticleContentItemList;
import com.nacion.android.nacioncostarica.models.Site;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public interface MainPresenter {
    Site getSite();
    void setSite(Site argSite);
    void updateView();
    void goToLeftSubMenuFromMenuOnTouchListener();
    void backLeftMenuFromMenuOnClickListener();
    void addItemToMainMenuFromSubMenuView(int position);
    void removeItemFromMainMenuSubMenuView(int position);
    void removeItemFromMainMenuSubMenuView(String name);
    void goToSectionFromAdapter(int boardId);
    void addMenuToNotificationsFromMenuView(String name);
    void removeMenuFromNotificationsFromMenuView(String name);
    void reorderMenuFromMainMenu(int origin, int end);
    Context getContextFromMainActivity();
}
