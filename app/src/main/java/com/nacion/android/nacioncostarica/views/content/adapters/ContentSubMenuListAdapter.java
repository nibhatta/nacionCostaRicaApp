package com.nacion.android.nacioncostarica.views.content.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.models.Menu;
import com.nacion.android.nacioncostarica.views.content.ContentPresenter;
import com.nacion.android.nacioncostarica.views.content.holder.ContentSubMenuViewHolder;
import com.nacion.android.nacioncostarica.views.home.adapters.HomeListView;
import com.nacion.android.nacioncostarica.views.home.holder.SubMenuViewHolder;
import com.nacion.android.nacioncostarica.views.main.MainPresenter;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class ContentSubMenuListAdapter extends ArrayAdapter<Menu> implements HomeListView {
    private static final int VIEWS_TYPE_COUNT = 2;
    private ContentPresenter presenter;
    private LayoutInflater inflater;

    public ContentSubMenuListAdapter(Context context, List<Menu> argContents) {
        super(context, R.layout.menu_list_item, argContents);
        inflater = LayoutInflater.from(context);
    }

    public ContentPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(ContentPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Menu itemList = getItem(position);
        int codeType = getItemViewType(position);
        ContentSubMenuViewHolder holder;

        if(convertView == null){
            holder = new ContentSubMenuViewHolder(presenter);
            switch(codeType) {
                case Menu.HEADER:
                    convertView = inflater.inflate(R.layout.header_sub_menu_list_item, null);
                    holder.setComponentsReferencesForHeaderView(convertView);
                    break;

                case Menu.MENU:
                    convertView = inflater.inflate(R.layout.menu_sub_menu_list_item, null);
                    holder.setComponentsReferencesForMenuView(convertView, position);
                    break;
            }
            convertView.setTag(holder);

        }else{
            holder = (ContentSubMenuViewHolder)convertView.getTag();
        }

        setHolderViewValuesByCodeType(holder, itemList, codeType);

        return convertView;
    }

    private void setHolderViewValuesByCodeType(ContentSubMenuViewHolder argHolder, Menu argItem, int argCodeType){
        switch(argCodeType){
            case Menu.HEADER:
                argHolder.setValuesForHeaderView(argItem);
                break;
            case Menu.MENU:
                argHolder.setValuesForMenuView(argItem);
                break;
        }
    }

    @Override
    public int getViewTypeCount() {
        return VIEWS_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        Menu item = getItem(position);
        return item.getTypeCode();
    }
}
