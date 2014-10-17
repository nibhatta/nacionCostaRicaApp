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
import com.nacion.android.nacioncostarica.home.fragments.ImageFragment;
import com.nacion.android.nacioncostarica.home.fragments.VideoFragment;
import com.nacion.android.nacioncostarica.models.ArticleContentItemList;
import com.nacion.android.nacioncostarica.models.Content;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class ContentListAdapter extends ArrayAdapter<ArticleContentItemList> implements ContentListView {
    private static final int VIEWS_TYPE_COUNT = 3;
    private IContentPresenter presenter;
    private LayoutInflater inflater;
    private Context context;
    private ViewPager viewPager;
    private FragmentManager fragmentManager;

    public ContentListAdapter(Context context, List<ArticleContentItemList> argContents, FragmentManager argFragmentManager) {
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
        ArticleContentItemList itemList = getItem(position);
        int codeType = getItemViewType(position);
        ArticleContentViewHolder holder;
        if(convertView == null){
            holder = new ArticleContentViewHolder(presenter);
            switch(codeType){
                case NacionConstants.ARTICLE_HIGHLIGHT_CODE:
                    convertView = inflater.inflate(R.layout.item_highlight_content, null);
                    holder.setComponentsReferencesForHighlightView(convertView);
                    break;
                case NacionConstants.ARTICLE_PARAGRAPH_CODE:
                    convertView = inflater.inflate(R.layout.item_paragraph_content, null);
                    holder.setComponentsReferencesForParagraphView(convertView);
                    break;
                case NacionConstants.ARTICLE_WEIGHT_CODE:
                    convertView = inflater.inflate(R.layout.item_weight_content, null);
                    holder.setComponentsReferencesForWeightView(convertView);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (ArticleContentViewHolder)convertView.getTag();
        }

        setHolderViewValuesByCodeType(holder, itemList, codeType);

        return convertView;
    }

    private void setHolderViewValuesByCodeType(ArticleContentViewHolder argHolder, ArticleContentItemList argItem, int argCodeType){
        switch(argCodeType){
            case NacionConstants.ARTICLE_HIGHLIGHT_CODE:
                argHolder.setValuesForHighlightView(argItem);
                break;
            case NacionConstants.ARTICLE_PARAGRAPH_CODE:
                argHolder.setValuesForParagraphView(argItem);
                break;
            case NacionConstants.ARTICLE_WEIGHT_CODE:
                argHolder.setValuesForWeightView(argItem);
                break;
        }
    }

    @Override
    public int getViewTypeCount() {
        return VIEWS_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        ArticleContentItemList articleContent = getItem(position);
        return articleContent.getTypeCode();
    }
}
