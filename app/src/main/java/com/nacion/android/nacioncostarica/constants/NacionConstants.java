package com.nacion.android.nacioncostarica.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gustavo Matarrita on 23/09/2014.
 */
public class NacionConstants {
    public final static int HOME_FRAGMENT_INDEX = 0;

    public final static String IMG = "IMG";
    public final static String PGL = "PGL";

    //public final static String JSON_URL = "http://192.168.205.34/perfilformulario/appNativoNacion.json";
    public final static String IFRAME_FOR_TESTING ="<html><body><iframe width=\"250\" height=\"250\" src=\"http://www.youtube.com/embed/hv2NEW0uC1o\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
    public final static String EMPTY_STRING = "";
    public final static String JSON_URL = "http://media.nacion.com/secure/apps/lanacion/tools/appNativoNacion.json";
    public final static String ADVERTISEMENT_URL = "http://www.aldia.cr/gnfactory/especiales/2014/brasil2014/futbolAlDia/appPublicidad.html";

    public final static String MODULE_ONE = "highlight";
    public final static String MODULE_TWO = "grid";
    public final static String MODULE_THREE = "multimedia";
    public final static String MODULE_FOURTH = "break";
    public final static String MODULE_FIVE = "gridOrder";
    public final static String MODULE_SIX = "BlockB";
    public final static String MODULE_SEVEN = "SpecialData";
    public final static String MODULE_EIGHT = "more";
    public final static String MODULE_NINE = "lastMinute";
    public final static String MODULE_TEN = "list";
    public final static String MODULE_ELEVEN = "bloqueEnfoque";

    public final static int MODULE_CODE_ONE = 0;
    public final static int MODULE_CODE_TWO = 1;
    public final static int MODULE_CODE_THREE = 2;
    public final static int MODULE_CODE_FOURTH = 3;
    public final static int MODULE_CODE_FIVE = 4;
    public final static int MODULE_CODE_SIX = 5;
    public final static int MODULE_CODE_SEVEN = 6;
    public final static int MODULE_CODE_EIGHT = 7;
    public final static int MODULE_CODE_NINE = 8;
    public final static int MODULE_CODE_TEN = 9;
    public final static int MODULE_CODE_ELEVEN = 10;

    public final static int SECTION_DISABLE = 0;
    public final static int SECTION_ENABLE = 1;

    public final static Map<String, Integer> MODULES = new HashMap<String, Integer>(){
        {
            put(MODULE_ONE, MODULE_CODE_ONE);
            put(MODULE_TWO, MODULE_CODE_TWO);
            put(MODULE_THREE, MODULE_CODE_THREE);
            put(MODULE_FOURTH, MODULE_CODE_FOURTH);
            put(MODULE_FIVE, MODULE_CODE_FIVE);
            put(MODULE_SIX, MODULE_CODE_SIX);
            put(MODULE_SEVEN, MODULE_CODE_SEVEN);
            put(MODULE_EIGHT, MODULE_CODE_EIGHT);
            put(MODULE_NINE, MODULE_CODE_NINE);
            put(MODULE_TEN, MODULE_CODE_TEN);
            put(MODULE_ELEVEN, MODULE_CODE_ELEVEN);
        }
    };

    public final static String ARTICLE_HIGHLIGHT = "article";
    public final static String ARTICLE_PARAGRAPH = "paragraph";
    public final static String ARTICLE_WEIGHT_IMG = "IMG";
    public final static String ARTICLE_RELATED = "related";
    public final static String ARTICLE_WEIGHT_PGL = "PGL";

    public final static int ARTICLE_HIGHLIGHT_CODE = 0;
    public final static int ARTICLE_PARAGRAPH_CODE = 1;
    public final static int ARTICLE_WEIGHT_IMG_CODE = 2;
    public final static int ARTICLE_RELATED_CODE = 3;
    public final static int ARTICLE_WEIGHT_PGL_CODE = 4;


    public final static Map<String, Integer> ARTICLE_PARTS = new HashMap<String, Integer>(){
        {
            put(ARTICLE_HIGHLIGHT, ARTICLE_HIGHLIGHT_CODE);
            put(ARTICLE_PARAGRAPH, ARTICLE_PARAGRAPH_CODE);
            put(ARTICLE_WEIGHT_IMG, ARTICLE_WEIGHT_IMG_CODE);
            put(ARTICLE_RELATED, ARTICLE_RELATED_CODE);
            put(ARTICLE_WEIGHT_PGL, ARTICLE_WEIGHT_PGL_CODE);

        }
    };
}




