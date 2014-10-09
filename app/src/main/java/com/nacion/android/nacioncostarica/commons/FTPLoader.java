package com.nacion.android.nacioncostarica.commons;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.nacion.android.nacioncostarica.commons.exceptions.FTPLoaderException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Gustavo Matarrita on 28/08/2014.
 */
public class FTPLoader {
    public static final Bitmap downloadPhotoFromURL(String argUrl) throws FTPLoaderException{
        if(!isUrlWellFormatted(argUrl)){
            throw new IllegalArgumentException("Invalid argument for FTPLoader.loadPhotoFromURL");
        }
        Bitmap bitmap = null;
        try {
            URL url = new URL(argUrl);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            InputStream stream = null;
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                stream = httpURLConnection.getInputStream();
            }

            bitmap = BitmapFactory.decodeStream(stream);
        }catch (MalformedURLException e) {
            throw new FTPLoaderException(e);
        }catch (IOException e){
            throw new FTPLoaderException(e);
        }
        return bitmap;
    }

    private static boolean isUrlWellFormatted(String argUrl){
        return(!argUrl.isEmpty() && argUrl != null);
    }
}

