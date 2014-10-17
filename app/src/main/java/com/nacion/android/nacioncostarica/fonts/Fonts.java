package com.nacion.android.nacioncostarica.fonts;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Gustavo Matarrita on 16/10/2014.
 */
public class Fonts {
    private static Fonts instance;
    private static Context context;
    public static Typeface ADELE_THIN_ITALIC;

    public static Fonts getInstance(Context argContext){
        if(instance == null){
            instance = new Fonts();
            instance.context = argContext;
            instance.setupFonts();
        }
        return instance;
    }

    private Fonts(){};

    private void setupFonts(){
        ADELE_THIN_ITALIC = Typeface.createFromAsset(context.getAssets(), "fonts/Adelle_ThinItalic.ttf");
    }
}
