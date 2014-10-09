package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Gustavo Matarrita on 24/09/2014.
 */
public class ContentModule extends ContentItemList{
    public ContentModule buildContentModuleFromJSONObject(JSONObject argContentModuleJSON){
        ContentModule contentModule = new ContentModule();
        try {
            String tagId = "id";
            if(argContentModuleJSON.has(tagId)) {
                contentModule.setId(argContentModuleJSON.getInt(tagId));
            }

            String tagTitle = "title";
            if(argContentModuleJSON.has(tagTitle)) {
                contentModule.setTitle(argContentModuleJSON.getString(tagTitle));
            }

            String tagSummary = "summary";
            if(argContentModuleJSON.has(tagSummary)) {
                contentModule.setSummary(argContentModuleJSON.getString(tagSummary));
            }

            String tagSection = "section";
            if(argContentModuleJSON.has(tagSection)) {
                contentModule.setSection(argContentModuleJSON.getString(tagSection));
            }

            String tagTimestamp = "timestamp";
            if(argContentModuleJSON.has(tagTimestamp)) {
                int timestampInt = argContentModuleJSON.getInt(tagTimestamp);
                contentModule.setTimestamp(new Date(timestampInt));
            }

            String tagImage = "image";
            if(argContentModuleJSON.has(tagImage)) {
                JSONObject jsonImage = argContentModuleJSON.getJSONObject(tagImage);
                Image image = new Image().buildImageFromJSONObject(jsonImage);
                contentModule.setImage(image);
            }

        }catch (JSONException e){
            Log.d(ContentModule.class.getName(), e.getLocalizedMessage());
        }
        return contentModule;
    }
}
