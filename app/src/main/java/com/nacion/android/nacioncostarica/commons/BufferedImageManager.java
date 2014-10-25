package com.nacion.android.nacioncostarica.commons;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gustavo Matarrita on 24/10/2014.
 */
public class BufferedImageManager {
    private static BufferedImageManager instance;
    private static Map<String, Bitmap> images = new HashMap<String, Bitmap>();

    protected BufferedImageManager(){}

    public static BufferedImageManager getInstance(){
        if(instance == null){
            instance = new BufferedImageManager();
        }
        return instance;
    }

    public void addImageToBuffer(String url, Bitmap bitmap){
        if(url == null){
            Log.e(BufferedImageManager.class.getName(), "Invalid argument for BufferedImageManaeger.");
            return;
        }
        if(!bufferContainsImage(url)){
            images.put(url, bitmap);
        }
    }

    public Bitmap getImageFromBuffer(String url){
        Bitmap image = null;
        if(bufferContainsImage(url)){
            image = images.get(url);
        }
        return image;
    }

    public boolean bufferContainsImage(String url){
        return images.containsKey(url);
    }
}
