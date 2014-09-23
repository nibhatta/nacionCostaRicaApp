package com.nacion.android.nacioncostarica.models;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class Module implements Item{
    private String type;
    private int order;
    private List<Content> contents;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
