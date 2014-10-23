package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import com.nacion.android.nacioncostarica.constants.NacionConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 06/10/2014.
 */
public class Body extends ArticleContentItemList{
    public static final List<String> DATA_OBJECT_TYPES = new ArrayList<String>(){
        {
            add(NacionConstants.IMG);
        }
    };

    private String dataStr;
    private Data dataObj;

    public String getDataStr() {
        return dataStr;
    }

    public void setDataStr(String dataStr) {
        this.dataStr = dataStr;
    }

    public Data getDataObj() {
        return dataObj;
    }

    public void setDataObj(Data dataObj) {
        this.dataObj = dataObj;
    }

    public List<Body> buildBodyListFromJSONObject(JSONArray jsonArrayBody){
        List<Body> bodies = new ArrayList<Body>();
        int size = jsonArrayBody.length();
        try {
            for (int i = 0; i < size; i++) {
                JSONObject jsonBody = jsonArrayBody.getJSONObject(i);
                Body body = buildBodyFromJSONObject(jsonBody);
                if(!body.isEmpty()) {
                    bodies.add(body);
                }
            }
        }catch(JSONException e){
            Log.d(Body.class.getName(), e.getLocalizedMessage());
        }
        return bodies;
    }

    public Body buildBodyFromJSONObject(JSONObject jsonBody) throws JSONException{
        Body body = new Body();

        String typeTag = "type";
        if(jsonBody.has(typeTag)) {
            body.setType(jsonBody.getString(typeTag));
        }

        String dataTag = "data";
        if(DATA_OBJECT_TYPES.contains(body.getType())){
            JSONObject jsonData = jsonBody.getJSONObject(dataTag);
            body.setDataObj(new Data().buildDataFromJSONObject(jsonData));
        }else{
            body.setDataStr(jsonBody.getString(dataTag));
        }
        return body;
    }

    public boolean isEmpty(){
        return(type == null && (dataStr == null || dataObj == null));
    }
}
