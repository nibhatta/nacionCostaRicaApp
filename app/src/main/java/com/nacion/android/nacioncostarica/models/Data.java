package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gustavo Matarrita on 06/10/2014.
 */
public class Data {
    private String id;
    private String caption;
    private String url;
    private String assetId;
    private String embed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getEmbed() {
        return embed;
    }

    public void setEmbed(String embed) {
        this.embed = embed;
    }

    public Data buildDataFromJSONObject(JSONObject argJSONData){
        Data data = new Data();
        try{
            data.setId(argJSONData.getString("id"));
            data.setCaption(argJSONData.getString("caption"));
            data.setUrl(argJSONData.getString("url"));
            data.setAssetId(argJSONData.getString("assetId"));
            data.setEmbed(argJSONData.getString("embed"));
        }catch(JSONException e){
            Log.d(Data.class.getName(), e.getLocalizedMessage());
        }
        return data;
    }
}
