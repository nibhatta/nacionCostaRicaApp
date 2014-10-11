package com.nacion.android.nacioncostarica.content;

import com.nacion.android.nacioncostarica.models.IArticleContentItemList;
import com.nacion.android.nacioncostarica.models.Site;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 10/10/2014.
 */
public interface IContentPresenter {
    Site getSite();
    void setSite(Site argSite);
    List<IArticleContentItemList> getArticleContentFromView(int argArticleId);
    boolean articleNotExistsFromView(int argArticleId);
}
