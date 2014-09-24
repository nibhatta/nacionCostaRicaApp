package com.nacion.android.nacioncostarica.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class Content{
    private int id;
    private String title;
    private Date date;
    private Image image;
    private Module module;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public static Content createDummyContentCore(int argId, Module argModule){
        Content content = new Content();
        content.id = argId;
        content.title = "Title";
        content.date = new Date();
        content.module = argModule;
        content.image = Image.createDummyImageCore();
        return content;
    }
}
