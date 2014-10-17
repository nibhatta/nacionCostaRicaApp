package com.nacion.android.nacioncostarica.home.holder;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.holders.ViewHolderBase;
import com.nacion.android.nacioncostarica.home.adapters.HomeListPresenter;
import com.nacion.android.nacioncostarica.home.adapters.MenuListAdapter;
import com.nacion.android.nacioncostarica.main.MainPresenter;
import com.nacion.android.nacioncostarica.models.ContentItemList;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 15/10/2014.
 */
public class MenuViewHolder extends ViewHolderBase{
    private final static int START_POSITION = 0;

    private ImageView deleteImageView;
    private ImageView notificationImageView;
    private TextView title;
    private MainPresenter presenter;

    public MenuViewHolder(MainPresenter argPresenter){
        presenter = argPresenter;
    }

    public void setComponentsReferences(View argView){
        deleteImageView = (ImageView)argView.findViewById(R.id.deleteImageView);
        notificationImageView = (ImageView)argView.findViewById(R.id.notificationImageView);
        title = (TextView)argView.findViewById(R.id.menuIdTextView);
    }

    public void setValuesForArticleView(final int argPosition, final MenuListAdapter argListAdapter, String argItemList){
        title.setText(argItemList);
        deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOriginalView();
                argListAdapter.getContentsList().remove(argPosition);
                argListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void resetOriginalView(){
        deleteImageView.setVisibility(View.INVISIBLE);
        notificationImageView.setVisibility(View.VISIBLE);
        title.setTranslationX(START_POSITION);
    }
}
