package com.nacion.android.nacioncostarica.popups;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.R;
import static com.nacion.android.nacioncostarica.constants.NacionConstants.*;
import com.nacion.android.nacioncostarica.holders.Section;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class SectionPopupListAdapter extends ArrayAdapter<Section> {
    private static final int VIEWS_TYPE_COUNT = 2;
    private LayoutInflater inflater;
    private Context context;

    public SectionPopupListAdapter(Context context, List<Section> argContents) {
        super(context, R.layout.item_section_enable, argContents);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Section section = getItem(position);
        int codeType = getItemViewType(position);
        ViewHolder holder;
        if(convertView == null){

            holder = new ViewHolder();
            switch(codeType){
                case SECTION_ENABLE:
                    convertView = inflater.inflate(R.layout.item_section_enable, null);
                    break;
                case SECTION_DISABLE:
                    convertView = inflater.inflate(R.layout.item_section_disable, null);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolder{
        ViewPager viewPager;


    }

    @Override
    public int getViewTypeCount() {
        return VIEWS_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        Section section = getItem(position);
        return section.isEnable() ? SECTION_ENABLE : SECTION_DISABLE;
    }
}
