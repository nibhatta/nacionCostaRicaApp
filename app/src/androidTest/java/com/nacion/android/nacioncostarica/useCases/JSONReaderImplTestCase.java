package com.nacion.android.nacioncostarica.useCases;

import android.test.AndroidTestCase;

import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.models.Article;
import com.nacion.android.nacioncostarica.models.Board;
import com.nacion.android.nacioncostarica.models.Body;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.models.ContentModule;
import com.nacion.android.nacioncostarica.models.Cover;
import com.nacion.android.nacioncostarica.models.Data;
import com.nacion.android.nacioncostarica.models.Gallery;
import com.nacion.android.nacioncostarica.models.Image;
import com.nacion.android.nacioncostarica.models.Module;
import com.nacion.android.nacioncostarica.models.Related;
import com.nacion.android.nacioncostarica.models.Site;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class JSONReaderImplTestCase extends AndroidTestCase {
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

    public void testCaseCreateObjectsFromJSONStringExpectedSuccess(){
        String json = getJSONString();
        assertTrue(json != null && !json.isEmpty());
        Site site = jsonReader.createObjectsFromJSONString(json);
        assertNotNull(site.getTimestamp());
        assertNotNull(site.getBoardNames());
        validateBoardsExpectedSuccess(site);
        validateArticlesExpectedSuccess(site);
        validateGalleriesExpectedSuccess(site);
    }

    private void validateGalleriesExpectedSuccess(Site argSite){
        Map<Integer, Gallery> galleryMap = argSite.getGalleries();
        assertNotNull(galleryMap);
        Set<Integer> keys = galleryMap.keySet();
        for(Integer key:keys){
            Gallery gallery = galleryMap.get(key);
            assertNotNull(gallery);
            assertTrue(gallery.getId() > 0);
            validateImageListExpectedSuccess(gallery.getImages());
        }
    }

    private void validateImageListExpectedSuccess(List<Image> argImages){
        assertNotNull(argImages);
        for(Image image : argImages){
            validateImageFromArticleExpectedSuccess(image);
        }
    }

    private void validateImageFromArticleExpectedSuccess(Image argImage){
        assertNotNull(argImage);
        assertTrue(argImage.getPhoneUrl() != null && !argImage.getPhoneUrl().isEmpty());
        assertTrue(argImage.getTabletPortraitUrl() != null && !argImage.getTabletPortraitUrl().isEmpty());
        assertTrue(argImage.getTabletLandscapeUrl() != null && !argImage.getTabletLandscapeUrl().isEmpty());
        assertTrue(argImage.getCaption() != null && !argImage.getCaption().isEmpty());
    }

    private void validateArticlesExpectedSuccess(Site argSite){
        Map<Integer, Article> articleMap = argSite.getArticles();
        assertNotNull(articleMap);
        Set<Integer> keys = articleMap.keySet();
        for(Integer key:keys){
            Article article = articleMap.get(key);
            assertNotNull(article);
            assertTrue(article.getTitle() != null && !article.getTitle().isEmpty());
            validateCoverFromArticleExpectedSuccess(article.getCover());
            validateBodyExpectedSuccess(article.getBody());
            validateRelatedExpectedSuccess(article.getRelated());
        }
    }

    private void validateCoverFromArticleExpectedSuccess(Cover cover){
        assertNotNull(cover);
        assertNotNull(cover.getContentType());
        assertTrue(cover.getDataAre() != null && !cover.getDataAre().isEmpty());
        for(Data data : cover.getDataAre()){
            validateDataFromCoverExpectedSuccess(data);
        }
    }

    private void validateBodyExpectedSuccess(List<Body> bodies){
        assertNotNull(bodies);
        for(Body body : bodies){
            assertNotNull(body);
            assertTrue(body.getType() != null && !body.getType().isEmpty());
            if(Body.DATA_OBJECT_TYPES.contains(body.getType())) {
                validateDataFromCoverExpectedSuccess(body.getDataObj());
            }else{
                assertTrue(body.getDataStr() != null && !body.getDataStr().isEmpty());
            }
        }
    }

    private void validateRelatedExpectedSuccess(List<Related> relatedArray){
        assertNotNull(relatedArray);
        for(Related related : relatedArray){
            assertNotNull(related);
            assertNotNull(related.getTimestamp());
            assertNotNull(related.getTitle());
            assertNotNull(related.getSummary());
            assertNotNull(related.getSection());
            assertNotNull(related.getSubSection());
        }
    }

    private void validateDataFromCoverExpectedSuccess(Data data){
        assertNotNull(data);
        assertTrue(data.getCaption() != null && !data.getCaption().isEmpty());
        assertTrue(data.getCredit() != null && !data.getCredit().isEmpty());
        assertTrue(data.getTablet() != null && !data.getTablet().isEmpty());
        assertTrue(data.getPhone() != null && !data.getPhone().isEmpty());
    }

    public void validateBoardsExpectedSuccess(Site argSite){
        assertFalse(argSite.getBoardNames().isEmpty());
        assertEquals(argSite.getBoards().size(), 2);
        validateBoardListExpectedSuccess(argSite.getBoards());
    }

    private void validateBoardListExpectedSuccess(List<Board> argBoards){
        assertNotNull(argBoards);
        for(Board board : argBoards){
            assertNotNull(board);
            assertTrue(board.getId() != 0);
            validateModuleListExpectedSuccess(board);
        }
    }

    private void validateModuleListExpectedSuccess(Board argBoard){
        int minCountModules = 10;
        int count = argBoard.getModules().size();
        assertTrue(count > minCountModules);
        for(Module module : argBoard.getModules()){
            assertNotNull(module);
            assertNotNull(module.getType());
            assertTrue(module.getOrder() > 0);
            validateContentModuleExpectedSuccess(module);
            validateContentsExpectedSuccess(module);
        }
    }

    private void validateContentModuleExpectedSuccess(Module argModule){
        ContentModule contentModule = argModule.getContentModule();
        if(contentModule != null) {
            assertNotNull(contentModule);
        }
    }

    private void validateContentsExpectedSuccess(Module argModule){
        if(argModule.getContents() == null || argModule.getContents().isEmpty()){
            return;
        }
        List<Content> contents = argModule.getContents();
        int size = contents.size();
        assertNotNull(contents);
        assertFalse(contents.isEmpty());
        //TODO Errors on JSON file.
        //assertEquals(size, argModule.getCount());
        for(Content content : contents){
            assertTrue(content.getId() > 0);
            assertTrue(content.getTitle() != null && !content.getTitle().isEmpty());
            validateImageExpectedSuccess(content.getImage());
        }
    }

    private void validateImageExpectedSuccess(Image argImage){
        assertNotNull(argImage);
        assertNotNull(argImage.getPhoneUrl());
        assertNotNull(argImage.getTabletUrl());
    }

    private String getJSONString(){
        return jsonFeed.readJSONFeed(NacionConstants.JSON_URL);
    }
}
