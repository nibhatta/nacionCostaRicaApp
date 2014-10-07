package com.nacion.android.nacioncostarica.useCases;

import android.test.AndroidTestCase;

import com.nacion.android.nacioncostarica.models.Article;
import com.nacion.android.nacioncostarica.models.Board;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.models.ContentModule;
import com.nacion.android.nacioncostarica.models.Data;
import com.nacion.android.nacioncostarica.models.Gallery;
import com.nacion.android.nacioncostarica.models.Image;
import com.nacion.android.nacioncostarica.models.Module;
import com.nacion.android.nacioncostarica.models.Site;
import com.nacion.android.nacioncostarica.models.Weight;

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

    private void validateArticlesExpectedSuccess(Site argSite){
        Map<Integer, Article> articleMap = argSite.getArticles();
        assertNotNull(articleMap);
        Set<Integer> keys = articleMap.keySet();
        for(Integer key:keys){
            Article article = articleMap.get(key);
            assertNotNull(article);
            assertTrue(article.getTitle() != null && !article.getTitle().isEmpty());
            validateImageFromArticleExpectedSuccess(article.getImage());
            validateWeightExpectedSuccess(article.getWeights());
        }
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

    private void validateWeightExpectedSuccess(List<Weight> argWeights){
        assertNotNull(argWeights);
        for(Weight weight : argWeights){
            assertNotNull(weight);
            assertTrue(weight.getType() != null && !weight.getType().isEmpty());
            validateDataExpectedSuccess(weight.getData());
        }
    }

    private void validateImageListExpectedSuccess(List<Image> argImages){
        assertNotNull(argImages);
        for(Image image : argImages){
            validateImageFromArticleExpectedSuccess(image);
        }
    }

    private void validateDataExpectedSuccess(Data argData){
        assertNotNull(argData);
        assertTrue(argData.getId() != null && !argData.getId().isEmpty());
        assertTrue(argData.getCaption() != null && !argData.getCaption().isEmpty());
        assertTrue(argData.getUrl() != null && !argData.getUrl().isEmpty());
        assertTrue(argData.getAssetId() != null && !argData.getAssetId().isEmpty());
        assertTrue(argData.getEmbed() != null && !argData.getEmbed().isEmpty());
    }

    private void validateImageFromArticleExpectedSuccess(Image argImage){
        assertNotNull(argImage);
        assertTrue(argImage.getPhoneUrl() != null && !argImage.getPhoneUrl().isEmpty());
        assertTrue(argImage.getTabletPortraitUrl() != null && !argImage.getTabletPortraitUrl().isEmpty());
        assertTrue(argImage.getTabletLandscapeUrl() != null && !argImage.getTabletLandscapeUrl().isEmpty());
        assertTrue(argImage.getCaption() != null && !argImage.getCaption().isEmpty());
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
        assertEquals(size, argModule.getCount());
        for(Content content : contents){
            assertTrue(content.getId() > 0);
            assertTrue(content.getTitle() != null && !content.getTitle().isEmpty());
            validateImageExpectedSuccess(content.getImage());
        }
    }

    private void validateImageExpectedSuccess(Image argImage){
        assertNotNull(argImage);
        assertTrue(argImage.getPhoneUrl() != null && !argImage.getPhoneUrl().isEmpty());
        assertTrue(argImage.getTabletUrl() != null && !argImage.getTabletUrl().isEmpty());
    }

    private String getJSONString(){
        String urlToTest = "http://192.168.205.34/perfilformulario/appNativoNacion.json";
        return jsonFeed.readJSONFeed(urlToTest);
    }
}
