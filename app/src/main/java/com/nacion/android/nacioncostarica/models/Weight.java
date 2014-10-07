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
public class Weight {
    private String type;
    private Data data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

        JSONObject jsonData = argJSONWeight.getJSONObject("data");
        weight.setData(new Data().buildDataFromJSONObject(jsonData));

        return weight;
    }
}
