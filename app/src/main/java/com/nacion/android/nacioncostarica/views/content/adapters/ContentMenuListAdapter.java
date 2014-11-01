package com.nacion.android.nacioncostarica.views.content.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.models.Menu;
import com.nacion.android.nacioncostarica.views.content.ContentPresenter;
import com.nacion.android.nacioncostarica.views.content.holder.ContentMenuViewHolder;
import com.nacion.android.nacioncostarica.views.content.listeners.ContentSubMenuOnClickListener;
import com.nacion.android.nacioncostarica.views.home.adapters.HomeListView;
import com.nacion.android.nacioncostarica.views.home.holder.MenuViewHolder;
import com.nacion.android.nacioncostarica.views.home.listeners.MenuOnTouchListener;
import com.nacion.android.nacioncostarica.views.home.listeners.SubMenuOnClickListener;
import com.nacion.android.nacioncostarica.views.main.MainPresenter;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class ContentMenuListAdapter extends ArrayAdapter<Menu> implements HomeListView {
    private static final int VIEWS_TYPE_COUNT = 3;
    private ContentPresenter presenter;
    private LayoutInflater inflater;
    private Context context;
    private FragmentManager fragmentManager;
    private DrawerLayout parentDrawerLayout;
    private ViewGroup parentReferences;
    private List<Menu> menuList;

    public ContentMenuListAdapter(Context context, List<Menu> argContents, FragmentManager argFragmentManager) {
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

    public ContentPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(ContentPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        this.parentReferences = parent;
        Menu itemList = getItem(position);
        int codeType = getItemViewType(position);
        ContentMenuViewHolder holder;

        if(convertView == null){
            holder = new ContentMenuViewHolder(presenter);
            switch(codeType) {
                case Menu.HEADER:
                    convertView = inflater.inflate(R.layout.header_menu_list_item, null);
                    holder.setReferencesForHeaderView(convertView);
                    break;

                case Menu.MENU:
                    convertView = inflater.inflate(R.layout.menu_list_item, null);
                    MenuOnTouchListener listener = new MenuOnTouchListener(parentDrawerLayout, parent, position);
                    convertView.setOnTouchListener(listener);
                    holder.setReferencesForMenuView(convertView);
                    break;

                case Menu.SUB_MENU:
                    convertView = inflater.inflate(R.layout.submenu_list_item, null);
                    ContentSubMenuOnClickListener subMenuListener = new ContentSubMenuOnClickListener(presenter);
                    convertView.setOnClickListener(subMenuListener);
                    holder.setReferencesForSubMenuView(convertView);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (ContentMenuViewHolder)convertView.getTag();
        }

        setHolderViewValuesByCodeType(holder, itemList, codeType, position);

        return convertView;
    }

    private void setHolderViewValuesByCodeType(ContentMenuViewHolder argHolder, Menu argItem, int argCodeType, int argPosition){
        switch(argCodeType){
            case Menu.HEADER:
                argHolder.setValuesForHeaderView(argItem);
                break;
            case Menu.MENU:
                argHolder.setValuesForMenuView(argPosition, this, argItem);
                break;
            case Menu.SUB_MENU:
                argHolder.setValuesForSubMenuView(argItem);
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

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
