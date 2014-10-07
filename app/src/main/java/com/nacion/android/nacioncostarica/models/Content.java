package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class Content extends ContentItemList{

    public static Content createDummyContentCore(int argId, Module argModule){
        Content content = new Content();
        content.id = argId;
        content.title = "Title";
        content.timestamp = new Date();
        content.module = argModule;
        content.image = Image.createDummyImageCore();
        return content;
    }

    public List<Content> buildContentListFromJSONObject(JSONArray argJSONArray, Module argModule){
        List<Content> contents = new ArrayList<Content>();
        int size = argJSONArray.length();
        try {
            for (int i = 0; i < size; i++) {
                JSONObject jsonContent = argJSONArray.getJSONObject(i);
                Content content = buildContentFromJSONObject(jsonContent);
                content.setModule(argModule);
                contents.add(content);
            }
        }catch(JSONException e){
            Log.d(Content.class.getName(), e.getLocalizedMessage());
        }
        return contents;
    }

    public Content buildContentFromJSONObject(JSONObject argJSONContent) throws JSONException{
        Content content = new Content();
        content.setId(argJSONContent.getInt("id"));
        content.setTitle(argJSONContent.getString("title"));
        JSONObject jsonImage = argJSONContent.getJSONObject("image");
        Image image = new Image().buildImageFromJSONObject(jsonImage);
        content.setImage(image);
        return content;
    }
}
