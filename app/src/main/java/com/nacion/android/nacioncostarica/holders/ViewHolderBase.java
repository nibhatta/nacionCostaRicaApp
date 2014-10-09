package com.nacion.android.nacioncostarica.holders;

import android.util.Log;

import com.nacion.android.nacioncostarica.constants.NacionConstants;

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
}
