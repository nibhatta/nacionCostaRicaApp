package com.nacion.android.nacioncostarica.views.content.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.views.content.ContentPresenter;
import com.nacion.android.nacioncostarica.views.content.holder.ArticleContentViewHolder;
import com.nacion.android.nacioncostarica.models.ArticleContentItemList;
import com.nacion.android.nacioncostarica.models.Cover;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class ContentListAdapter extends ArrayAdapter<ArticleContentItemList> implements ContentListView {
    private static final int VIEWS_TYPE_COUNT = 4;
    private ContentPresenter presenter;
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

    public ContentPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(ContentPresenter presenter) {
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
                    if(isAGallery(itemList)) {
                        convertView = inflater.inflate(R.layout.item_highlight_image_gallery_content, null);
                        holder.setComponentsReferencesForHighlightImageGalleryView(convertView);
                    }else{
                        convertView = inflater.inflate(R.layout.item_highlight_content, null);
                        holder.setComponentsReferencesForHighlightView(convertView);
                    }
                    break;
                case NacionConstants.ARTICLE_PARAGRAPH_CODE:
                    convertView = inflater.inflate(R.layout.item_paragraph_content, null);
                    holder.setComponentsReferencesForParagraphView(convertView);
                    break;
                case NacionConstants.ARTICLE_WEIGHT_CODE:
                    convertView = inflater.inflate(R.layout.item_weight_content, null);
                    holder.setComponentsReferencesForWeightView(convertView);
                    break;
                case NacionConstants.ARTICLE_RELATED_CODE:
                    convertView = inflater.inflate(R.layout.item_related_content, null);
                    holder.setComponentsReferencesForRelatedView(convertView);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (ArticleContentViewHolder)convertView.getTag();
        }

        setHolderViewValuesByCodeType(holder, itemList, codeType);

        return convertView;
    }

    private boolean isAGallery(ArticleContentItemList article){
        Cover cover = article.getCover();
        return cover != null && cover.getContentType() != null && cover.getContentType().equals(NacionConstants.PGL);
    }

    private void setHolderViewValuesByCodeType(ArticleContentViewHolder argHolder, ArticleContentItemList item, int argCodeType){
        switch(argCodeType){
            case NacionConstants.ARTICLE_HIGHLIGHT_CODE:
                if(isAGallery(item)) {
                    argHolder.setValuesForHighlightImageGalleryView(item, fragmentManager);
                }else{
                    argHolder.setValuesForHighlightView(item);
                }
                break;
            case NacionConstants.ARTICLE_PARAGRAPH_CODE:
                argHolder.setValuesForParagraphView(item);
                break;
            case NacionConstants.ARTICLE_WEIGHT_CODE:
                argHolder.setValuesForWeightView(item);
                break;
            case NacionConstants.ARTICLE_RELATED_CODE:
                argHolder.setValuesForRelatedView(item);
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
