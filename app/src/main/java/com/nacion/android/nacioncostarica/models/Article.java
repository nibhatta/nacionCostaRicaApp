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
public class Article implements IArticleContentItemList {
    private int id;
    private int typeCode;
    private Image image;
    private String title;
    private String summary;
    private String author;
    private Date timestamp;
    private String body;
    private List<Weight> weights;

    public boolean isAEmptyObject(){
        return(id == 0 || image == null ||
               title == null || summary == null ||
               author == null || timestamp == null || body == null);
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Weight> getWeights() {
        return weights;
    }

    public void setWeights(List<Weight> weights) {
        this.weights = weights;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
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

    public Article buildArticleFromJSONObject(JSONObject argJSONArticle) throws JSONException{
        Article article = new Article();
        Iterator<String> iterator = argJSONArticle.keys();
        String key = (iterator.hasNext())? iterator.next() : null;
        if(key != null){
            article.id = Integer.parseInt(key);
            JSONObject jsonArticle = argJSONArticle.getJSONObject(key);
            article.setTitle(jsonArticle.getString("title"));
            article.setSummary(jsonArticle.getString("summary"));
            article.setAuthor(jsonArticle.getString("autor"));

            int timestampInt = jsonArticle.getInt("datetime");
            article.setTimestamp(new Date(timestampInt));

            article.setBody(jsonArticle.getString("body"));

            JSONObject jsonImage = jsonArticle.getJSONObject("image");
            article.setImage(new Image().buildImageFromJSONObject(jsonImage));

            String weightTag = "pesos";
            if(jsonArticle.has(weightTag)) {
                JSONArray jsonWeights = jsonArticle.getJSONArray(weightTag);
                article.setWeights(new Weight().buildWeightListFromJSONObject(jsonWeights));
            }
        }
        return article;
    }
}
