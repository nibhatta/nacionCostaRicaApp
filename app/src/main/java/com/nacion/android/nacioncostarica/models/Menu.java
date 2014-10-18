package com.nacion.android.nacioncostarica.models;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 17/10/2014.
 */
public class Menu {
    private static final int HEADER = 0;
    private static final int MENU = 0;
    private static final int SUB_MENU = 0;

    private int moduleId;
    private int typeCode;
    private String name;
    private List<Menu> sections;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public List<Menu> getSections() {
        return sections;
    }

    public void setSections(List<Menu> sections) {
        this.sections = sections;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }
}
