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

    private ImageView backImageView;
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


    public void setComponentsReferencesForMenuView(View view, final int position){
        addImageView = (ImageView)view.findViewById(R.id.addImageView);
        title = (TextView)view.findViewById(R.id.menuIdTextView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int visibilityState = addImageView.getVisibility();
                if(visibilityState == View.INVISIBLE){
                    presenter.addItemToMainMenuFromSubMenuView(position);
                }else{
                    presenter.removeItemFromMainMenuSubMenuView(position);
                }
                addImageView.setVisibility(visibilityState == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
            }
        });
    }

    public void setValuesForMenuView(Menu menu){
        title.setText(menu.getName());
        if(menu.isMain()){
            addImageView.setVisibility(View.VISIBLE);
        }else{
            addImageView.setVisibility(View.INVISIBLE);
        }
    }


}
