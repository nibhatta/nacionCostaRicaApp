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
import com.nacion.android.nacioncostarica.home.adapters.HomeSpecialDataGridAdapter;
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
    private ImageView b1ImageView;
    private ImageView b2ImageView;
    private ImageView b3ImageView;
    private TextView b1TextView;
    private TextView b2TextView;
    private TextView approachTextView;

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
        homeArticleGridAdapter.setPresenter(presenter);
        gridView.setAdapter(homeArticleGridAdapter);
    }

    public void setComponentsReferencesForSpecialDataView(View argView){
        title = (TextView)argView.findViewById(R.id.specialDataTitleTextView);
        gridView = (GridView)argView.findViewById(R.id.specialDataGridView);
    }

    public void setValuesForSpecialDataView(Context argContext, ContentItemList argItem){
        ContentModule contentModule = argItem.getModule().getContentModule();
        title.setText(contentModule.getTitle());
        List<Content> items = argItem.getModule().getContents();
        HomeSpecialDataGridAdapter homeSpecialDataGridAdapter = new HomeSpecialDataGridAdapter(argContext, items);
        gridView.setAdapter(homeSpecialDataGridAdapter);
    }

    public void setComponentsReferencesForBBlockView(View argView){
        b1ImageView = (ImageView)argView.findViewById(R.id.blockB1ImageView);
        b1TextView = (TextView)argView.findViewById(R.id.blockB1TextView);
        b2ImageView = (ImageView)argView.findViewById(R.id.blockB2ImageView);
        b2TextView = (TextView)argView.findViewById(R.id.blockB2TextView);
        b3ImageView = (ImageView)argView.findViewById(R.id.blockB3ImageView);
    }

    public void setValuesForBBlockView(Context argContext, ContentItemList argItem){
        List<Content> items = argItem.getModule().getContents();
        Content first = items.get(0);
        Content second = items.get(1);
        Content third = items.get(2);

        b1TextView.setText(first.getTitle());
        b2TextView.setText(second.getTitle());

        downloadImage(first.getImage().getPhoneUrl(), b1ImageView);
        downloadImage(second.getImage().getPhoneUrl(), b2ImageView);
        downloadImage(third.getImage().getPhoneUrl(), b1ImageView);
    }

    public void setComponentsReferencesForApproachView(View argView){
        image = (ImageView)argView.findViewById(R.id.approachImageView);
        section = (TextView)argView.findViewById(R.id.approachSectionTextView);
        title = (TextView)argView.findViewById(R.id.approachTitleTextView);
        summary = (TextView)argView.findViewById(R.id.approachSummaryTextView);
        approachTextView = (TextView)argView.findViewById(R.id.approachTextView);
    }

    public void setValuesForApproachView(ContentItemList argItem){
        section.setText(getSectionString(argItem));
        title.setText(argItem.getTitle());
        summary.setText(argItem.getSummary());
        //approachTextView.getText() + arg
        //downloadImage(argItem.getImage().getPhoneUrl(), image);
    }

    public void setComponentsReferencesForArticleAdView(View argView){
        gridView = (GridView)argView.findViewById(R.id.articleAdGridView);
    }

    public void setValuesForArticleAdView(Context argContext, ContentItemList argItem){
        List<Content> items = argItem.getModule().getContents();
        HomeArticleGridAdapter homeArticleGridAdapter = new HomeArticleGridAdapter(argContext, items);
        homeArticleGridAdapter.setPresenter(presenter);
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
        homeGridAdapter.setPresenter(presenter);
        gridView.setAdapter(homeGridAdapter);

        final String section = contentModule.getSection();
        final int articleId = contentModule.getId();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.startContextActivity(section, articleId);
            }
        });
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
