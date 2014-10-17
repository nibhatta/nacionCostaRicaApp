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
public class Weight extends ArticleContentItemList{
    private Data data;
    private String strData;

    public String getStrData() {
        return strData;
    }

    public void setStrData(String strData) {
        this.strData = strData;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Weight> buildWeightListFromJSONObject(JSONArray argJSONArrayWeights){
        List<Weight> weights = new ArrayList<Weight>();
        int size = argJSONArrayWeights.length();
        try {
            for (int i = 0; i < size; i++) {
                JSONObject jsonWeight = argJSONArrayWeights.getJSONObject(i);
                Weight weight = buildWeightFromJSONObject(jsonWeight);
                weights.add(weight);
            }
        }catch(JSONException e){
            Log.d(Weight.class.getName(), e.getLocalizedMessage());
        }
        return weights;
    }

    public Weight buildWeightFromJSONObject(JSONObject argJSONWeight) throws JSONException{
        Weight weight = new Weight();
        String type = argJSONWeight.getString("tipo");
        weight.setType(type);
        String dataTag = "data";
        if(isDataString(argJSONWeight)){
            weight.setStrData(argJSONWeight.getString(dataTag));
        }else {
            JSONObject jsonData = argJSONWeight.getJSONObject(dataTag);
            weight.setData(new Data().buildDataFromJSONObject(jsonData));
        }
        return weight;
    }

    private boolean isDataString(JSONObject argJSONData){
        return !argJSONData.has("id") && !argJSONData.has("caption") && !argJSONData.has("url") && !argJSONData.has("assetId") && !argJSONData.has("embed");
    }
}
