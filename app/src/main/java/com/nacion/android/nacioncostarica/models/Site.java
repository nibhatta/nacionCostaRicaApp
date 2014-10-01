package com.nacion.android.nacioncostarica.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class Site{
    private List<Board> boards;
    private List<Article> articles;
    private ImageGallery imageGallery;
    private VideoGallery videoGallery;

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
}
