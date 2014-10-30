package com.nacion.android.nacioncostarica.views.home.listeners;

import android.view.View;

import com.nacion.android.nacioncostarica.views.main.MainPresenter;

/**
 * Created by Gustavo Matarrita on 20/10/2014.
 */
public class MenuTextViewOnClickListener implements View.OnClickListener{
    private MainPresenter presenter;
    private int boardId;

    public MenuTextViewOnClickListener(MainPresenter presenter, int boardId){
        this.presenter = presenter;
        this.boardId = boardId;
    }

    @Override
    public void onClick(View view) {
        presenter.goToSectionFromAdapter(boardId);
    }
}