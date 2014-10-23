package com.nacion.android.nacioncostarica.commons;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.nacion.android.nacioncostarica.models.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 21/10/2014.
 */
public class SharedPreferencesManager{
    private static final String APP_ID = "nacionCostaRicaApp";
    private static final String EMPTY_VALUE = "";
    private static final String MENU = "menu";

    private static SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static SharedPreferences getPreferences(Context context){
        if(preferences == null){
            preferences = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
        }
        return preferences;
    }

    protected SharedPreferencesManager(){}

    public void putMenu(String jsonMenu){
        editor = preferences.edit();
        editor.putString(MENU, jsonMenu);
        editor.commit();
    }

    public List<Menu> getMenu(){
        List<Menu> menus = new ArrayList<Menu>();
        String json = preferences.getString(MENU, EMPTY_VALUE);
        return menus;
    }
}
