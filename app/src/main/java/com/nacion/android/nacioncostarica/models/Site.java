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
import java.util.Set;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class Site{

    private Date timestamp;
    private Map<String, String> boardNames;
    private List<Board> boards;
    private List<Article> articles;
    private ImageGallery imageGallery;
    private VideoGallery videoGallery;

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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public ImageGallery getImageGallery() {
        return imageGallery;
    }

    public void setImageGallery(ImageGallery imageGallery) {
        this.imageGallery = imageGallery;
    }

    public VideoGallery getVideoGallery() {
        return videoGallery;
    }

    public void setVideoGallery(VideoGallery videoGallery) {
        this.videoGallery = videoGallery;
    }

    public static Site createDummyCoverCoreForPhone(){
        Site cover = new Site();
        cover.boards = new ArrayList<Board>();
        cover.boards.add(Board.createDummyBoardCoreForPhone());
        return cover;
    }

    public static Site createDummyCoverCoreForTablet(){
        Site cover = new Site();
        cover.boards = new ArrayList<Board>();
        cover.boards.add(Board.createDummyBoardCoreForTablet());
        return cover;
    }

    public Site buildSiteFromJSONObject(JSONObject argJSONObject) {
        Site site = new Site();
        try{
            String timestamp = argJSONObject.getString("timestamp");
            String boardNames = argJSONObject.getString("boardNames");
            JSONArray boards = argJSONObject.getJSONArray("boards");
            site.timestamp = convertStringToTimestamp(timestamp);
            site.boardNames = createBoardNamesMap(boardNames);
            site.boards = new Board().buildBoardListFromJSONObject(boards);
        }catch(JSONException e){
            Log.d(Site.class.getName(), e.getLocalizedMessage());
        }
        return site;
    }

    private Date convertStringToTimestamp(String argTimestampString){
        int timestamp = Integer.parseInt(argTimestampString);
        return new Date(timestamp);
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
}
