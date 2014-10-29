package com.nacion.android.nacioncostarica.views.content;

import android.content.Context;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;

/**
 * Created by Gustavo Matarrita on 10/10/2014.
 */
public interface ContentView {
    Fonts getFontsFromChildrenViews();
    Context getContext();
}
