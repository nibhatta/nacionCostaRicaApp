package com.nacion.android.nacioncostarica.home.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.NacionFragment;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.home.fragments.ImageFragment;
import com.nacion.android.nacioncostarica.home.holder.HomeTabletViewHolder;
import com.nacion.android.nacioncostarica.models.ContentItemList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomeListForTabletAdapter extends ArrayAdapter<ContentItemList> implements HomeListView {
    private static final int VIEWS_TYPE_COUNT = 11;
    private HomeListPresenter presenter;
    private LayoutInflater inflater;
    private Context context;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;

    public HomeListForTabletAdapter(Context argContext, List<ContentItemList> argContents, FragmentManager argFragmentManager) {
        super(argContext, R.layout.item_module, argContents);
        context = argContext;
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
        HomeTabletViewHolder holder;
        if(convertView == null){
            holder = new HomeTabletViewHolder(presenter);
            switch(codeType){
                case NacionConstants.MODULE_CODE_ONE:
                    convertView = inflater.inflate(R.layout.item_module, null);
                    holder.setComponentsReferencesForHighlightView(convertView);
                    break;
                case NacionConstants.MODULE_CODE_TWO:
                    convertView = inflater.inflate(R.layout.item_article, null);
                    holder.setComponentsReferencesForArticleView(convertView);
                    break;
                case NacionConstants.MODULE_CODE_THREE:
                    convertView = inflater.inflate(R.layout.item_video_gallery, null);
                    holder.setComponentsReferencesForVideoGalleryView(convertView, itemList, fragmentManager);
                    break;
                case NacionConstants.MODULE_CODE_FOURTH:
                    convertView = inflater.inflate(R.layout.item_weather, null);
                    break;

                case NacionConstants.MODULE_CODE_SIX:
                    convertView = inflater.inflate(R.layout.item_entertainment, null);
                    /*
                    holder.viewPager = (ViewPager)convertView.findViewById(R.id.imageGalleryViewPager);
                    GalleryImagePagerAdapter pagerAdapter = new GalleryImagePagerAdapter(fragmentManager, getFragmentsArray());
                    if(holder.viewPager.getAdapter() == null) {
                        holder.viewPager.setAdapter(pagerAdapter);
                        holder.viewPager.setOffscreenPageLimit(4);
                    }*/

                    break;
                case NacionConstants.MODULE_CODE_SEVEN:
                    convertView = inflater.inflate(R.layout.item_special_data, null);
                    break;

                case NacionConstants.MODULE_CODE_EIGHT:
                    convertView = inflater.inflate(R.layout.item_more_news, null);
                    break;

                case NacionConstants.MODULE_CODE_NINE:
                    convertView = inflater.inflate(R.layout.item_ad, null);
                    break;

                case NacionConstants.MODULE_CODE_TEN:
                    convertView = inflater.inflate(R.layout.item_ad, null);
                    break;

                case NacionConstants.MODULE_CODE_ELEVEN:
                    convertView = inflater.inflate(R.layout.item_health, null);
                    break;
            }
            if(convertView != null) {
                convertView.setTag(holder);
            }
        }else{
            holder = (HomeTabletViewHolder)convertView.getTag();
        }

        setHolderViewValuesByCodeType(holder, itemList, codeType);

        return convertView;
    }

    private void setHolderViewValuesByCodeType(HomeTabletViewHolder argHolder, ContentItemList argItem, int argCodeType){
        switch(argCodeType){
            case NacionConstants.MODULE_CODE_ONE:
                argHolder.setValuesForHighlightView(argItem, context);
                break;
            case NacionConstants.MODULE_CODE_TWO:
                argHolder.setValuesForArticleView(context, argItem);
                break;
            case NacionConstants.MODULE_CODE_THREE:
                //argHolder.setViewHolderValuesForVideoGalleryView();
                break;
            case NacionConstants.MODULE_CODE_FOURTH:

                break;
            case NacionConstants.MODULE_CODE_FIVE:

                break;
            case NacionConstants.MODULE_CODE_EIGHT:

                break;
        }
    }

    private List<NacionFragment> getFragmentsArray(){
        List<NacionFragment> fragments = new ArrayList<NacionFragment>();
        fragments.add(new ImageFragment().getInstance(0));
        fragments.add(new ImageFragment().getInstance(1));
        fragments.add(new ImageFragment().getInstance(2));
        fragments.add(new ImageFragment().getInstance(3));
        return fragments;
    }

    private List<NacionFragment> getVideoFragmentsArray(){
        List<NacionFragment> fragments = new ArrayList<NacionFragment>();
        /*
        fragments.add(new VideoFragment().getInstance(0));
        fragments.add(new VideoFragment().getInstance(1));
        fragments.add(new VideoFragment().getInstance(2));
        fragments.add(new VideoFragment().getInstance(3));
        */
        return fragments;
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
        Log.d(HomeListForTabletAdapter.class.getName(), "=====> Position: " + position);
        ContentItemList content = getItem(position);
        if(content.getModule() == null){
            throw new RuntimeException("=====> The module is null!!!!");
        }
        return content.getModule().getTypeCode();
    }
}
