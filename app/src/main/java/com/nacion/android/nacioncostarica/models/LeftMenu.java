package com.nacion.android.nacioncostarica.models;

import android.content.Context;
import android.util.Log;

import com.nacion.android.nacioncostarica.commons.SharedPreferencesManager;
import com.nacion.android.nacioncostarica.views.main.MainPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Gustavo Matarrita on 20/10/2014.
 */
public class LeftMenu {
    private List<Menu> menus;
    private Context context;
    private SharedPreferencesManager preferences;

    public LeftMenu(Context context){
        this.context = context;
        preferences = SharedPreferencesManager.getPreferences(context);
    }

    public void setMenus(Map<String, String> boardNamesMap){
        if(preferences.isMenuInSharedPreferences()) {
            menus = preferences.getMenu();
        }else{
            makeMenuFromScratch(boardNamesMap);
        }
    }

    private void makeMenuFromScratch(Map<String, String> boardNamesMap){
        menus = new ArrayList<Menu>();
        List<String> keys = new ArrayList<String>(boardNamesMap.keySet());
        for(String key : keys){
            String value = boardNamesMap.get(key);
            int keyInt = Integer.parseInt(key);
            Menu menu = key.equals(Menu.HOME_CODE) ? new Menu(Menu.MENU, value, true, true, keyInt) : new Menu(Menu.MENU, value, false, false, keyInt);
            menus.add(menu);
        }
    }

    public List<Menu> getMenus(){
        return menus;
    }

    public List<Menu> getMainMenu(){
        List<Menu> mainMenu = new ArrayList<Menu>();
        mainMenu.add(Menu.createMainMenuHeader());
        for(Menu menu : menus){
            if(menu.isMain()) {
                mainMenu.add(menu);
            }
        }
        mainMenu.add(Menu.createSectionSubMenu());
        return mainMenu;
    }

    public List<Menu> getSubMenu(){
        List<Menu> subMenu = new ArrayList<Menu>();
        subMenu.add(Menu.createMainMenuHeader());
        subMenu.addAll(menus);
        return subMenu;
    }

    public void addItemToMainMenu(int position){
        Menu item = menus.get(position - 1);
        if(item != null){
            item.setMain(true);
        }
    }

    public void removeItemFromMainMenu(int position){
        Menu item = menus.get(position - 1);
        if(item != null){
            item.setMain(false);
        }
    }

    public void removeItemFromMainMenu(String name){
        Menu menuToRemove = searchMenuByName(name);
        if(menuToRemove != null){
            menuToRemove.setMain(false);
            menuToRemove.setNotification(false);
        }
    }

    public void addItemToNotifications(String name){
        Menu menu = searchMenuByName(name);
        if(menu != null){
            menu.setNotification(true);
        }
    }

    public void removeItemFromNotifications(String name){
        Menu menu = searchMenuByName(name);
        if(menu != null){
            menu.setNotification(false);
        }
    }

    private Menu searchMenuByName(String name){
        Menu toSearch = null;
        for(Menu menu : menus){
            if(menu.getName().equals(name)){
                toSearch = menu;
                break;
            }
        }
        return toSearch;
    }

    public JSONObject getJSONArrayObject(){
        JSONArray arrayObj = new JSONArray();
        for(Menu menu : menus){
            arrayObj.put(menu.getJSONObject());
        }
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("menus", arrayObj);
        }catch(JSONException e){
            Log.d(LeftMenu.class.getName(), e.getLocalizedMessage());
        }
        return jsonObj;
    }
}
