package com.nacion.android.nacioncostarica.gui.textView;

import android.content.Context;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;

/**
 * Created by Gustavo Matarrita on 23/10/2014.
 */
public class BodyTextCreator {
    private TextView text;
    private Fonts fonts;

    public BodyTextCreator(Context context){
        fonts = Fonts.getInstance(context);
    }

    public BodyTextCreator buildText(TextView text){
        this.text = text;
        return this;
    }

    public TextView withTimesNewRoman(){
        text.setTypeface(fonts.TIMES_NEW_ROMAN);
        return text;
    }

    public TextView withAdeleBold(){
        text.setTypeface(fonts.ADELE_BOLD);
        return text;
    }

    public TextView withAdeleSemiBold(){
        text.setTypeface(fonts.ADELE_SEMI_BOLD);
        return text;
    }
}
