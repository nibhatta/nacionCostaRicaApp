package com.nacion.android.nacioncostarica.useCases;

import android.util.Log;

import com.nacion.android.nacioncostarica.models.Site;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gustavo Matarrita on 02/10/2014.
 */
public class JSONReaderImpl implements JSONReader{
    @Override
    public Site createObjectsFromJSONString(String argJson) {
        Site site = null;
        try {
            JSONObject jsonObject = new JSONObject(argJson);
            site = new Site().buildSiteFromJSONObject(jsonObject);
        }catch(JSONException e){
            Log.d(JSONReaderImpl.class.getName(), e.getLocalizedMessage());
        }
        return site;
    }
}
