package com.nacion.android.nacioncostarica.commons;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.nacion.android.nacioncostarica.models.Menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 21/10/2014.
 */
public class SharedPreferencesManager{
    private static final String APP_ID = "nacionCostaRicaApp";
    private static final String EMPTY_VALUE = "";
    private static final String MENU = "menu";

    private static SharedPreferencesManager instance;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static SharedPreferencesManager getPreferences(Context context){
        if(instance == null){
            instance = new SharedPreferencesManager();
            instance.preferences = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
        }
        return instance;
    }

    protected SharedPreferencesManager(){}

    public void putMenu(String jsonMenu){
        editor = preferences.edit();
        editor.putString(MENU, jsonMenu);
        editor.commit();
    }

    public List<Menu> getMenu(){
        List<Menu> menus = new ArrayList<Menu>();
        String jsonStr = preferences.getString(MENU, EMPTY_VALUE);
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObj.optJSONArray("menus");
            if(jsonArray != null){
                int size = jsonArray.length();
                for(int i=0; i<size; i++){
                    JSONObject obj = jsonArray.getJSONObject(i);
                    Menu menu = new Menu();
                    menu.setTypeCode(obj.getInt("typeCode"));
                    menu.setBoardId(obj.getInt("boardId"));
                    menu.setName(obj.getString("name"));
                    menu.setMain(obj.getBoolean("main"));
                    menu.setNotification(obj.getBoolean("notification"));
                    menus.add(menu);
                }
            }
        }catch(JSONException e){
            Log.e(SharedPreferencesManager.class.getName(), e.getLocalizedMessage());
        }
        return menus;
    }

    public boolean isMenuInSharedPreferences(){
        return preferences.contains(MENU);
    }
}
