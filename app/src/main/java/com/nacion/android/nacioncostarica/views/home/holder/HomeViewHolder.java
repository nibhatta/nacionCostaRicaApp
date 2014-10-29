package com.nacion.android.nacioncostarica.views.home.holder;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.gui.textView.SectionTextCreator;
import com.nacion.android.nacioncostarica.gui.textView.SummaryTextCreator;
import com.nacion.android.nacioncostarica.gui.textView.TitleTextCreator;
import com.nacion.android.nacioncostarica.models.Image;
import com.nacion.android.nacioncostarica.models.Module;
import com.nacion.android.nacioncostarica.views.base.holders.ViewHolderBase;
import com.nacion.android.nacioncostarica.views.home.adapters.GalleryVideoPagerAdapter;
import com.nacion.android.nacioncostarica.views.home.adapters.HomeListAdapter;
import com.nacion.android.nacioncostarica.views.home.fragments.GalleryFragment;
import com.nacion.android.nacioncostarica.views.home.fragments.VideoFragment;
import com.nacion.android.nacioncostarica.views.home.listeners.GalleryOnPageChangeListener;
import com.nacion.android.nacioncostarica.views.home.adapters.HomeListPresenter;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.models.ContentItemList;
import com.nacion.android.nacioncostarica.models.ContentModule;

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
    private TitleTextCreator titleCreator;
    private SummaryTextCreator summaryCreator;
    private SectionTextCreator sectionCreator;

    public HomeViewHolder(HomeListPresenter argPresenter){
        presenter = argPresenter;
        Context context = presenter.getContextFromMainActivity();
        titleCreator = new TitleTextCreator(context);
        summaryCreator = new SummaryTextCreator(context);
        sectionCreator = new SectionTextCreator(context);
    }

    public void setReferencesForArticleView(View view){
        image = (ImageView)view.findViewById(R.id.articleImageView);
        section = (TextView)view.findViewById(R.id.articleSectionTextView);
        summary = summaryCreator
                .buildText((TextView)view.findViewById(R.id.articleSummaryTextView))
                .withTimesNewRoman();
    }

    public void setValuesForArticleView(ContentItemList argItem){
        Image imageModel = argItem.getImage();
        if(imageModel != null) {
            String url = imageModel.getPhoneUrl();
            downloadImage(url, image);
        }

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

    public void setReferencesForHighlightView(View argView){
        image = (ImageView)argView.findViewById(R.id.moduleImageView);
        summary = (TextView)argView.findViewById(R.id.moduleSummaryTextView);
        title = titleCreator
                .buildText((TextView) argView.findViewById(R.id.moduleTitleTextView))
                .withAdeleBold();
    }

    public void setValuesForHighlightView(ContentItemList argItem){
        ContentModule contentModule = argItem.getModule().getContentModule();
        String url = contentModule.getImage().getPhoneUrl();
        downloadImage(url, image);
        title.setText(contentModule.getTitle());

        summary.setText(contentModule.getSummary());
        final String section = contentModule.getSection();
        final int articleId = contentModule.getId();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.startContextActivity(section, articleId);
            }
        });
    }

    public void setReferencesForMoreView(View view, final HomeListAdapter adapter, final ContentItemList item){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view = adapter.getInflater().inflate(R.layout.item_article, null);
                setReferencesForArticleView(view);
                adapter.displayMoreNews(item);
            }
        });
    }

    public void setValuesForMoreView(ContentItemList item){

    }

    public void setReferencesForVideoGalleryView(View view){
        viewPager = (ViewPager)view.findViewById(R.id.videoGalleryViewPager);
        viewPagerTitle = titleCreator
                .buildText((TextView)view.findViewById(R.id.videoTitleTextView))
                .withAdeleBold();
        viewPagerSection = sectionCreator
                .buildText((TextView)view.findViewById(R.id.sectionVideoTextView))
                .withOpenSansRegular();

    }

    public void setValuesForVideoGalleryView(ContentItemList item, FragmentManager argFragmentManager){
        List<Content> contents = item.getModule().getContents();
        int size = contents.size();

        List<GalleryFragment> fragments = getVideoFragmentsArray(contents);

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

    private List<GalleryFragment> getVideoFragmentsArray(List<Content> argContents){
        List<GalleryFragment> fragments = new ArrayList<GalleryFragment>();
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
        if(argItem.getTimestamp() == null || argItem.getSection() == null){
            return NacionConstants.EMPTY_STRING;
        }
        String date = getDateFormat(argItem.getTimestamp());
        String section = argItem.getSection() + date;
        return section;
    }
}
