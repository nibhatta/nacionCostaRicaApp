package com.nacion.android.nacioncostarica.home.holders;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.NacionCostaRicaActivity;
import com.nacion.android.nacioncostarica.NacionFragment;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.activities.ContentActivity;
import com.nacion.android.nacioncostarica.holders.ViewHolderBase;
import com.nacion.android.nacioncostarica.home.galleryAdapter.GalleryVideoPagerAdapter;
import com.nacion.android.nacioncostarica.home.galleryAdapter.VideoFragment;
import com.nacion.android.nacioncostarica.home.galleryListener.GalleryOnPageChangeListener;
import com.nacion.android.nacioncostarica.home.listAdapter.HomeListPresenter;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.models.ContentItemList;
import com.nacion.android.nacioncostarica.models.ContentModule;
import com.nacion.android.nacioncostarica.tasks.ImageDownloaderTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 08/10/2014.
 */
public class HomeViewHolder extends ViewHolderBase{
    private ImageView image;
    private TextView title;
    private TextView summary;
    private TextView section;
    private TextView timestamp;
    private TextView viewPagerTitle;
    private TextView viewPagerSection;
    private ViewPager viewPager;
    private HomeListPresenter presenter;

    public HomeViewHolder(HomeListPresenter argPresenter){
        presenter = argPresenter;
    }

    public void setViewHolderComponentsReferencesForArticleView(View argView){
        image = (ImageView)argView.findViewById(R.id.articleImageView);
        section = (TextView)argView.findViewById(R.id.articleSectionTextView);
        summary = (TextView)argView.findViewById(R.id.articleSummaryTextView);
    }

    public void setViewHolderValuesForArticleView(ContentItemList argItem){
        String url = argItem.getImage().getPhoneUrl();
        downloadImage(url);
        section.setText(getSectionString(argItem));
        summary.setText(argItem.getSummary());

        final String section = argItem.getSection();
        final int articleId = argItem.getId();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.startContextActivity(section, articleId);
            }
        });
    }

    public void setViewHolderComponentsReferencesForHighlightView(View argView){
        image = (ImageView)argView.findViewById(R.id.moduleImageView);
        section = (TextView)argView.findViewById(R.id.moduleSectionTextView);
        title = (TextView)argView.findViewById(R.id.moduleTitleTextView);
    }

    public void setViewHolderValuesForHighlightView(ContentItemList argItem){
        ContentModule contentModule = argItem.getModule().getContentModule();
        String url = contentModule.getImage().getPhoneUrl();
        downloadImage(url);
        section.setText(getSectionString(contentModule));
        title.setText(contentModule.getTitle());

        final String section = contentModule.getSection();
        final int articleId = contentModule.getId();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.startContextActivity(section, articleId);
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
            videoFragment.setPresenter(presenter);
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

    private void downloadImage(String argUrl){
        ImageDownloaderTask task = new ImageDownloaderTask(image);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, argUrl);
        }else{
            task.execute(argUrl);
        }
    }
}
