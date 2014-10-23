package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/10/2014.
 */
public class Related extends ArticleContentItemList{
    private Date timestamp;
    private String title;
    private String summary;
    private String section;
    private String subSection;

    @Override
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubSection() {
        return subSection;
    }

    public void setSubSection(String subSection) {
        this.subSection = subSection;
    }

    public List<Related> buildRelatedListFromJSONObject(JSONArray jsonArray){
        List<Related> relatedArray = new ArrayList<Related>();
        int size = jsonArray.length();
        try {
            for (int i = 0; i < size; i++) {
                JSONObject jsonRelated = jsonArray.getJSONObject(i);
                Related related = buildRelatedFromJSONObject(jsonRelated);
                relatedArray.add(related);
            }
        }catch(JSONException e){
            Log.d(Related.class.getName(), e.getLocalizedMessage());
        }
        return relatedArray;
    }

    public Related buildRelatedFromJSONObject(JSONObject jsonContent) throws JSONException{
        Related related = new Related();

        String tagTitle = "title";
        if(jsonContent.has(tagTitle)) {
            related.setTitle(jsonContent.getString(tagTitle));
        }

        String tagTimestamp = "timestamp";
        if(jsonContent.has(tagTimestamp)) {
            int timestampInt = jsonContent.getInt(tagTimestamp);
            related.setTimestamp(new Date(timestampInt));
        }

        String tagSection = "section";
        if(jsonContent.has(tagSection)){
            related.setSection(jsonContent.getString(tagSection));
        }

        String tagSubSection = "subsection";
        if(jsonContent.has(tagSubSection)) {
            related.setSection(jsonContent.getString(tagSubSection));
        }

        String tagSummary = "summary";
        if(jsonContent.has(tagSummary)){
            related.setSummary(jsonContent.getString(tagSummary));
        }

        return related;
    }
}
