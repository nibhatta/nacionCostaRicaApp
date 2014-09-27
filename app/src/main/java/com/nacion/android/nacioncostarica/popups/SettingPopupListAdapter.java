package com.nacion.android.nacioncostarica.popups;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.holders.Section;
import com.nacion.android.nacioncostarica.holders.Setting;

import java.util.List;

import static com.nacion.android.nacioncostarica.constants.NacionConstants.SECTION_DISABLE;
import static com.nacion.android.nacioncostarica.constants.NacionConstants.SECTION_ENABLE;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class SettingPopupListAdapter extends ArrayAdapter<Setting> {
    private LayoutInflater inflater;
    private Context context;

    public SettingPopupListAdapter(Context context, List<Setting> argContents) {
        super(context, R.layout.item_setting, argContents);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Setting Setting = getItem(position);
        int codeType = getItemViewType(position);
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_setting, null);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }


    private class ViewHolder{
        ViewPager viewPager;
    }
}
