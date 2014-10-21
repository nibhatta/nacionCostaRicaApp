package com.nacion.android.nacioncostarica.home.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.holders.ViewHolderBase;
import com.nacion.android.nacioncostarica.home.adapters.MenuListAdapter;
import com.nacion.android.nacioncostarica.home.adapters.SubMenuListAdapter;
import com.nacion.android.nacioncostarica.main.MainPresenter;
import com.nacion.android.nacioncostarica.models.Menu;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 15/10/2014.
 */
public class SubMenuViewHolder extends ViewHolderBase{
    private final static int START_POSITION = 0;

    private ImageView backImageView;
    private ImageView removeImageView;
    private ImageView addImageView;
    private TextView title;
    private MainPresenter presenter;

    public SubMenuViewHolder(MainPresenter argPresenter){
        presenter = argPresenter;
    }

    public void setComponentsReferencesForHeaderView(View argView){
        backImageView = (ImageView)argView.findViewById(R.id.backToImageView);
        title = (TextView)argView.findViewById(R.id.headerTitleTextView);
    }

    public void setValuesForHeaderView(Menu argItem){
        title.setText(argItem.getName());
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.backLeftMenuFromMenuOnClickListener();
            }
        });
    }


    public void setComponentsReferencesForMenuView(View argView){
        removeImageView = (ImageView)argView.findViewById(R.id.removeImageView);
        addImageView = (ImageView)argView.findViewById(R.id.addImageView);
        title = (TextView)argView.findViewById(R.id.menuIdTextView);
    }

    public void setValuesForMenuView(Menu argItem, final List<Menu> argItems, final int argPosition){
        title.setText(argItem.getName());
        removeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeImageView.setVisibility(View.INVISIBLE);
                addImageView.setVisibility(View.VISIBLE);
                Menu menu = argItems.get(argPosition);
                menu.setMain(true);
            }
        });

        addImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeImageView.setVisibility(View.VISIBLE);
                addImageView.setVisibility(View.INVISIBLE);
                Menu menu = argItems.get(argPosition);
                menu.setMain(false);
            }
        });
    }


}
