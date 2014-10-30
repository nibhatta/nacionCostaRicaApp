package com.nacion.android.nacioncostarica.views.content;

import android.content.Context;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;
import com.nacion.android.nacioncostarica.models.ArticleContentItemList;
import com.nacion.android.nacioncostarica.models.Site;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 10/10/2014.
 */
public interface ContentPresenter {
    Site getSite();
    void setSite(Site argSite);
    void goToLeftSubMenuFromMenuOnTouchListener();
    void backLeftMenuFromMenuOnClickListener();
    void addItemToMainMenuFromSubMenuView(int position);
    void removeItemFromMainMenuSubMenuView(int position);
    void removeItemFromMainMenuSubMenuView(String name);
    void goToSectionFromAdapter(int boardId);
    void addMenuToNotificationsFromMenuView(String name);
    void removeMenuFromNotificationsFromMenuView(String name);
    boolean articleNotExistsFromView(int argArticleId);
    Fonts getFonts();
    Context getContextFromContentActivity();
    List<ArticleContentItemList> getArticleContentFromView(int argArticleId);
}
