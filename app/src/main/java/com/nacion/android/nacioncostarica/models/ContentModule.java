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
            contentModule.setId(argContentModuleJSON.getInt("id"));
            contentModule.setTitle(argContentModuleJSON.getString("title"));
            contentModule.setSummary(argContentModuleJSON.getString("summary"));
            contentModule.setSection(argContentModuleJSON.getString("section"));

            int timestampInt = argContentModuleJSON.getInt("timestamp");
            contentModule.setTimestamp(new Date(timestampInt));

        }catch (JSONException e){
            Log.d(ContentModule.class.getName(), e.getLocalizedMessage());
        }
        return contentModule;
    }
}
