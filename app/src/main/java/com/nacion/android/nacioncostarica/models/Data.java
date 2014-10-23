package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 06/10/2014.
 */
public class Data {
    private String caption;
    private String credit;
    private String tablet;
    private String phone;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getTablet() {
        return tablet;
    }

    public void setTablet(String tablet) {
        this.tablet = tablet;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Data> buildDataListFromJSONObject(JSONArray jsonArrayData){
        List<Data> arrayData = new ArrayList<Data>();
        int size = jsonArrayData.length();
        try {
            for (int i = 0; i < size; i++) {
                JSONObject jsonData = jsonArrayData.getJSONObject(i);
                Data data = buildDataFromJSONObject(jsonData);
                arrayData.add(data);
            }
        }catch(JSONException e){
            Log.d(Data.class.getName(), e.getLocalizedMessage());
        }
        return arrayData;
    }

    public Data buildDataFromJSONObject(JSONObject argJSONData){
        Data data = new Data();
        try{
            data.setCaption(argJSONData.getString("caption"));
            data.setCredit(argJSONData.getString("credit"));
            data.setTablet(argJSONData.getString("tablet"));
            data.setPhone(argJSONData.getString("phone"));
        }catch(JSONException e){
            Log.d(Data.class.getName(), e.getLocalizedMessage());
        }
        return data;
    }
}
