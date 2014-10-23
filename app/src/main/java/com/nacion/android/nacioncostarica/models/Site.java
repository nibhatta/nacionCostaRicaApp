package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import com.nacion.android.nacioncostarica.constants.NacionConstants;

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
            String keyStr = values[key].replaceAll("\"", "").replaceAll("\\{", "").replaceAll("\\}", "");
            String valueStr = values[value].replaceAll("\"", "").replaceAll("\\{", "").replaceAll("\\}", "");
            boardNames.put(keyStr, valueStr);
        }
        return boardNames;
    }

    public List<ArticleContentItemList> getArticleContentsForPhone(int argArticleId){
        List<ArticleContentItemList> articleContents = new ArrayList<ArticleContentItemList>();
        Article article = getArticleById(argArticleId);
        article.setType(NacionConstants.ARTICLE_HIGHLIGHT);
        articleContents.add(article);
        for(Body body : article.getBody()){
            articleContents.add(body);
        }
        for(Related related : article.getRelated()){
            related.setType(NacionConstants.ARTICLE_RELATED);
            articleContents.add(related);
        }
        return articleContents;
    }

    public Article getArticleById(int argArticleId){
        return articles.containsKey(argArticleId)? articles.get(argArticleId) : new Article();
    }
}

