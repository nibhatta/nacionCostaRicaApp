package com.nacion.android.nacioncostarica.models;

import java.util.Date;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 24/09/2014.
 */
public abstract class ContentItemList{
    protected int id;
    protected String title;
    protected Date date;
    protected Image image;
    protected Module module;
    protected List<Content> contents;

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public void setContents(List<Content> argContents){
        contents = argContents;
    }

    public List<Content> getContents(){
        return contents;
    }
}
