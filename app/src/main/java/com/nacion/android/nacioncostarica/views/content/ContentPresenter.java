package com.nacion.android.nacioncostarica.views.content;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;
import com.nacion.android.nacioncostarica.models.ArticleContentItemList;
import com.nacion.android.nacioncostarica.models.Site;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 10/10/2014.
 */
public interface ContentPresenter {
    Site getSite();
    void setSite(Site argSite);
    List<ArticleContentItemList> getArticleContentFromView(int argArticleId);
    boolean articleNotExistsFromView(int argArticleId);
    Fonts getFonts();
}
