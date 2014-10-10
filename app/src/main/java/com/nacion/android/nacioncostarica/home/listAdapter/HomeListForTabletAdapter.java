package com.nacion.android.nacioncostarica.home.listAdapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.NacionFragment;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.home.galleryAdapter.GalleryImagePagerAdapter;
import com.nacion.android.nacioncostarica.home.galleryAdapter.GalleryVideoPagerAdapter;
import com.nacion.android.nacioncostarica.home.galleryAdapter.ImageFragment;
import com.nacion.android.nacioncostarica.home.galleryAdapter.VideoFragment;
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
    private Context mContext;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;

    private Integer[] mImageIds = {
            R.drawable.ic_launcher,
            R.drawable.plus_icon,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher
    };

    public HomeListForTabletAdapter(Context context, List<ContentItemList> argContents, FragmentManager argFragmentManager) {
        super(context, R.layout.item_module, argContents);
        mContext = context;
        inflater = LayoutInflater.from(context);
        fragmentManager = argFragmentManager;
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
                    holder.viewPager = (ViewPager)convertView.findViewById(R.id.videoGalleryViewPager);
                    GalleryVideoPagerAdapter videoPagerAdapter = new GalleryVideoPagerAdapter(fragmentManager, getVideoFragmentsArray());
                    if(holder.viewPager.getAdapter() == null) {
                        holder.viewPager.setAdapter(videoPagerAdapter);
                        holder.viewPager.setOffscreenPageLimit(4);
                    }
                    break;
                case NacionConstants.MODULE_CODE_FOURTH:
                    convertView = inflater.inflate(R.layout.item_weather, null);
                    break;
                case NacionConstants.MODULE_CODE_FIVE:

                    convertView = inflater.inflate(R.layout.item_image_gallery, null);

                    holder.viewPager = (ViewPager)convertView.findViewById(R.id.imageGalleryViewPager);
                    GalleryImagePagerAdapter pagerAdapter = new GalleryImagePagerAdapter(fragmentManager, getFragmentsArray());
                    if(holder.viewPager.getAdapter() == null) {
                        holder.viewPager.setAdapter(pagerAdapter);
                        holder.viewPager.setOffscreenPageLimit(4);
                    }

                    break;
                case NacionConstants.MODULE_CODE_SIX:
                    convertView = inflater.inflate(R.layout.item_more_news, null);
                    break;

                case NacionConstants.MODULE_CODE_SEVEN:
                    convertView = inflater.inflate(R.layout.item_articles_ad, null);
                    break;

                case NacionConstants.MODULE_CODE_EIGHT:
                    convertView = inflater.inflate(R.layout.item_health, null);
                    break;

                case NacionConstants.MODULE_CODE_NINE:
                    convertView = inflater.inflate(R.layout.item_ad, null);
                    break;

                case NacionConstants.MODULE_CODE_TEN:
                    convertView = inflater.inflate(R.layout.item_entertainment, null);
                    break;

                case NacionConstants.MODULE_CODE_ELEVEN:
                    convertView = inflater.inflate(R.layout.item_special_data, null);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        if(codeType == NacionConstants.MODULE_CODE_FIVE){

        }



        return convertView;
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
        ContentItemList content = getItem(position);
        return content.getModule().getTypeCode();
    }
}
