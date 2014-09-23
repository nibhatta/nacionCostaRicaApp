package com.nacion.android.nacioncostarica.models;

import java.util.Date;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class Content implements Item{
    private int id;
    private String title;
    private Date date;
    private List<Image> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
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
}
