package com.nacion.android.nacioncostarica.content.holder;

import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.NacionFragment;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.content.IContentPresenter;
import com.nacion.android.nacioncostarica.holders.ViewHolderBase;
import com.nacion.android.nacioncostarica.home.galleryAdapter.GalleryVideoPagerAdapter;
import com.nacion.android.nacioncostarica.home.galleryAdapter.VideoFragment;
import com.nacion.android.nacioncostarica.home.galleryListener.GalleryOnPageChangeListener;
import com.nacion.android.nacioncostarica.home.listAdapter.HomeListPresenter;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.models.ContentItemList;
import com.nacion.android.nacioncostarica.models.ContentModule;
import com.nacion.android.nacioncostarica.models.IArticleContentItemList;
import com.nacion.android.nacioncostarica.tasks.ImageDownloaderTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 08/10/2014.
 */
public class ArticleContentViewHolder extends ViewHolderBase{
    private ImageView image;
    private TextView title;
    private TextView info;
    private ImageView infoButtonImageView;
    private TextView body;
    private TextView summary;
    private TextView section;
    private TextView timestamp;
    private TextView viewPagerTitle;
    private TextView viewPagerSection;
    private ViewPager viewPager;
    private IContentPresenter presenter;

    public ArticleContentViewHolder(IContentPresenter argPresenter){
        presenter = argPresenter;
    }

    public void setComponentsReferencesForArticleView(View argView){
        image = (ImageView)argView.findViewById(R.id.articleImageView);
        section = (TextView)argView.findViewById(R.id.articleSectionTextView);
        summary = (TextView)argView.findViewById(R.id.articleSummaryTextView);
    }

    public void setValuesForArticleView(ContentItemList argItem){
        String url = argItem.getImage().getPhoneUrl();
        downloadImage(url, image);
        section.setText(getSectionString(argItem));
        summary.setText(argItem.getSummary());

        final String section = argItem.getSection();
        final int articleId = argItem.getId();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // presenter.startContextActivity(section, articleId);
            }
        });
    }

    public void setComponentsReferencesForHighlightView(View argView){
        image = (ImageView)argView.findViewById(R.id.moduleImageView);
        title = (TextView)argView.findViewById(R.id.titleContentTextView);
        body = (TextView)argView.findViewById(R.id.bodyContentTextView);
        info = (TextView)argView.findViewById(R.id.infoTextView);
        infoButtonImageView = (ImageView)argView.findViewById(R.id.infoButtonImageView);
    }

    public void setValuesForHighlightView(IArticleContentItemList argItem){
        String url = argItem.getImage().getPhoneUrl();
        downloadImage(url, image);
        title.setText(argItem.getTitle());
        body.setText(argItem.getBody());
        info.setText(argItem.getSummary());

        //final String section = contentModule.getSection();
        //final int articleId = contentModule.getId();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  presenter.startContextActivity(section, articleId);
            }
        });

        infoButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(info.getVisibility() == View.VISIBLE){
                    info.setVisibility(View.INVISIBLE);
                }else{
                    info.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void setViewHolderComponentsReferencesForVideoGalleryView(View argView, ContentItemList argItem, FragmentManager argFragmentManager){
        viewPager = (ViewPager)argView.findViewById(R.id.videoGalleryViewPager);
        viewPagerTitle = (TextView)argView.findViewById(R.id.videoTitleTextView);
        viewPagerSection = (TextView)argView.findViewById(R.id.sectionVideoTextView);

        List<Content> contents = argItem.getModule().getContents();
        int size = contents.size();

        List<NacionFragment> fragments = getVideoFragmentsArray(contents);

        GalleryOnPageChangeListener listener = new GalleryOnPageChangeListener(fragments);
        listener.setTitleViewPager(viewPagerTitle);
        listener.setSectionViewPager(viewPagerSection);

        GalleryVideoPagerAdapter videoPagerAdapter = new GalleryVideoPagerAdapter(argFragmentManager, fragments);
        videoPagerAdapter.setTabsCount(size);

        if(viewPager.getAdapter() == null){
            viewPager.setAdapter(videoPagerAdapter);
            viewPager.setOnPageChangeListener(listener);
            viewPager.setOffscreenPageLimit(size);
        }
    }

    public void setViewHolderValuesForVideoGalleryView(){

    }

    private List<NacionFragment> getVideoFragmentsArray(List<Content> argContents){
        List<NacionFragment> fragments = new ArrayList<NacionFragment>();
        int index = 0;
        for(Content content : argContents){
            VideoFragment videoFragment = new VideoFragment().getInstance(index, content.getImage().getPhoneUrl());
            videoFragment.setTitle(content.getTitle());
            videoFragment.setSection(getDateFormat(content.getTimestamp()));
           // videoFragment.setPresenter(presenter);
            videoFragment.setContent(content);
            fragments.add(videoFragment);
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
