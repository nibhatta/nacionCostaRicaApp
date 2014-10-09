package com.nacion.android.nacioncostarica.home.listAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nacion.android.nacioncostarica.NacionFragment;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.home.galleryAdapter.GalleryImageAdapter;
import com.nacion.android.nacioncostarica.home.galleryAdapter.GalleryImagePagerAdapter;
import com.nacion.android.nacioncostarica.home.galleryAdapter.GalleryVideoPagerAdapter;
import com.nacion.android.nacioncostarica.home.galleryAdapter.ImageFragment;
import com.nacion.android.nacioncostarica.home.galleryAdapter.VideoFragment;
import com.nacion.android.nacioncostarica.home.galleryListener.GalleryOnPageChangeListener;
import com.nacion.android.nacioncostarica.home.holders.HomeViewHolder;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.models.ContentItemList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomeListAdapter extends ArrayAdapter<ContentItemList> implements HomeListView {
    private static final int VIEWS_TYPE_COUNT = 8;
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

    public HomeListAdapter(Context context, List<ContentItemList> argContents, FragmentManager argFragmentManager) {
        super(context, R.layout.item_module, argContents);
        mContext = context;
        presenter = new HomeListPresenterImpl(this);
        inflater = LayoutInflater.from(context);
        fragmentManager = argFragmentManager;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContentItemList itemList = getItem(position);
        int codeType = getItemViewType(position);
        HomeViewHolder holder;
        if(convertView == null){
            holder = new HomeViewHolder();
            switch(codeType){
                case NacionConstants.MODULE_CODE_ONE:
                    convertView = inflater.inflate(R.layout.item_module, null);
                    holder.setViewHolderComponentsReferencesForHighlightView(convertView);
                    break;
                case NacionConstants.MODULE_CODE_TWO:
                    convertView = inflater.inflate(R.layout.item_article, null);
                    holder.setViewHolderComponentsReferencesForArticleView(convertView);
                    break;
                case NacionConstants.MODULE_CODE_THREE:
                    convertView = inflater.inflate(R.layout.item_video_gallery, null);
                    holder.viewPager = (ViewPager)convertView.findViewById(R.id.videoGalleryViewPager);

                    List<Content> contents = itemList.getModule().getContents();
                    int size = contents.size();

                    List<NacionFragment> fragments = getVideoFragmentsArray(contents);

                    GalleryOnPageChangeListener listener = new GalleryOnPageChangeListener(fragments);
                    listener.setTitleViewPager((TextView)convertView.findViewById(R.id.videoTitleTextView));

                    GalleryVideoPagerAdapter videoPagerAdapter = new GalleryVideoPagerAdapter(fragmentManager, fragments);
                    videoPagerAdapter.setTabsCount(size);

                    if(holder.viewPager.getAdapter() == null) {
                        holder.viewPager.setAdapter(videoPagerAdapter);
                        holder.viewPager.setOnPageChangeListener(listener);
                        holder.viewPager.setOffscreenPageLimit(size);
                    }
                    break;
                case NacionConstants.MODULE_CODE_FOURTH:
                    convertView = inflater.inflate(R.layout.item_weather, null);
                    break;
                case NacionConstants.MODULE_CODE_FIVE:

                    convertView = inflater.inflate(R.layout.item_image_gallery, null);

                    holder.viewPager = (ViewPager)convertView.findViewById(R.id.imageGalleryViewPager);
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

    private void setHolderViewValuesByCodeType(HomeViewHolder argHolder, ContentItemList argItem, int argCodeType){
        switch(argCodeType){
            case NacionConstants.MODULE_CODE_ONE:
                argHolder.setViewHolderValuesForHighlightView(argItem);
                break;
            case NacionConstants.MODULE_CODE_TWO:
                argHolder.setViewHolderValuesForArticleView(argItem);
                break;
            case NacionConstants.MODULE_CODE_THREE:

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

    private List<NacionFragment> getVideoFragmentsArray(List<Content> argContents){
        List<NacionFragment> fragments = new ArrayList<NacionFragment>();
        int index = 0;
        for(Content content : argContents){
            VideoFragment videoFragment = new VideoFragment().getInstance(index, content.getImage().getPhoneUrl());
            videoFragment.setTitle(content.getTitle());
            fragments.add(videoFragment);
            index++;
        }
        return fragments;
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
