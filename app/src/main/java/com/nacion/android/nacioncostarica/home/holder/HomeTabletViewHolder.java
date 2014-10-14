package com.nacion.android.nacioncostarica.home.holder;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.NacionFragment;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.holders.ViewHolderBase;
import com.nacion.android.nacioncostarica.home.adapters.GalleryVideoPagerAdapter;
import com.nacion.android.nacioncostarica.home.fragments.VideoFragment;
import com.nacion.android.nacioncostarica.home.fragments.VideoTabletFragment;
import com.nacion.android.nacioncostarica.home.listeners.GalleryOnPageChangeListener;
import com.nacion.android.nacioncostarica.home.adapters.HomeArticleGridAdapter;
import com.nacion.android.nacioncostarica.home.adapters.HomeGridAdapter;
import com.nacion.android.nacioncostarica.home.adapters.HomeListPresenter;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.models.ContentItemList;
import com.nacion.android.nacioncostarica.models.ContentModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 08/10/2014.
 */
public class HomeTabletViewHolder extends ViewHolderBase{
    private ImageView image;
    private TextView title;
    private TextView summary;
    private TextView section;
    private TextView timestamp;
    private TextView viewPagerTitle;
    private TextView viewPagerSection;
    private ViewPager viewPager;
    private GridView gridView;
    private HomeListPresenter presenter;

    public HomeTabletViewHolder(HomeListPresenter argPresenter){
        presenter = argPresenter;
    }

    public void setComponentsReferencesForArticleView(View argView){
        gridView = (GridView)argView.findViewById(R.id.articleGridView);
    }

    public void setValuesForArticleView(Context argContext, ContentItemList argItem){
        List<Content> items = argItem.getModule().getContents();
        HomeArticleGridAdapter homeArticleGridAdapter = new HomeArticleGridAdapter(argContext, items);
        gridView.setAdapter(homeArticleGridAdapter);
    }

    public void setComponentsReferencesForHighlightView(View argView){
        image = (ImageView)argView.findViewById(R.id.moduleImageView);
        section = (TextView)argView.findViewById(R.id.moduleSectionTextView);
        title = (TextView)argView.findViewById(R.id.moduleTitleTextView);
        summary = (TextView)argView.findViewById(R.id.moduleSummaryTextView);
        gridView = (GridView)argView.findViewById(R.id.moduleGridView);
    }

    public void setValuesForHighlightView(ContentItemList argItem, Context argContext){
        ContentModule contentModule = argItem.getModule().getContentModule();
        String url = contentModule.getImage().getPhoneUrl();
        downloadImage(url, image);
        section.setText(getSectionString(contentModule));
        title.setText(contentModule.getTitle());
        summary.setText(contentModule.getSummary());

        List<Content> items = argItem.getModule().getContents();
        HomeGridAdapter homeGridAdapter = new HomeGridAdapter(argContext, items);
        gridView.setAdapter(homeGridAdapter);
    }

    public void setComponentsReferencesForVideoGalleryView(View argView, ContentItemList argItem, FragmentManager argFragmentManager){
        viewPager = (ViewPager)argView.findViewById(R.id.videoGalleryViewPager);

        List<Content> contents = argItem.getModule().getContents();
        int size = contents.size();

        List<NacionFragment> fragments = getVideoFragmentsArray(contents);

        GalleryVideoPagerAdapter videoPagerAdapter = new GalleryVideoPagerAdapter(argFragmentManager, fragments);
        videoPagerAdapter.setTabsCount(size);

        if(viewPager.getAdapter() == null){
            viewPager.setAdapter(videoPagerAdapter);
            viewPager.setOffscreenPageLimit(size);
        }
    }

    public void setViewHolderValuesForVideoGalleryView(){

    }

    private List<NacionFragment> getVideoFragmentsArray(List<Content> argContents){
        List<NacionFragment> fragments = new ArrayList<NacionFragment>();
        int index = 0;
        for(Content content : argContents){
            VideoTabletFragment videoTabletFragment = new VideoTabletFragment().getInstance(index, content.getImage().getPhoneUrl());
            videoTabletFragment.setTitle(content.getTitle());
            videoTabletFragment.setSection(getDateFormat(content.getTimestamp()));
            videoTabletFragment.setPresenter(presenter);
            fragments.add(videoTabletFragment);
            index++;
        }
        return fragments;
    }

    private String getSectionString(ContentItemList argItem){
        String date = getDateFormat(argItem.getTimestamp());
        String section = argItem.getSection() + date;
        return section;
    }
}
