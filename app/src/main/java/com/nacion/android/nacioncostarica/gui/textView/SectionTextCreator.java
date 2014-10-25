package com.nacion.android.nacioncostarica.gui.textView;

import android.content.Context;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;

/**
 * Created by Gustavo Matarrita on 23/10/2014.
 */
public class SectionTextCreator {
    private TextView text;
    private Fonts fonts;

    public SectionTextCreator(Context context){
        fonts = Fonts.getInstance(context);
    }

    public SectionTextCreator buildText(TextView text){
        this.text = text;
        return this;
    }

    public TextView withOpenSans(){
        return text;
    }

    public TextView withOpenSansBold(){
        return text;
    }
}
