package com.nacion.android.nacioncostarica.content.holder;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.NacionFragment;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.content.IContentPresenter;
import com.nacion.android.nacioncostarica.holders.ViewHolderBase;
import com.nacion.android.nacioncostarica.home.adapters.GalleryVideoPagerAdapter;
import com.nacion.android.nacioncostarica.home.fragments.VideoFragment;
import com.nacion.android.nacioncostarica.home.listeners.GalleryOnPageChangeListener;
import com.nacion.android.nacioncostarica.models.ArticleContentItemList;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.models.ContentItemList;
import com.nacion.android.nacioncostarica.models.Cover;
import com.nacion.android.nacioncostarica.models.Data;

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
    private TextView timestamp;
    private TextView viewPagerTitle;
    private TextView viewPagerSection;
    private ViewPager viewPager;
    private IContentPresenter presenter;

    public ArticleContentViewHolder(IContentPresenter argPresenter){
        presenter = argPresenter;
    }

    public void setComponentsReferencesForParagraphView(View argView){
        body = (TextView)argView.findViewById(R.id.bodyContentTextView);
    }

    public void setValuesForParagraphView(ArticleContentItemList argItem){
        if(argItem.isParagraph()) {
            body.setText(Html.fromHtml(argItem.getDataStr()));
        }
    }

    public void setComponentsReferencesForHighlightView(View argView){
        image = (ImageView)argView.findViewById(R.id.moduleImageView);
        title = (TextView)argView.findViewById(R.id.titleContentTextView);
        author = (TextView)argView.findViewById(R.id.authorNewsContentTextView);
        photoAuthor = (TextView)argView.findViewById(R.id.authorPhotoContentTextView);
        info = (TextView)argView.findViewById(R.id.infoTextView);
        infoButtonImageView = (ImageView)argView.findViewById(R.id.infoButtonImageView);
        setTypefaceForTextViews();
    }

    public void setValuesForHighlightView(ArticleContentItemList article){
        Cover cover = article.getCover();
        List<Data> dataArray = cover.getDataAre();

        Data data = dataArray.get(0);
        String url = data.getPhone();
        downloadImage(url, image);
        photoAuthor.setText(data.getCredit());

        author.setText(article.getAuthor());

        title.setText(article.getTitle());
        //body.setText(Html.fromHtml(argItem.getBody()));
        info.setText(article.getSummary());

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

    public void setComponentsReferencesForHighlightImageGalleryView(View argView){
        viewPager = (ViewPager)argView.findViewById(R.id.imageGalleryViewPager);
        title = (TextView)argView.findViewById(R.id.titleContentTextView);
        author = (TextView)argView.findViewById(R.id.authorNewsContentTextView);
        photoAuthor = (TextView)argView.findViewById(R.id.authorPhotoContentTextView);
        info = (TextView)argView.findViewById(R.id.infoTextView);
        infoButtonImageView = (ImageView)argView.findViewById(R.id.infoButtonImageView);
        setTypefaceForTextViews();
    }

    public void setValuesForHighlightImageGalleryView(ArticleContentItemList article){



        Cover cover = article.getCover();
        List<Data> dataArray = cover.getDataAre();

        Data data = dataArray.get(0);
        String url = data.getPhone();
        downloadImage(url, image);
        photoAuthor.setText(data.getCredit());

        author.setText(article.getAuthor());

        title.setText(article.getTitle());
        //body.setText(Html.fromHtml(argItem.getBody()));
        info.setText(article.getSummary());

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

    public void setComponentsReferencesForWeightView(View argView){
        image = (ImageView)argView.findViewById(R.id.weightImageView);
    }

    public void setValuesForWeightView(ArticleContentItemList argItem){
        Data data = argItem.getDataObj();
        String url = data.getPhone();
        downloadImage(url, image);
    }

    public void setComponentsReferencesForRelatedView(View view){
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

    private void setTypefaceForTextViews(){
        if(summary != null){
            summary.setTypeface(presenter.getFonts().TIMES_NEW_ROMAN);
        }
        if(title != null){
            title.setTypeface(presenter.getFonts().ADELE_BOLD);
        }
        if(viewPagerTitle != null){
            viewPagerTitle.setTypeface(presenter.getFonts().ADELE_SEMI_BOLD);
        }
    }


}
