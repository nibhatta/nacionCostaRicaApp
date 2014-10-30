package com.nacion.android.nacioncostarica.views.content.holder;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.gui.fonts.Fonts;
import com.nacion.android.nacioncostarica.gui.textView.TextCreator;
import com.nacion.android.nacioncostarica.views.content.ContentPresenter;
import com.nacion.android.nacioncostarica.views.content.adapters.ContentImageGalleryPagerAdapter;
import com.nacion.android.nacioncostarica.views.content.adapters.WeightImageGalleryPagerAdapter;
import com.nacion.android.nacioncostarica.views.content.fragments.ContentFragment;
import com.nacion.android.nacioncostarica.views.content.fragments.ContentImageFragment;
import com.nacion.android.nacioncostarica.views.content.listeners.ContentGalleryOnPageChangeListener;
import com.nacion.android.nacioncostarica.views.base.holders.ViewHolderBase;
import com.nacion.android.nacioncostarica.models.ArticleContentItemList;
import com.nacion.android.nacioncostarica.models.ContentItemList;
import com.nacion.android.nacioncostarica.models.Cover;
import com.nacion.android.nacioncostarica.models.Data;
import com.nacion.android.nacioncostarica.views.content.listeners.WeightGalleryOnPageChangeListener;

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
    private TextView author;
    private TextView photoAuthor;
    private TextView summary;
    private TextView section;
    private ViewPager viewPager;
    private ContentPresenter presenter;
    private Fonts fonts;

    public ArticleContentViewHolder(ContentPresenter argPresenter){
        presenter = argPresenter;
        Context context = presenter.getContextFromContentActivity();
        fonts = Fonts.getInstance(context);
    }

    public void setReferencesForParagraphView(View argView){
        body = (TextView)argView.findViewById(R.id.bodyContentTextView);
    }

    public void setValuesForParagraphView(ArticleContentItemList argItem){
        if(argItem.isParagraph()) {
            body.setText(Html.fromHtml(argItem.getDataStr()));
        }
    }

    public void setReferencesForHighlightView(View argView){
        image = (ImageView)argView.findViewById(R.id.moduleImageView);
        title = new TextCreator
                .Builder((TextView)argView.findViewById(R.id.titleContentTextView))
                .typeface(fonts.ADELE_BOLD)
                .size(21)
                .build();
        author = (TextView)argView.findViewById(R.id.authorNewsContentTextView);
        photoAuthor = (TextView)argView.findViewById(R.id.authorPhotoContentTextView);
        info = (TextView)argView.findViewById(R.id.infoTextView);
        infoButtonImageView = (ImageView)argView.findViewById(R.id.infoButtonImageView);
    }

    public void setValuesForHighlightView(ArticleContentItemList article){
        Cover cover = article.getCover();
        List<Data> dataArray = cover.getDataAre();

        int firstItem = 0;
        Data data = dataArray.get(firstItem);
        String url = data.getPhone();
        downloadImage(url, image);

        photoAuthor.setText(data.getCredit());
        author.setText(article.getAuthor());
        title.setText(article.getTitle());
        info.setText(article.getSummary());

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

    public void setReferencesForHighlightImageGalleryView(View argView){
        viewPager = (ViewPager)argView.findViewById(R.id.imageGalleryViewPager);
        title = new TextCreator
                .Builder((TextView)argView.findViewById(R.id.titleContentTextView))
                .size(21)
                .typeface(fonts.ADELE_BOLD)
                .build();
        author = (TextView)argView.findViewById(R.id.authorNewsContentTextView);
        photoAuthor = (TextView)argView.findViewById(R.id.authorPhotoContentTextView);
        info = (TextView)argView.findViewById(R.id.infoTextView);
        infoButtonImageView = (ImageView)argView.findViewById(R.id.infoButtonImageView);
    }

    public void setValuesForHighlightImageGalleryView(ArticleContentItemList article, FragmentManager fragmentManager){
        Cover cover = article.getCover();
        List<Data> dataArray = cover.getDataAre();
        int size = dataArray.size();
        List<ContentFragment> fragments = getContentImageFragmentsArray(dataArray);

        ContentImageGalleryPagerAdapter adapter = new ContentImageGalleryPagerAdapter(fragmentManager, fragments);
        adapter.setTabsCount(size);

        ContentGalleryOnPageChangeListener listener = new ContentGalleryOnPageChangeListener(fragments);
        listener.setPhotoAuthorTextView(photoAuthor);

        if(viewPager.getAdapter() == null){
            viewPager.setAdapter(adapter);
            viewPager.setOnPageChangeListener(listener);
            viewPager.setOffscreenPageLimit(size);
        }

        author.setText(article.getAuthor());
        title.setText(article.getTitle());
        info.setText(article.getSummary());
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

    private List<ContentFragment> getContentImageFragmentsArray(List<Data> dataArray){
        List<ContentFragment> fragments = new ArrayList<ContentFragment>();
        int index = 0;
        for(Data data : dataArray){
            ContentImageFragment imageFragment = new ContentImageFragment().getInstance(index, data.getPhone());
            imageFragment.setPresenter(presenter);
            imageFragment.setPhotoAuthor(data.getCredit());
            fragments.add(imageFragment);
            index++;
        }
        return fragments;
    }

    public void setReferencesForWeightImageGalleryView(View argView){
        viewPager = (ViewPager)argView.findViewById(R.id.bodyImageGalleryViewPager);
        title = new TextCreator
                .Builder((TextView)argView.findViewById(R.id.imageTitleTextView))
                .size(21)
                .typeface(fonts.ADELE_BOLD)
                .build();
    }

    public void setValuesForWeightImageGalleryView(ArticleContentItemList article, FragmentManager fragmentManager){

        List<Data> dataArray = article.getDataList();
        int size = dataArray.size();
        List<ContentFragment> fragments = getWeightImageFragmentsArray(dataArray);

        WeightImageGalleryPagerAdapter adapter = new WeightImageGalleryPagerAdapter(fragmentManager, fragments);
        adapter.setTabsCount(size);

        WeightGalleryOnPageChangeListener listener = new WeightGalleryOnPageChangeListener(fragments);
        listener.setTitleTextView(title);

        if(viewPager.getAdapter() == null){
            viewPager.setAdapter(adapter);
            viewPager.setOnPageChangeListener(listener);
            viewPager.setOffscreenPageLimit(size);
        }
    }

    private List<ContentFragment> getWeightImageFragmentsArray(List<Data> dataArray){
        List<ContentFragment> fragments = new ArrayList<ContentFragment>();
        int index = 0;
        for(Data data : dataArray){
            ContentImageFragment imageFragment = new ContentImageFragment().getInstance(index, data.getPhone());
            imageFragment.setPresenter(presenter);
            imageFragment.setTitle(data.getCaption());
            fragments.add(imageFragment);
            index++;
        }
        return fragments;
    }



    public void setReferencesForWeightView(View argView){
        image = (ImageView)argView.findViewById(R.id.weightImageView);
    }

    public void setValuesForWeightView(ArticleContentItemList argItem){
        List<Data> dataList = argItem.getDataList();
        int firstItem = 0;
        Data data = dataList.get(firstItem);
        String url = data.getPhone();
        downloadImage(url, image);
    }

    public void setReferencesForRelatedView(View view){
        image = (ImageView)view.findViewById(R.id.relatedImageView);
        section = (TextView)view.findViewById(R.id.relatedSectionTextView);
        summary = (TextView)view.findViewById(R.id.relatedSummaryTextView);
    }

    public void setValuesForRelatedView(ArticleContentItemList item){
        section.setText(getDateFormat(item.getTimestamp()));
        summary.setText(item.getSummary());
    }

    private String getSectionString(ContentItemList argItem){
        String date = getDateFormat(argItem.getTimestamp());
        String section = argItem.getSection() + date;
        return section;
    }
}
