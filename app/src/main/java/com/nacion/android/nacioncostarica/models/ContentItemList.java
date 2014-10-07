package com.nacion.android.nacioncostarica.models;

import java.util.Date;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 24/09/2014.
 */
public abstract class ContentItemList{
    protected int id;
    protected String title;
    protected String summary;
    protected String section;
    protected String subSection;
    protected Date timestamp;
    protected boolean isSubsection;
    protected String caption;
    protected String team1;
    protected String team2;
    protected String scores1;
    protected String scores2;
    protected String mainText;
    protected boolean isFull;
    protected boolean isScores;
    protected boolean isMainText;
    protected boolean isSpecial;
    protected boolean isEvolution;
    protected Image image;
    protected Module module;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
