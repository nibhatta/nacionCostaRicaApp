package com.nacion.android.nacioncostarica.views.home.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.gui.textView.SectionTextCreator;
import com.nacion.android.nacioncostarica.gui.textView.SummaryTextCreator;
import com.nacion.android.nacioncostarica.gui.textView.TitleTextCreator;
import com.nacion.android.nacioncostarica.views.base.holders.ViewHolderBase;
import com.nacion.android.nacioncostarica.views.home.adapters.HomeListPresenter;
import com.nacion.android.nacioncostarica.models.ContentItemList;

/**
 * Created by Gustavo Matarrita on 08/10/2014.
 */
public class GridViewHolder extends ViewHolderBase{
    private ImageView image;
    private TextView title;
    private TextView section;
    private TextView summary;
    private HomeListPresenter presenter;
    private TitleTextCreator titleCreator;
    private SummaryTextCreator summaryCreator;
    private SectionTextCreator sectionCreator;

    public GridViewHolder(HomeListPresenter argPresenter){
        presenter = argPresenter;
        Context context = presenter.getContextFromMainActivity();
        titleCreator = new TitleTextCreator(context);
        summaryCreator = new SummaryTextCreator(context);
        sectionCreator = new SectionTextCreator(context);
    }

    public void setComponentsReferences(View argView){
        image = (ImageView)argView.findViewById(R.id.moduleNewsImageView);
        title = titleCreator
                .buildText((TextView)argView.findViewById(R.id.moduleNewsTitleTextView))
                .withAdeleRegular();
    }

    public void setValues(ContentItemList argItem){
        String url = argItem.getImage().getPhoneUrl();
        downloadImage(url, image);
        title.setText(argItem.getTitle());
        setStartContextActivityEvent(argItem);
    }

    public void setComponentsReferencesForArticleGrid(View argView){
        title = (TextView)argView.findViewById(R.id.articleNewsTitleTextView);
        section = (TextView)argView.findViewById(R.id.articleNewsSectionTextView);
        summary = (TextView)argView.findViewById(R.id.articleNewsSummaryTextView);
        image = (ImageView)argView.findViewById(R.id.articleNewsImageView);
    }

    public void setValuesForArticleGrid(ContentItemList argItem){
        String url = argItem.getImage().getPhoneUrl();
        downloadImage(url, image);
        title.setText(argItem.getTitle());
        section.setText(getSectionString(argItem));
        summary.setText(argItem.getSummary());
        setStartContextActivityEvent(argItem);
    }

    public void setStartContextActivityEvent(ContentItemList argItem){
        final String section = argItem.getSection();
        final int articleId = argItem.getId();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.startContextActivity(section, articleId);
            }
        });
    }

    public void setComponentsReferencesForSpecialDataGrid(View argView){
        title = (TextView)argView.findViewById(R.id.specialDataCellTitleTextView);
    }

    public void setValuesForSpecialDataGrid(ContentItemList argItem){
        title.setText(argItem.getTitle());
    }

    private String getSectionString(ContentItemList argItem){
        String date = getDateFormat(argItem.getTimestamp());
        String section = argItem.getSection() + date;
        return section;
    }
}
