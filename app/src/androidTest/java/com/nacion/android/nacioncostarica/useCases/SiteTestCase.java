package com.nacion.android.nacioncostarica.useCases;

import android.test.AndroidTestCase;

import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.models.Article;
import com.nacion.android.nacioncostarica.models.Board;
import com.nacion.android.nacioncostarica.models.ContentItemList;
import com.nacion.android.nacioncostarica.models.Site;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class SiteTestCase extends AndroidTestCase {
    private JSONReader jsonReader;
    private JSONFeed jsonFeed;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        jsonReader = new JSONReaderImpl();
        jsonFeed = new JSONFeedImpl();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCaseGetArticleByIdExpectedSuccess(){
        String json = getJSONString();
        assertTrue(json != null && !json.isEmpty());
        Site site = jsonReader.createObjectsFromJSONString(json);
        int articleId = 115388674;
        Article article = site.getArticleById(articleId);
        assertNotNull(article);
        assertFalse(article.isAEmptyObject());
    }

    private String getJSONString(){
        return jsonFeed.readJSONFeed(NacionConstants.JSON_URL);
    }
}
