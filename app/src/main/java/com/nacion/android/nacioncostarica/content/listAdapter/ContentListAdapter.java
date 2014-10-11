package com.nacion.android.nacioncostarica.content.listAdapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.NacionFragment;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.content.IContentPresenter;
import com.nacion.android.nacioncostarica.content.holder.ArticleContentViewHolder;
import com.nacion.android.nacioncostarica.home.galleryAdapter.ImageFragment;
import com.nacion.android.nacioncostarica.home.galleryAdapter.VideoFragment;
import com.nacion.android.nacioncostarica.home.holder.HomeViewHolder;
import com.nacion.android.nacioncostarica.home.listAdapter.HomeListPresenter;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.models.ContentItemList;
import com.nacion.android.nacioncostarica.models.IArticleContentItemList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class ContentListAdapter extends ArrayAdapter<IArticleContentItemList> implements ContentListView {
    private static final int VIEWS_TYPE_COUNT = 1;
    private IContentPresenter presenter;
    private LayoutInflater inflater;
    private Context context;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;

    public ContentListAdapter(Context context, List<IArticleContentItemList> argContents, FragmentManager argFragmentManager) {
        super(context, R.layout.item_highlight_content, argContents);
        this.context = context;
        inflater = LayoutInflater.from(context);
        fragmentManager = argFragmentManager;
    }

    public IContentPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(IContentPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IArticleContentItemList itemList = getItem(position);
        int codeType = getItemViewType(position);
        ArticleContentViewHolder holder;
        if(convertView == null){
            holder = new ArticleContentViewHolder(presenter);
            switch(codeType){
                case NacionConstants.MODULE_CODE_ONE:
                    convertView = inflater.inflate(R.layout.item_highlight_content, null);
                    holder.setComponentsReferencesForHighlightView(convertView);
                    break;
                /*
                case NacionConstants.MODULE_CODE_TWO:
                    convertView = inflater.inflate(R.layout.item_article, null);
                    holder.setViewHolderComponentsReferencesForArticleView(convertView);
                    break;
                case NacionConstants.MODULE_CODE_THREE:
                    convertView = inflater.inflate(R.layout.item_video_gallery, null);
                    holder.setViewHolderComponentsReferencesForVideoGalleryView(convertView, itemList, fragmentManager);
                    break;
                case NacionConstants.MODULE_CODE_FOURTH:
                    convertView = inflater.inflate(R.layout.item_weather, null);
                    break;
                case NacionConstants.MODULE_CODE_FIVE:

                    convertView = inflater.inflate(R.layout.item_image_gallery, null);


                    break;
                case NacionConstants.MODULE_CODE_EIGHT:
                    convertView = inflater.inflate(R.layout.item_more_news, null);
                    break;
                */
            }
            convertView.setTag(holder);
        }else{
            holder = (ArticleContentViewHolder)convertView.getTag();
        }

        setHolderViewValuesByCodeType(holder, itemList, codeType);

        return convertView;
    }

    private void setHolderViewValuesByCodeType(ArticleContentViewHolder argHolder, IArticleContentItemList argItem, int argCodeType){
        switch(argCodeType){
            case NacionConstants.MODULE_CODE_ONE:
                argHolder.setValuesForHighlightView(argItem);
                break;
            /*
            case NacionConstants.MODULE_CODE_TWO:
                argHolder.setViewHolderValuesForArticleView(argItem);
                break;
            case NacionConstants.MODULE_CODE_THREE:
                argHolder.setViewHolderValuesForVideoGalleryView();
                break;
            case NacionConstants.MODULE_CODE_FOURTH:

                break;
            case NacionConstants.MODULE_CODE_FIVE:

                break;
            case NacionConstants.MODULE_CODE_EIGHT:

                break;
             */
        }
    }

    private List<NacionFragment> getFragmentsArray(){
        List<NacionFragment> fragments = new ArrayList<NacionFragment>();
        fragments.add(new ImageFragment().getInstance(0));
        fragments.add(new ImageFragment().getInstance(1));
        fragments.add(new ImageFragment().getInstance(2));
        fragments.add(new ImageFragment().getInstance(3));
        return fragments;
    }

    private List<NacionFragment> getVideoFragmentsArray(List<Content> argContents){
        List<NacionFragment> fragments = new ArrayList<NacionFragment>();
        int index = 0;
        for(Content content : argContents){
            VideoFragment videoFragment = new VideoFragment().getInstance(index, content.getImage().getPhoneUrl());
            videoFragment.setTitle(content.getTitle());
            fragments.add(videoFragment);
            index++;
        }
        return fragments;
    }

    @Override
    public int getViewTypeCount() {
        return VIEWS_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        IArticleContentItemList articleContent = getItem(position);
        return articleContent.getTypeCode();
    }
}
