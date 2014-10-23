package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 21/10/2014.
 */
public class Cover extends ArticleContentItemList{
    private List<Data> dataAre;
    private String contentType;

    public List<Data> getDataAre() {
        return dataAre;
    }

    public void setDataAre(List<Data> dataAre) {
        this.dataAre = dataAre;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Cover buildCoverFromJSONObject(JSONObject jsonCover){
        Cover cover = new Cover();
        try {
            String contentTypeTag = "contentType";
            if (jsonCover.has(contentTypeTag)) {
                cover.setContentType(jsonCover.getString(contentTypeTag));
            }

            String dataTag = "data";
            if(jsonCover.has(dataTag)){
                JSONArray dataArray = jsonCover.getJSONArray(dataTag);
                cover.setDataAre(new Data().buildDataListFromJSONObject(dataArray));
            }
        }catch(JSONException e){
            Log.d(Cover.class.getName(), e.getLocalizedMessage());
        }
        return cover;
    }
}
