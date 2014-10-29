package com.nacion.android.nacioncostarica.gui.fonts;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Gustavo Matarrita on 16/10/2014.
 */
public class Fonts {
    private static Fonts instance;
    private static Context context;
    public static Typeface ADELE_THIN_ITALIC;
    public static Typeface ADELE_BOLD;
    public static Typeface TIMES_NEW_ROMAN;
    public static Typeface ADELE_SEMI_BOLD;
    public static Typeface ADELE_REGULAR;
    public static Typeface ADELE_EXTRA_BOLD;
    public static Typeface OPEN_SANS_REGULAR;
    public static Typeface OPEN_SANS_BOLD;

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
        ADELE_THIN_ITALIC = Typeface.createFromAsset(context.getAssets(), "fonts/Adelle_ThinItalic.otf");
        ADELE_BOLD = Typeface.createFromAsset(context.getAssets(), "fonts/Adelle_Bold.otf");
        ADELE_EXTRA_BOLD = Typeface.createFromAsset(context.getAssets(), "fonts/Adelle_ExtraBold.otf");
        ADELE_REGULAR = Typeface.createFromAsset(context.getAssets(), "fonts/Adelle_Regular.otf");
        TIMES_NEW_ROMAN = Typeface.createFromAsset(context.getAssets(), "fonts/times.ttf");
        ADELE_SEMI_BOLD = Typeface.createFromAsset(context.getAssets(), "fonts/Adelle_Semibold.otf");
        OPEN_SANS_REGULAR = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Regular.ttf");
        OPEN_SANS_BOLD = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Bold.ttf");
    }
}
