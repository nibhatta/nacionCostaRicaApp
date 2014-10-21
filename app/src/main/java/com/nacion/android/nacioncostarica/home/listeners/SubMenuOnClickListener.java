package com.nacion.android.nacioncostarica.home.listeners;

import android.util.Log;
import android.view.View;

import com.nacion.android.nacioncostarica.main.MainPresenter;

/**
 * Created by Gustavo Matarrita on 20/10/2014.
 */
public class SubMenuOnClickListener implements View.OnClickListener{
    private MainPresenter presenter;

    public SubMenuOnClickListener(MainPresenter argPresenter){
        presenter = argPresenter;
    }

    @Override
    public void onClick(View view) {
        Log.d(SubMenuOnClickListener.class.getName(), "=====> OnClick");
        presenter.goToLeftSubMenuFromMenuOnTouchListener();
    }
}
