package com.nacion.android.nacioncostarica.gui.textView;

import android.content.Context;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;

/**
 * Created by Gustavo Matarrita on 23/10/2014.
 */
public class TitleTextView {
    private TextView text;
    private Fonts fonts;

    public TitleTextView(Context context, TextView textView){
        fonts = Fonts.getInstance(context);
        text = textView;
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
