package com.nacion.android.nacioncostarica.holders;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.tasks.ImageDownloaderTask;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gustavo Matarrita on 08/10/2014.
 */
public abstract class ViewHolderBase {

    public String getDateFormat(Date argDate){
        if(argDate == null){
            Log.d(ViewHolderBase.class.getName(), "Invalid argument for ViewHolderBase.getDateFormat");
            return NacionConstants.EMPTY_STRING;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(" hh:mm a");
        return dateFormat.format(argDate);
    }

    public void downloadImage(String argUrl, ImageView argImage){
        ImageDownloaderTask task = new ImageDownloaderTask(argImage);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, argUrl);
        }else{
            task.execute(argUrl);
        }
    }
}
