package com.nacion.android.nacioncostarica.views.content.listeners;

import android.view.View;

import com.nacion.android.nacioncostarica.views.content.ContentPresenter;
import com.nacion.android.nacioncostarica.views.main.MainPresenter;

/**
 * Created by Gustavo Matarrita on 20/10/2014.
 */
public class ContentSubMenuOnClickListener implements View.OnClickListener{
    private ContentPresenter presenter;

    public ContentSubMenuOnClickListener(ContentPresenter argPresenter){
        presenter = argPresenter;
    }

    @Override
    public void onClick(View view) {
        presenter.goToLeftSubMenuFromMenuOnTouchListener();
    }
}
