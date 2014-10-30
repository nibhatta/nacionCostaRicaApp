package com.nacion.android.nacioncostarica.views.base.activity;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.ListView;

import com.nacion.android.nacioncostarica.commons.SharedPreferencesManager;
import com.nacion.android.nacioncostarica.models.LeftMenu;
import com.nacion.android.nacioncostarica.models.Menu;
import com.nacion.android.nacioncostarica.views.home.adapters.MenuListAdapter;
import com.nacion.android.nacioncostarica.views.home.adapters.SubMenuListAdapter;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 30/10/2014.
 */
public abstract class BaseActivity extends FragmentActivity{
    protected DrawerLayout drawerLayout;
    protected ListView leftList;
    protected ListView rightList;
    protected LeftMenu leftMenu;
    protected FragmentManager mainFragmentManager;
    protected float lastTranslate = 0.0f;
    protected SharedPreferencesManager preferences;
    protected ActionBarDrawerToggle drawerToggle;

    public void addItemToMainMenu(int position){
        leftMenu.addItemToMainMenu(position);
        storeMenuChanges();
    }

    public void addMenuToNotification(String name) {
        leftMenu.addItemToNotifications(name);
        storeMenuChanges();
    }

    public void removeMenuFromNotification(String name) {
        leftMenu.removeItemFromNotifications(name);
        storeMenuChanges();
    }

    public void removeItemFromMainMenu(int position){
        leftMenu.removeItemFromMainMenu(position);
        storeMenuChanges();
    }

    public void removeItemFromMainMenu(String name){
        leftMenu.removeItemFromMainMenu(name);
        storeMenuChanges();
    }

    protected void closeLeftMenu(){
        if(drawerLayout != null && drawerLayout.isDrawerOpen(leftList)){
            drawerLayout.closeDrawer(leftList);
        }
    }

    protected void storeMenuChanges(){
        String jsonString = leftMenu.getJSONArrayObject().toString();
        preferences.putMenu(jsonString);
    }

    protected void showRightDrawLayout() {
        if(drawerLayout != null && rightList != null){
            if(drawerLayout.isDrawerOpen(leftList)){
                drawerLayout.closeDrawer(leftList);
            }
            drawerLayout.openDrawer(rightList);
        }
    }

    protected void showLeftDrawLayout() {
        if(drawerLayout != null && leftList != null){
            if(drawerLayout.isDrawerOpen(rightList)){
                drawerLayout.closeDrawer(rightList);
            }
            drawerLayout.openDrawer(leftList);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            showLeftDrawLayout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
