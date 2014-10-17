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

    public void setComponentsReferencesForParagraphView(View argView){
        body = (TextView)argView.findViewById(R.id.bodyContentTextView);
    }

    public void setValuesForParagraphView(ArticleContentItemList argItem){
        if(argItem.isParagraph()) {
            body.setText(Html.fromHtml(argItem.getStrData()));
        }
    }

    public void setComponentsReferencesForHighlightView(View argView){
        image = (ImageView)argView.findViewById(R.id.moduleImageView);
        title = (TextView)argView.findViewById(R.id.titleContentTextView);

        info = (TextView)argView.findViewById(R.id.infoTextView);
        infoButtonImageView = (ImageView)argView.findViewById(R.id.infoButtonImageView);
    }

    public void setValuesForHighlightView(ArticleContentItemList argItem){
        String url = argItem.getImage().getPhoneUrl();
        downloadImage(url, image);
        title.setText(argItem.getTitle());
        //body.setText(Html.fromHtml(argItem.getBody()));
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

    public void setComponentsReferencesForWeightView(View argView){
        image = (ImageView)argView.findViewById(R.id.weightImageView);
    }

    public void setValuesForWeightView(ArticleContentItemList argItem){

    }

    private String getSectionString(ContentItemList argItem){
        String date = getDateFormat(argItem.getTimestamp());
        String section = argItem.getSection() + date;
        return section;
    }


}
