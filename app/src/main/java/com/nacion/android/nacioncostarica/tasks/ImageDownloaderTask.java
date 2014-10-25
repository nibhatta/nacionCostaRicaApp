package com.nacion.android.nacioncostarica.tasks;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.nacion.android.nacioncostarica.commons.BufferedImageManager;
import com.nacion.android.nacioncostarica.commons.FTPLoader;
import com.nacion.android.nacioncostarica.commons.exceptions.FTPLoaderException;

import java.lang.ref.WeakReference;

/**
 * Created by Gustavo Matarrita on 08/10/2014.
 */
public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
    private WeakReference imageViewReference;
    private BufferedImageManager buffer = BufferedImageManager.getInstance();

    public ImageDownloaderTask(ImageView argImageView){
        imageViewReference = new WeakReference(argImageView);
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        Bitmap bitmap = null;
        try {
            String url = urls[0];
            if(buffer.bufferContainsImage(url)){
                bitmap = buffer.getImageFromBuffer(url);
            }else {
                bitmap = FTPLoader.downloadPhotoFromURL(url);
                buffer.addImageToBuffer(url, bitmap);
            }

        }catch(FTPLoaderException e){
            Log.e(ImageDownloaderTask.class.getName(), e.getLocalizedMessage());
        }catch(IllegalArgumentException e){
            Log.e(ImageDownloaderTask.class.getName(), e.getLocalizedMessage());
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
            if(argBitmap != null && imageView != null){
                imageView.setImageBitmap(argBitmap);
            }
        }
    }
}