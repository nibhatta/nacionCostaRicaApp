package com.nacion.android.nacioncostarica.main;

import com.nacion.android.nacioncostarica.models.Site;

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
}
