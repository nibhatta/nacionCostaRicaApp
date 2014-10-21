package com.nacion.android.nacioncostarica.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Gustavo Matarrita on 20/10/2014.
 */
public class LeftMenu {
    private List<Menu> menus;

    public void setMenus(Map<String, String> argBoardNamesMap){
        menus = new ArrayList<Menu>();
        List<String> keys = new ArrayList<String>(argBoardNamesMap.keySet());
        for(String key : keys){
            String value = argBoardNamesMap.get(key);
            Menu menu = key.equals(Menu.HOME_CODE) ? new Menu(Menu.MENU, value, true, true) : new Menu(Menu.MENU, value, false, false);
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
        for(Menu menu : menus){
            if(!menu.isMain()) {
                subMenu.add(menu);
            }
        }
        return subMenu;
    }
}
