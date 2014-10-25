package com.nacion.android.nacioncostarica.views.home.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.views.home.holder.HomeViewHolder;
import com.nacion.android.nacioncostarica.models.ContentItemList;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomeListAdapter extends ArrayAdapter<ContentItemList> implements HomeListView {
    private static final int VIEWS_TYPE_COUNT = 8;
    private HomeListPresenter presenter;
    private LayoutInflater inflater;
    private Context context;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;

    public HomeListAdapter(Context context, List<ContentItemList> argContents, FragmentManager argFragmentManager) {
        super(context, R.layout.item_module, argContents);
        this.context = context;
        inflater = LayoutInflater.from(context);
        fragmentManager = argFragmentManager;
    }

    public HomeListPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(HomeListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContentItemList itemList = getItem(position);
        int codeType = getItemViewType(position);
        HomeViewHolder holder;
        if(convertView == null){
            holder = new HomeViewHolder(presenter);
            switch(codeType){
                case NacionConstants.MODULE_CODE_ONE:
                    convertView = inflater.inflate(R.layout.item_module, null);
                    holder.setReferencesForHighlightView(convertView);
                    break;
                case NacionConstants.MODULE_CODE_TWO:
                    convertView = inflater.inflate(R.layout.item_article, null);
                    holder.setReferencesForArticleView(convertView);
                    break;
                case NacionConstants.MODULE_CODE_THREE:
                    convertView = inflater.inflate(R.layout.item_video_gallery, null);
                    holder.setReferencesForVideoGalleryView(convertView);
                    break;
                case NacionConstants.MODULE_CODE_FOURTH:
                    convertView = inflater.inflate(R.layout.item_weather, null);
                    break;
                case NacionConstants.MODULE_CODE_FIVE:

                    convertView = inflater.inflate(R.layout.item_image_gallery, null);

                    //holder.viewPager = (ViewPager)convertView.findViewById(R.id.imageGalleryViewPager);
                    /*
                    fragments = getFragmentsArray();

                    GalleryImagePagerAdapter pagerAdapter = new GalleryImagePagerAdapter(fragmentManager, fragments);
                    if(holder.viewPager.getAdapter() == null) {
                        holder.viewPager.setAdapter(pagerAdapter);
                        holder.viewPager.setOnPageChangeListener();
                        holder.viewPager.setOffscreenPageLimit(4);
                    }*/

                    break;
                case NacionConstants.MODULE_CODE_EIGHT:
                    convertView = inflater.inflate(R.layout.item_more_news, null);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (HomeViewHolder)convertView.getTag();
        }

        setHolderViewValuesByCodeType(holder, itemList, codeType);

        return convertView;
    }

    private void setHolderViewValuesByCodeType(HomeViewHolder holder, ContentItemList item, int codeType){
        switch(codeType){
            case NacionConstants.MODULE_CODE_ONE:
                holder.setValuesForHighlightView(item);
                break;
            case NacionConstants.MODULE_CODE_TWO:
                holder.setValuesForArticleView(item);
                break;
            case NacionConstants.MODULE_CODE_THREE:
                holder.setValuesForVideoGalleryView(item, fragmentManager);
                break;
            case NacionConstants.MODULE_CODE_FOURTH:

                break;
            case NacionConstants.MODULE_CODE_FIVE:

                break;
            case NacionConstants.MODULE_CODE_EIGHT:

                break;
        }
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
