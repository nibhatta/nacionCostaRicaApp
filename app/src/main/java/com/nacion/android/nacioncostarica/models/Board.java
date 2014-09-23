package com.nacion.android.nacioncostarica.models;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class Board implements Item{
    private int id;
    private List<Module> modules;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
