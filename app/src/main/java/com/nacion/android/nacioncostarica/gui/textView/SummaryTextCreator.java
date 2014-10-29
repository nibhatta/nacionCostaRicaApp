package com.nacion.android.nacioncostarica.gui.textView;

import android.content.Context;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;

/**
 * Created by Gustavo Matarrita on 23/10/2014.
 */
public class SummaryTextCreator {
    private TextView text;
    private Fonts fonts;

    public SummaryTextCreator(Context context){
        fonts = Fonts.getInstance(context);
    }

    public SummaryTextCreator buildText(TextView text){
        this.text = text;
        return this;
    }

    public TextView withTimesNewRoman(){
        text.setTypeface(fonts.TIMES_NEW_ROMAN);
        return text;
    }

    public TextView withOpenSansRegular(){
        text.setTypeface(fonts.OPEN_SANS_REGULAR);
        return text;
    }
}
