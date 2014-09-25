package com.nacion.android.nacioncostarica.home.listAdapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.models.ContentItemList;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomeListAdapter extends ArrayAdapter<ContentItemList> implements HomeListView {
    private static final int VIEWS_TYPE_COUNT = 6;
    private HomeListPresenter presenter;
    private LayoutInflater inflater;
    private FragmentManager fragmentManager;
    private Context context;

    public HomeListAdapter(Context context, List<ContentItemList> argContents) {
        super(context, R.layout.item_module, argContents);
        fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
        presenter = new HomeListPresenterImpl(this);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContentItemList itemList = getItem(position);
        int codeType = getItemViewType(position);
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            switch(codeType){
                case NacionConstants.MODULE_CODE_ONE:
                    convertView = inflater.inflate(R.layout.item_module, null);
                    break;
                case NacionConstants.MODULE_CODE_TWO:
                    convertView = inflater.inflate(R.layout.item_article, null);
                    break;
                case NacionConstants.MODULE_CODE_THREE:
                    convertView = inflater.inflate(R.layout.item_video_gallery, null);
                    break;
                case NacionConstants.MODULE_CODE_FOURTH:
                    convertView = inflater.inflate(R.layout.item_weather, null);
                    break;
                case NacionConstants.MODULE_CODE_FIVE:
                    convertView = inflater.inflate(R.layout.item_image_gallery, null);
                    break;
                case NacionConstants.MODULE_CODE_SIX:
                    convertView = inflater.inflate(R.layout.item_more_news, null);
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
        ContentItemList content = getItem(position);
        return content.getModule().getTypeCode();
    }
}
