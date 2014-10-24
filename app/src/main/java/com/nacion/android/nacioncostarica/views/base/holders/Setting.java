package com.nacion.android.nacioncostarica.views.base.holders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 25/09/2014.
 */
public class Setting {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Setting> createDummySettingList(){
        List<Setting> settings = new ArrayList<Setting>(){
            {
                add(createDummySettingCore("Registrarse"));
                add(createDummySettingCore("Ingresar"));
                add(createDummySettingCore("Visualizar"));
                add(createDummySettingCore("Tamaño"));
            }
        };

        return settings;
    }

    private static Setting createDummySettingCore(String argName){
        Setting setting = new Setting();
        setting.name = argName;
        return setting;
    }
}
