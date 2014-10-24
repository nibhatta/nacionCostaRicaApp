package com.nacion.android.nacioncostarica.views.home.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.views.base.holders.ViewHolderBase;
import com.nacion.android.nacioncostarica.views.home.adapters.MenuListAdapter;
import com.nacion.android.nacioncostarica.views.home.listeners.MenuTextViewOnClickListener;
import com.nacion.android.nacioncostarica.main.MainPresenter;
import com.nacion.android.nacioncostarica.models.Menu;

/**
 * Created by Gustavo Matarrita on 15/10/2014.
 */
public class MenuViewHolder extends ViewHolderBase{
    private final static int START_POSITION = 0;

    private ImageView goToImageView;
    private ImageView deleteImageView;
    private ImageView notificationImageView;
    private TextView title;
    private MainPresenter presenter;

    public MenuViewHolder(MainPresenter argPresenter){
        presenter = argPresenter;
    }

    public void setComponentsReferencesForHeaderView(View argView){
        title = (TextView)argView.findViewById(R.id.headerTitleTextView);
    }

    public void setValuesForHeaderView(Menu argItem){
        title.setText(argItem.getName());
    }

    public void setComponentsReferencesForSubMenuView(View argView){
        title = (TextView)argView.findViewById(R.id.subMenuTextView);
        goToImageView = (ImageView)argView.findViewById(R.id.goToImageView);
    }

    public void setValuesForSubMenuView(Menu argItem){
        title.setText(argItem.getName());
        goToImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void setComponentsReferencesForMenuView(View argView){
        deleteImageView = (ImageView)argView.findViewById(R.id.deleteImageView);
        notificationImageView = (ImageView)argView.findViewById(R.id.notificationImageView);
        title = (TextView)argView.findViewById(R.id.menuIdTextView);
    }

    public void setValuesForMenuView(final int position, final MenuListAdapter listAdapter, final Menu menu){
        title.setText(menu.getName());
        MenuTextViewOnClickListener listener = new MenuTextViewOnClickListener(presenter, menu.getBoardId());
        title.setOnClickListener(listener);

        deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOriginalView();
                listAdapter.getMenuList().remove(position);
                listAdapter.notifyDataSetChanged();
                presenter.removeItemFromMainMenuSubMenuView(menu.getName());
            }
        });
    }

    private void resetOriginalView(){
        deleteImageView.setVisibility(View.INVISIBLE);
        notificationImageView.setVisibility(View.VISIBLE);
        title.setTranslationX(START_POSITION);
    }
}
