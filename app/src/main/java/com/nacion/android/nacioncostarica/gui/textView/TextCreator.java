package com.nacion.android.nacioncostarica.gui.textView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.gui.fonts.Fonts;

import org.w3c.dom.Text;

/**
 * Created by Gustavo Matarrita on 23/10/2014.
 */
public class TextCreator {
    private final TextView text;

    public static class Builder{
        private TextView text;
        private int size;
        private Typeface typeface;

        public Builder(TextView text){
            this.text = text;
        }

        public Builder size(int size){
            this.size = size;
            this.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.size);
            return this;
        }

        public Builder typeface(Typeface typeface){
            this.typeface = typeface;
            this.text.setTypeface(this.typeface);
            return this;
        }

        public TextView build(){
            return new TextCreator(this).text;
        }
    }

    private TextCreator(Builder builder){
        text = builder.text;
    }
}
