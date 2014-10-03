package com.nacion.android.nacioncostarica.useCases;

import android.test.AndroidTestCase;

import com.nacion.android.nacioncostarica.models.Board;
import com.nacion.android.nacioncostarica.models.Module;
import com.nacion.android.nacioncostarica.models.Site;

import java.util.List;
import java.util.Map;

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

    public void testCaseCreateObjectsFromJSONStringReachToSiteExpectedSuccess(){
        String json = getJSONString();
        assertTrue(json != null && !json.isEmpty());
        Site site = jsonReader.createObjectsFromJSONString(json);
        assertNotNull(site.getTimestamp());
        assertNotNull(site.getBoardNames());
    }

    public void testCaseCreateObjectsFromJSONStringReachToBoardsExpectedSuccess(){
        String json = getJSONString();
        assertTrue(json != null && !json.isEmpty());
        Site site = jsonReader.createObjectsFromJSONString(json);
        assertFalse(site.getBoardNames().isEmpty());
        validateBoardList(site.getBoards());
    }

    private void validateBoardList(List<Board> argBoards){
        assertNotNull(argBoards);
        for(Board board : argBoards){
            assertNotNull(board);
            assertTrue(board.getId() != 0);
        }
    }

    public void testCaseCreateObjectsFromJSONStringReachToModulesModulesExpectedSuccess(){
        String json = getJSONString();
        assertTrue(json != null && !json.isEmpty());
        Site site = jsonReader.createObjectsFromJSONString(json);
        assertFalse(site.getBoardNames().isEmpty());
        validateModuleList(site.getBoards());
    }

    private void validateModuleList(List<Board> argBoards){
        for(Board board : argBoards){
            for(Module module : board.getModules()){
                assertNotNull(module);
                assertNotNull(module.getType());
                assertTrue(module.getOrder() > 0);
            }
        }
    }

    private String getJSONString(){
        String urlToTest = "http://192.168.205.34/perfilformulario/appNativoNacion.json";
        return jsonFeed.readJSONFeed(urlToTest);
    }
}
