package com.nacion.android.nacioncostarica.home.holders;

import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.holders.ViewHolderBase;
import com.nacion.android.nacioncostarica.models.ContentItemList;
import com.nacion.android.nacioncostarica.models.ContentModule;
import com.nacion.android.nacioncostarica.tasks.ImageDownloaderTask;

/**
 * Created by Gustavo Matarrita on 08/10/2014.
 */
public class HomeViewHolder extends ViewHolderBase{
    public ImageView image;
    public TextView title;
    public TextView summary;
    public TextView section;
    public TextView timestamp;
    public ViewPager viewPager;

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
