package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import com.nacion.android.nacioncostarica.constants.NacionConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class Image{
    private String phoneUrl;
    private String tabletUrl;
    private String tabletPortraitUrl;
    private String tabletLandscapeUrl;
    private String caption;

    public String getTabletPortraitUrl() {
        return tabletPortraitUrl;
    }

    public void setTabletPortraitUrl(String tabletPortraitUrl) {
        this.tabletPortraitUrl = tabletPortraitUrl;
    }

    public String getTabletLandscapeUrl() {
        return tabletLandscapeUrl;
    }

    public void setTabletLandscapeUrl(String tabletLandscapeUrl) {
        this.tabletLandscapeUrl = tabletLandscapeUrl;
    }

    public String getPhoneUrl() {
        return phoneUrl;
    }

    public void setPhoneUrl(String phoneUrl) {
        this.phoneUrl = phoneUrl;
    }

    public String getTabletUrl() {
        return tabletUrl;
    }

    public void setTabletUrl(String tabletUrl) {
        this.tabletUrl = tabletUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public static Image createDummyImageCore(){
        String url = NacionConstants.URL;
        Image image = new Image();
        image.phoneUrl = url;

        return image;
    }

    public List<Image> buildImageListFromJSONObject(JSONArray argJSONImages){
        List<Image> images = new ArrayList<Image>();
        int size = argJSONImages.length();
        try {
            for (int i = 0; i < size; i++) {
                JSONObject jsonBoard = argJSONImages.getJSONObject(i);
                Image image = buildImageFromJSONObject(jsonBoard);
                images.add(image);
            }
        }catch(JSONException e){
            Log.d(Image.class.getName(), e.getLocalizedMessage());
        }
        return images;
    }

    public Image buildImageFromJSONObject(JSONObject argJSONImage){
        Image image = new Image();
        try{
            image.setPhoneUrl(argJSONImage.getString("phone"));

            String tabTag = "tab";
            if(argJSONImage.has(tabTag)) {
                image.setTabletUrl(argJSONImage.getString(tabTag));
            }

            String tabletPortraitTag = "tab_p";
            if(argJSONImage.has(tabletPortraitTag)) {
                image.setTabletPortraitUrl(argJSONImage.getString(tabletPortraitTag));
            }

            String tabletLandscapeTag = "tab_l";
            if(argJSONImage.has(tabletLandscapeTag)) {
                image.setTabletLandscapeUrl(argJSONImage.getString(tabletLandscapeTag));
            }

            String tabCaption = "caption";
            if(argJSONImage.has(tabCaption)) {
                image.setCaption(argJSONImage.getString(tabCaption));
            }

        }catch(JSONException e){
            Log.d(Image.class.getName(), e.getLocalizedMessage());
        }
        return image;
    }
}
