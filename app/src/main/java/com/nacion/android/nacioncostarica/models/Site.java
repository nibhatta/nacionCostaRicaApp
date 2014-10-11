package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class Site{

    private Date timestamp;
    private Map<String, String> boardNames;
    private List<Board> boards;
    private Map<Integer, Article> articles;
    private Map<Integer, Gallery> galleries;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getBoardNames() {
        return boardNames;
    }

    public void setBoardNames(Map<String, String> boardNames) {
        this.boardNames = boardNames;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public Map<Integer, Article> getArticles() {
        return articles;
    }

    public void setArticles(Map<Integer, Article> articles) {
        this.articles = articles;
    }

    public Map<Integer, Gallery> getGalleries() {
        return galleries;
    }

    public void setGalleries(Map<Integer, Gallery> galleries) {
        this.galleries = galleries;
    }

    public Site buildSiteFromJSONObject(JSONObject argJSONObject) {
        Site site = new Site();
        try{
            int timestamp = argJSONObject.getInt("timestamp");
            String boardNames = argJSONObject.getString("boardNames");
            JSONArray boards = argJSONObject.getJSONArray("boards");
            JSONArray articles = argJSONObject.getJSONArray("articles");
            JSONArray galleries = argJSONObject.getJSONArray("galleries");

            site.setTimestamp(new Date(timestamp));
            site.setBoardNames(createBoardNamesMap(boardNames));
            site.setBoards(new Board().buildBoardListFromJSONObject(boards));

            Map<Integer, Article> articleMap = new Article().buildArticlesMapFromJSONObject(articles);
            site.setArticles(articleMap);

            Map<Integer, Gallery> galleryMap = new Gallery().buildGalleriesMapFromJSONObject(galleries);
            site.setGalleries(galleryMap);

        }catch(JSONException e){
            Log.d(Site.class.getName(), e.getLocalizedMessage());
        }
        return site;
    }

    private Map<String, String> createBoardNamesMap(String argBoardNames) throws JSONException{
        int key = 0, value = 1;
        Map<String, String> boardNames = new HashMap<String, String>();
        String[] items = argBoardNames.split(",");
        for(String item : items){
            String[] values = item.split(":");
            boardNames.put(values[key], values[value]);
        }
        return boardNames;
    }

    public List<IArticleContentItemList> getArticleContentsForPhone(int argArticleId){
        //TODO Create a list of article content item list with the article object.
        List<IArticleContentItemList> articleContents = new ArrayList<IArticleContentItemList>();
        //TODO Add the type code in order.
        Article article = getArticleById(argArticleId);
        article.setTypeCode(0);
        articleContents.add(article);
        return articleContents;
    }

    public Article getArticleById(int argArticleId){
        return articles.containsKey(argArticleId)? articles.get(argArticleId) : new Article();
    }
}
