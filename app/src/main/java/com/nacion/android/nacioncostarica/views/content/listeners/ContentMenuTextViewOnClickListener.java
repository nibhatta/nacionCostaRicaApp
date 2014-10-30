package com.nacion.android.nacioncostarica.views.content.listeners;

import android.view.View;

import com.nacion.android.nacioncostarica.views.content.ContentPresenter;
import com.nacion.android.nacioncostarica.views.main.MainPresenter;

/**
 * Created by Gustavo Matarrita on 20/10/2014.
 */
public class ContentMenuTextViewOnClickListener implements View.OnClickListener{
    private ContentPresenter presenter;
    private int boardId;

    public ContentMenuTextViewOnClickListener(ContentPresenter presenter, int boardId){
        this.presenter = presenter;
        this.boardId = boardId;
    }

    @Override
    public void onClick(View view) {
        presenter.goToSectionFromAdapter(boardId);
    }
}