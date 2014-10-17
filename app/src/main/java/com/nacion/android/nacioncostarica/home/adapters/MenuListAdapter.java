package com.nacion.android.nacioncostarica.home.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.home.holder.MenuViewHolder;
import com.nacion.android.nacioncostarica.home.listeners.MenuOnTouchListener;
import com.nacion.android.nacioncostarica.main.MainPresenter;
import com.nacion.android.nacioncostarica.models.ContentItemList;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class MenuListAdapter extends ArrayAdapter<String> implements HomeListView {
    private static final int VIEWS_TYPE_COUNT = 8;
    private MainPresenter presenter;
    private LayoutInflater inflater;
    private Context context;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;
    private DrawerLayout parentDrawerLayout;
    private ViewGroup parentReferences;
    private List<String> contentsList;

    public MenuListAdapter(Context context, List<String> argContents, FragmentManager argFragmentManager) {
        super(context, R.layout.menu_list_item, argContents);
        this.context = context;
        contentsList = argContents;
        inflater = LayoutInflater.from(context);
        fragmentManager = argFragmentManager;
    }

    public DrawerLayout getParentDrawerLayout() {
        return parentDrawerLayout;
    }

    public void setParentDrawerLayout(DrawerLayout parentDrawerLayout) {
        this.parentDrawerLayout = parentDrawerLayout;
    }

    public MainPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        this.parentReferences = parent;
        String itemList = getItem(position);
        int codeType = getItemViewType(position);
        MenuViewHolder holder;

        if(convertView == null){
            holder = new MenuViewHolder(presenter);
            convertView = inflater.inflate(R.layout.menu_list_item, null);

            MenuOnTouchListener listener = new MenuOnTouchListener(parentDrawerLayout, parent);
            listener.setPresenter(presenter);

            convertView.setOnTouchListener(listener);
            convertView.setTag(holder);
            holder.setComponentsReferences(convertView);
        }else{
            holder = (MenuViewHolder)convertView.getTag();
        }

        holder.setValuesForArticleView(position, this, itemList);

        return convertView;
    }

    public List<String> getContentsList() {
        return contentsList;
    }

    public void setContentsList(List<String> contentsList) {
        this.contentsList = contentsList;
    }

    /*
    private void setHolderViewValuesByCodeType(HomeViewHolder argHolder, ContentItemList argItem, int argCodeType){
        switch(argCodeType){
            case NacionConstants.MODULE_CODE_ONE:
                argHolder.setViewHolderValuesForHighlightView(argItem);
                break;
            case NacionConstants.MODULE_CODE_TWO:
                argHolder.setViewHolderValuesForArticleView(argItem);
                break;
            case NacionConstants.MODULE_CODE_THREE:
                argHolder.setViewHolderValuesForVideoGalleryView();
                break;
            case NacionConstants.MODULE_CODE_FOURTH:

                break;
            case NacionConstants.MODULE_CODE_FIVE:

                break;
            case NacionConstants.MODULE_CODE_EIGHT:

                break;
        }
    }*/
}
