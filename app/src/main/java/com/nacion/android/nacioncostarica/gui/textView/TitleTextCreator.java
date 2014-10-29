package com.nacion.android.nacioncostarica.gui.textView;

import android.content.Context;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;

/**
 * Created by Gustavo Matarrita on 23/10/2014.
 */
public class TitleTextCreator {
    private TextView text;
    private Fonts fonts;

    public TitleTextCreator(Context context){
        fonts = Fonts.getInstance(context);
    }

    public TitleTextCreator buildText(TextView text){
        this.text = text;
        return this;
    }

    public TextView withAdeleBold(){
        text.setTypeface(fonts.ADELE_BOLD);
        return text;
    }

    public TextView withAdeleSemiBold(){
        text.setTypeface(fonts.ADELE_SEMI_BOLD);
        return text;
    }

    public TextView withAdeleExtraBold(){
        text.setTypeface(fonts.ADELE_EXTRA_BOLD);
        return text;
    }

    public TextView withAdeleRegular(){
        text.setTypeface(fonts.ADELE_REGULAR);
        return text;
    }
}
