package com.nacion.android.nacioncostarica.home.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.home.holder.MenuViewHolder;
import com.nacion.android.nacioncostarica.home.holder.SubMenuViewHolder;
import com.nacion.android.nacioncostarica.home.listeners.MenuOnTouchListener;
import com.nacion.android.nacioncostarica.home.listeners.SubMenuOnClickListener;
import com.nacion.android.nacioncostarica.main.MainPresenter;
import com.nacion.android.nacioncostarica.models.Menu;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class SubMenuListAdapter extends ArrayAdapter<Menu> implements HomeListView {
    private static final int VIEWS_TYPE_COUNT = 2;
    private MainPresenter presenter;
    private LayoutInflater inflater;
    private Context context;
    private FragmentManager fragmentManager;
    private DrawerLayout parentDrawerLayout;
    private ViewGroup parentReferences;
    private List<Menu> menuList;

    public SubMenuListAdapter(Context context, List<Menu> argContents, FragmentManager argFragmentManager) {
        super(context, R.layout.menu_list_item, argContents);
        this.context = context;
        menuList = argContents;
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
        Menu itemList = getItem(position);
        int codeType = getItemViewType(position);
        SubMenuViewHolder holder;

        if(convertView == null){
            holder = new SubMenuViewHolder(presenter);
            switch(codeType) {
                case Menu.HEADER:
                    convertView = inflater.inflate(R.layout.header_sub_menu_list_item, null);
                    holder.setComponentsReferencesForHeaderView(convertView);
                    break;

                case Menu.MENU:
                    convertView = inflater.inflate(R.layout.menu_sub_menu_list_item, null);
                    holder.setComponentsReferencesForMenuView(convertView);
                    break;
            }
            convertView.setTag(holder);

        }else{
            holder = (SubMenuViewHolder)convertView.getTag();
        }

        setHolderViewValuesByCodeType(holder, itemList, codeType, position);

        return convertView;
    }

    private void setHolderViewValuesByCodeType(SubMenuViewHolder argHolder, Menu argItem, int argCodeType, int argPosition){
        switch(argCodeType){
            case Menu.HEADER:
                argHolder.setValuesForHeaderView(argItem);
                break;
            case Menu.MENU:
                argHolder.setValuesForMenuView(argItem, menuList, argPosition);
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
