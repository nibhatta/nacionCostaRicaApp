package com.nacion.android.nacioncostarica.useCases;

import android.test.AndroidTestCase;

import com.nacion.android.nacioncostarica.models.Article;
import com.nacion.android.nacioncostarica.models.Board;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.models.ContentItemList;
import com.nacion.android.nacioncostarica.models.ContentModule;
import com.nacion.android.nacioncostarica.models.Data;
import com.nacion.android.nacioncostarica.models.Gallery;
import com.nacion.android.nacioncostarica.models.Image;
import com.nacion.android.nacioncostarica.models.Module;
import com.nacion.android.nacioncostarica.models.Site;
import com.nacion.android.nacioncostarica.models.Weight;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class BoardTestCase extends AndroidTestCase {
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

    public void testCaseGetAllContentsForPhoneExpectedSuccess(){
        String json = getJSONString();
        assertTrue(json != null && !json.isEmpty());
        Site site = jsonReader.createObjectsFromJSONString(json);
        for(Board board : site.getBoards()){
            List<ContentItemList> contentForPhone = board.getAllContentsForPhoneDevice();
            validateContentForPhone(contentForPhone);
        }
    }

    private void validateContentForPhone(List<ContentItemList> argContentForPhone){
        for(ContentItemList item:argContentForPhone){
            assertNotNull(item.getModule());
            assertTrue(item.getModule().isContentToDisplayOnPhone());
        }
    }

    private String getJSONString(){
        String urlToTest = "http://192.168.205.34/perfilformulario/appNativoNacion.json";
        return jsonFeed.readJSONFeed(urlToTest);
    }
}
