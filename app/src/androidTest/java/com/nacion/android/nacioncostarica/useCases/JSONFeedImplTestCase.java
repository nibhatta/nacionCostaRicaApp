package com.nacion.android.nacioncostarica.useCases;

import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.nacion.android.nacioncostarica.NacionCostaRicaActivity;
import com.nacion.android.nacioncostarica.constants.NacionConstants;

import junit.framework.TestCase;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class JSONFeedImplTestCase extends AndroidTestCase {
    private JSONFeed jsonFeed;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        jsonFeed = new JSONFeedImpl();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCaseReadJSONFeedExpectedSuccess(){
        String json = jsonFeed.readJSONFeed(NacionConstants.JSON_URL);
        assertTrue(json != null && !json.isEmpty());
    }
}
