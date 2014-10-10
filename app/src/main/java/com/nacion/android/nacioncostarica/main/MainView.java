package com.nacion.android.nacioncostarica.main;

import android.widget.ListView;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public interface MainView {
    void showRightDrawLayout();
    void showLeftDrawLayout();
    void updateViewFromModel();
    void showContentActivityFromViewHolder(String argSection, int argArticleId);
}
