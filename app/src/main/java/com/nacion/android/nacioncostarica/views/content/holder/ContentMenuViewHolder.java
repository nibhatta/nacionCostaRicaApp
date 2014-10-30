package com.nacion.android.nacioncostarica.views.content.holder;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.models.Menu;
import com.nacion.android.nacioncostarica.views.base.holders.ViewHolderBase;
import com.nacion.android.nacioncostarica.views.content.ContentPresenter;
import com.nacion.android.nacioncostarica.views.content.adapters.ContentMenuListAdapter;
import com.nacion.android.nacioncostarica.views.content.listeners.ContentMenuTextViewOnClickListener;
import com.nacion.android.nacioncostarica.views.home.adapters.MenuListAdapter;
import com.nacion.android.nacioncostarica.views.home.listeners.MenuTextViewOnClickListener;
import com.nacion.android.nacioncostarica.views.main.MainPresenter;

/**
 * Created by Gustavo Matarrita on 15/10/2014.
 */
public class ContentMenuViewHolder extends ViewHolderBase{
    private final static int START_POSITION = 0;
    private ImageView goToImageView;
    private ImageView deleteImageView;
    private ToggleButton notificationToggleButton;
    private TextView title;
    private ContentPresenter presenter;

    public ContentMenuViewHolder(ContentPresenter argPresenter){
        presenter = argPresenter;
    }

    public void setReferencesForHeaderView(View argView){
        title = (TextView)argView.findViewById(R.id.headerTitleTextView);
    }

    public void setValuesForHeaderView(Menu argItem){
        title.setText(argItem.getName());
    }

    public void setReferencesForSubMenuView(View argView){
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

    public void setReferencesForMenuView(View argView){
        deleteImageView = (ImageView)argView.findViewById(R.id.deleteImageView);
        notificationToggleButton = (ToggleButton)argView.findViewById(R.id.notificationToggleButton);
        title = (TextView)argView.findViewById(R.id.menuIdTextView);
    }

    public void setValuesForMenuView(final int position, final ContentMenuListAdapter listAdapter, final Menu menu){
        title.setText(menu.getName());
        ContentMenuTextViewOnClickListener listener = new ContentMenuTextViewOnClickListener(presenter, menu.getBoardId());
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

        if(menu.isNotification()) {
            notificationToggleButton.setChecked(true);
        }else{
            notificationToggleButton.setChecked(false);
        }

        notificationToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String name = menu.getName();
                if(isChecked){
                    presenter.addMenuToNotificationsFromMenuView(name);
                }else{
                    presenter.removeMenuFromNotificationsFromMenuView(name);
                }
            }
        });
    }

    private void resetOriginalView(){
        deleteImageView.setVisibility(View.INVISIBLE);
        notificationToggleButton.setVisibility(View.VISIBLE);
        title.setTranslationX(START_POSITION);
    }
}
