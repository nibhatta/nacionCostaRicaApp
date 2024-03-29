package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Gustavo Matarrita on 17/10/2014.
 */
public class Menu {
    public static final int WITHOUT_BOARD = -1;
    public static final String HOME_CODE = "1600";
    public static final int HEADER = 0;
    public static final int MENU = 1;
    public static final int SUB_MENU = 2;

    private int typeCode;
    private int boardId;
    private String name;
    private boolean main;
    private boolean notification;

    public Menu(int typeCode, String name, boolean main, boolean notification, int boardId){
        this.typeCode = typeCode;
        this.name = name;
        this.main = main;
        this.notification = notification;
        this.boardId = boardId;
    }

    public Menu(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public JSONObject getJSONObject(){
        JSONObject obj = new JSONObject();
        try{
            obj.put("typeCode", typeCode);
            obj.put("boardId", boardId);
            obj.put("name", name);
            obj.put("main", main);
            obj.put("notification", notification);
        }catch(JSONException e){
            Log.d(Menu.class.getName(), e.getLocalizedMessage());
        }
        return obj;
    }

    public static Menu createMainMenuHeader(){
        return new Menu(HEADER, "Secciones", true, false, WITHOUT_BOARD);
    }

    public static Menu createSectionSubMenu(){
        return new Menu(SUB_MENU, "Más secciones", true, false, WITHOUT_BOARD);
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
}
