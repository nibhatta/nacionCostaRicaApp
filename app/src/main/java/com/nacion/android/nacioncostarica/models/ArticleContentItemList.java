package com.nacion.android.nacioncostarica.models;

import com.nacion.android.nacioncostarica.constants.NacionConstants;

import java.util.Date;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 16/10/2014.
 */
public abstract class ArticleContentItemList {
    protected String type;

    public String getTitle(){return null;}
    public String getAuthor(){return null;}
    public String getSummary(){return null;}
    public Image getImage(){return null;}
    public Cover getCover(){return null;}
    public String getDataStr(){return null;}
    public Data getDataObj(){return null;}
    public Date getTimestamp(){return null;}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeCode(){
        int code = 0;
        if(NacionConstants.ARTICLE_PARTS.containsKey(type)) {
            code = NacionConstants.ARTICLE_PARTS.get(type);
        }
        return code;
    }

    public boolean isParagraph(){
        return NacionConstants.ARTICLE_PARAGRAPH.equals(type);
    }
}
