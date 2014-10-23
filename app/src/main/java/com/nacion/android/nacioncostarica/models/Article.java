package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class Article extends ArticleContentItemList {
    private int id;
    private Cover cover;
    private String title;
    private String summary;
    private String author;
    private String place;
    private String section;
    private String subSection;
    private Date timestamp;
    private List<Body> body;
    private List<Related> related;

    public boolean isAEmptyObject(){
        return(id == 0 || cover == null ||
               title == null || summary == null ||
               author == null || timestamp == null ||
               body == null || place == null ||
               section == null || subSection == null ||
               timestamp == null || body == null);
    }

    public List<Related> getRelated() {
        return related;
    }

    public void setRelated(List<Related> related) {
        this.related = related;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public String getSubSection() {
        return subSection;
    }

    public void setSubSection(String subSection) {
        this.subSection = subSection;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<Body> getBody() {
        return body;
    }

    public void setBody(List<Body> body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Map<Integer, Article> buildArticlesMapFromJSONObject(JSONArray argJSONArticles){
        Map<Integer, Article> articles = new HashMap<Integer, Article>();
        int size = argJSONArticles.length();
        try {
            for (int i = 0; i < size; i++) {
                JSONObject jsonArticle = argJSONArticles.getJSONObject(i);
                Article article = buildArticleFromJSONObject(jsonArticle);
                articles.put(article.getId(), article);
            }
        }catch(JSONException e){
            Log.d(Board.class.getName(), e.getLocalizedMessage());
        }
        return articles;
    }

    public Article buildArticleFromJSONObject(JSONObject jsonArticle) throws JSONException{
        Article article = new Article();

        int id = Integer.parseInt(jsonArticle.getString("id"));
        article.setId(id);

        article.setTitle(jsonArticle.getString("title"));
        article.setSummary(jsonArticle.getString("summary"));
        article.setAuthor(jsonArticle.getString("author"));
        article.setPlace(jsonArticle.getString("place"));
        article.setSection(jsonArticle.getString("section"));
        article.setSubSection(jsonArticle.getString("subsection"));

        int timestampInt = jsonArticle.getInt("timestamp");
        article.setTimestamp(new Date(timestampInt));

        String bodyTag = "body";
        if(jsonArticle.has(bodyTag)){
            JSONArray jsonBody = jsonArticle.getJSONArray(bodyTag);
            article.setBody(new Body().buildBodyListFromJSONObject(jsonBody));
        }

        String coverTag = "cover";
        if(jsonArticle.has(coverTag)) {
            JSONObject jsonCover = jsonArticle.getJSONObject(coverTag);
            article.setCover(new Cover().buildCoverFromJSONObject(jsonCover));
        }

        String tagRelated = "related";
        if(jsonArticle.has(tagRelated)){
            JSONArray jsonRelated = jsonArticle.getJSONArray(tagRelated);
            article.setRelated(new Related().buildRelatedListFromJSONObject(jsonRelated));
        }

        return article;
    }
}
