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
