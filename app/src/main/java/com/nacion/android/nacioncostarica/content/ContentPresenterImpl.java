package com.nacion.android.nacioncostarica.content;

import com.nacion.android.nacioncostarica.fonts.Fonts;
import com.nacion.android.nacioncostarica.main.MainView;
import com.nacion.android.nacioncostarica.models.Article;
import com.nacion.android.nacioncostarica.models.ArticleContentItemList;
import com.nacion.android.nacioncostarica.models.Site;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 10/10/2014.
 */
public class ContentPresenterImpl implements IContentPresenter{
    private ContentView contentView;
    private Site site;

    public ContentPresenterImpl(ContentView contentView){
        this.contentView = contentView;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List<ArticleContentItemList> getArticleContentFromView(int argArticleId){
        return site.getArticleContentsForPhone(argArticleId);
    }

    public boolean articleNotExistsFromView(int argArticleId){
        Article article = site.getArticleById(argArticleId);
        return article.isAEmptyObject();
    }

    @Override
    public Fonts getFonts() {
        return contentView.getFontsFromChildrenViews();
    }
}
