package com.nacion.android.nacioncostarica.tasks;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.nacion.android.nacioncostarica.commons.FTPLoader;
import com.nacion.android.nacioncostarica.commons.exceptions.FTPLoaderException;

import java.lang.ref.WeakReference;

/**
 * Created by Gustavo Matarrita on 08/10/2014.
 */
public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
    private WeakReference imageViewReference;

    public ImageDownloaderTask(ImageView argImageView){
        imageViewReference = new WeakReference(argImageView);
    }

    @Override
    protected Bitmap doInBackground(String... argUrl) {
        Log.d(ImageDownloaderTask.class.getName(), "=====> Start download image in background!!!");
        Bitmap bitmap = null;
        try {
            bitmap = FTPLoader.downloadPhotoFromURL(argUrl[0]);
        }catch(FTPLoaderException e){
            Log.d(ImageDownloaderTask.class.getName(), e.getLocalizedMessage());
        }catch(IllegalArgumentException e){
            Log.d(ImageDownloaderTask.class.getName(), e.getLocalizedMessage());
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap argBitmap) {
        if(isCancelled()){
            argBitmap = null;
        }
        if(imageViewReference != null){
            ImageView imageView = (ImageView)imageViewReference.get();
            if(imageView != null){
                Log.d(ImageDownloaderTask.class.getName(), "=====> Weak reference was deleted for the garbage collector.");
            }
            if(argBitmap != null && imageView != null){
                imageView.setImageBitmap(argBitmap);
            }
        }
    }
}