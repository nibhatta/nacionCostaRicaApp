package com.nacion.android.nacioncostarica.views.home.holder;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.gui.textView.SectionTextCreator;
import com.nacion.android.nacioncostarica.gui.textView.SummaryTextCreator;
import com.nacion.android.nacioncostarica.gui.textView.TitleTextCreator;
import com.nacion.android.nacioncostarica.views.base.holders.ViewHolderBase;
import com.nacion.android.nacioncostarica.views.home.adapters.GalleryVideoPagerAdapter;
import com.nacion.android.nacioncostarica.views.home.adapters.HomeSpecialDataGridAdapter;
import com.nacion.android.nacioncostarica.views.home.fragments.GalleryFragment;
import com.nacion.android.nacioncostarica.views.home.fragments.VideoTabletFragment;
import com.nacion.android.nacioncostarica.views.home.adapters.HomeArticleGridAdapter;
import com.nacion.android.nacioncostarica.views.home.adapters.HomeGridAdapter;
import com.nacion.android.nacioncostarica.views.home.adapters.HomeListPresenter;
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
    private TitleTextCreator titleCreator;
    private SummaryTextCreator summaryCreator;
    private SectionTextCreator sectionCreator;

    public HomeTabletViewHolder(HomeListPresenter argPresenter){
        presenter = argPresenter;
        Context context = presenter.getContextFromMainActivity();
        titleCreator = new TitleTextCreator(context);
        summaryCreator = new SummaryTextCreator(context);
        sectionCreator = new SectionTextCreator(context);
    }

    public void setReferencesForArticleView(View argView){
        gridView = (GridView)argView.findViewById(R.id.articleGridView);
    }

    public void setValuesForArticleView(Context argContext, ContentItemList argItem){
        List<Content> items = argItem.getModule().getContents();
        HomeArticleGridAdapter homeArticleGridAdapter = new HomeArticleGridAdapter(argContext, items);
        homeArticleGridAdapter.setPresenter(presenter);
        gridView.setAdapter(homeArticleGridAdapter);
    }

    public void setReferencesForSpecialDataView(View argView){
        title = titleCreator
                .buildText((TextView)argView.findViewById(R.id.specialDataTitleTextView)).withAdeleBold();

        gridView = (GridView)argView.findViewById(R.id.specialDataGridView);
    }

    public void setValuesForSpecialDataView(Context argContext, ContentItemList argItem){
        ContentModule contentModule = argItem.getModule().getContentModule();
        title.setText(contentModule.getTitle());
        List<Content> items = argItem.getModule().getContents();
        HomeSpecialDataGridAdapter homeSpecialDataGridAdapter = new HomeSpecialDataGridAdapter(argContext, items);
        gridView.setAdapter(homeSpecialDataGridAdapter);
    }

    public void setReferencesForBBlockView(View argView){
        b1ImageView = (ImageView)argView.findViewById(R.id.blockB1ImageView);
        b1TextView = titleCreator
                .buildText((TextView)argView.findViewById(R.id.blockB1TextView))
                .withAdeleSemiBold();
        b2ImageView = (ImageView)argView.findViewById(R.id.blockB2ImageView);
        b2TextView = titleCreator
                .buildText((TextView)argView.findViewById(R.id.blockB2TextView))
                .withAdeleBold();
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

    public void setReferencesForApproachView(View argView){
        image = (ImageView)argView.findViewById(R.id.approachImageView);
        section = sectionCreator
                .buildText((TextView)argView.findViewById(R.id.approachSectionTextView))
                .withOpenSansRegular();
        title = titleCreator
                .buildText((TextView)argView.findViewById(R.id.approachTitleTextView))
                .withAdeleBold();
        summary = summaryCreator
                .buildText((TextView)argView.findViewById(R.id.approachSummaryTextView))
                .withTimesNewRoman();
        approachTextView = (TextView)argView.findViewById(R.id.approachTextView);
    }

    public void setValuesForApproachView(ContentItemList argItem){
        section.setText(getSectionString(argItem));
        title.setText(argItem.getTitle());
        summary.setText(argItem.getSummary());
        //approachTextView.getText() + arg
        //downloadImage(argItem.getImage().getPhoneUrl(), image);
    }

    public void setReferencesForArticleAdView(View argView){
        gridView = (GridView)argView.findViewById(R.id.articleAdGridView);
    }

    public void setValuesForArticleAdView(Context argContext, ContentItemList argItem){
        List<Content> items = argItem.getModule().getContents();
        HomeArticleGridAdapter homeArticleGridAdapter = new HomeArticleGridAdapter(argContext, items);
        homeArticleGridAdapter.setPresenter(presenter);
        gridView.setAdapter(homeArticleGridAdapter);
    }

    public void setReferencesForHighlightView(View argView){
        image = (ImageView)argView.findViewById(R.id.moduleImageView);
        section = sectionCreator
                .buildText((TextView)argView.findViewById(R.id.moduleSectionTextView))
                .withOpenSansRegular();
        title = titleCreator
                .buildText((TextView)argView.findViewById(R.id.moduleTitleTextView))
                .withAdeleExtraBold();
        summary = summaryCreator
                .buildText((TextView)argView.findViewById(R.id.moduleSummaryTextView))
                .withOpenSansRegular();
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

    public void setReferencesForVideoGalleryView(View argView){
        viewPager = (ViewPager)argView.findViewById(R.id.videoGalleryViewPager);
    }

    public void setValuesForVideoGalleryView(ContentItemList item, FragmentManager fragmentManager){
        List<Content> contents = item.getModule().getContents();
        int size = contents.size();

        List<GalleryFragment> fragments = getVideoFragmentsArray(contents);

        GalleryVideoPagerAdapter videoPagerAdapter = new GalleryVideoPagerAdapter(fragmentManager, fragments);
        videoPagerAdapter.setTabsCount(size);

        if(viewPager.getAdapter() == null){
            viewPager.setAdapter(videoPagerAdapter);
            viewPager.setOffscreenPageLimit(size);
        }
    }

    private List<GalleryFragment> getVideoFragmentsArray(List<Content> argContents){
        List<GalleryFragment> fragments = new ArrayList<GalleryFragment>();
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
