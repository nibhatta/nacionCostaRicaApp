package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class Gallery {
    private int id;
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

    public Map<Integer, Gallery> buildGalleriesMapFromJSONObject(JSONArray argJSONGalleries){
        Map<Integer, Gallery> galleries = new HashMap<Integer, Gallery>();
        int size = argJSONGalleries.length();
        try {
            for (int i = 0; i < size; i++) {
                JSONObject jsonGallery = argJSONGalleries.getJSONObject(i);
                Gallery gallery = buildGalleryFromJSONObject(jsonGallery);
                galleries.put(gallery.getId(), gallery);
            }
        }catch(JSONException e){
            Log.d(Board.class.getName(), e.getLocalizedMessage());
        }
        return galleries;
    }

    public Gallery buildGalleryFromJSONObject(JSONObject argJSONGallery) throws JSONException{
        Gallery gallery = new Gallery();
        Iterator<String> iterator = argJSONGallery.keys();
        String key = (iterator.hasNext())? iterator.next() : null;
        if(key != null){
            gallery.setId(Integer.parseInt(key));
            JSONArray jsonImages = argJSONGallery.getJSONArray(key);
            gallery.setImages(new Image().buildImageListFromJSONObject(jsonImages));
        }
        return gallery;
    }
}
