package com.nacion.android.nacioncostarica.views.base.holders;

/**
 * Created by Gustavo Matarrita on 25/09/2014.
 */
public class Section {
    private String name;
    private boolean notification;
    private boolean enable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public static Section createDummySectionCore(boolean argEnable){
        Section section = new Section();
        section.name = "Section";
        section.enable = argEnable;
        return section;
    }
}
