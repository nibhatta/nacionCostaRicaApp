package com.nacion.android.nacioncostarica.views.home.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.models.Module;
import com.nacion.android.nacioncostarica.views.home.holder.HomeViewHolder;
import com.nacion.android.nacioncostarica.models.ContentItemList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomeListAdapter extends ArrayAdapter<ContentItemList> implements HomeListView {
    private static final int VIEWS_TYPE_COUNT = 8;
    private static final int BLOCK_MORE = 4;
    private static final int MAX_MORE_DISPLAY = 16;
    private HomeListPresenter presenter;
    private LayoutInflater inflater;
    private FragmentManager fragmentManager;
    private List<Content> moreItems;
    private ListView parentListView;
    private List<ContentItemList> items;
    private int startMorePosition;

    public HomeListAdapter(Context context, List<ContentItemList> items, FragmentManager fragmentManager) {
        super(context, R.layout.item_module, items);
        this.items = items;
        inflater = LayoutInflater.from(context);
        this.fragmentManager = fragmentManager;
    }

    public HomeListPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(HomeListPresenter presenter) {
        this.presenter = presenter;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(parentListView == null) {
            parentListView = (ListView) parent;
        }
        ContentItemList itemList = getItem(position);
        int codeType = getItemViewType(position);
        HomeViewHolder holder;
        if(convertView == null){
            holder = new HomeViewHolder(presenter);
            switch(codeType){
                case NacionConstants.MODULE_CODE_ONE:
                    convertView = inflater.inflate(R.layout.item_module, null);
                    holder.setReferencesForHighlightView(convertView);
                    break;
                case NacionConstants.MODULE_CODE_TWO:
                    convertView = inflater.inflate(R.layout.item_article, null);
                    holder.setReferencesForArticleView(convertView);
                    break;
                case NacionConstants.MODULE_CODE_THREE:
                    convertView = inflater.inflate(R.layout.item_video_gallery, null);
                    holder.setReferencesForVideoGalleryView(convertView);
                    break;
                case NacionConstants.MODULE_CODE_FOURTH:
                    convertView = inflater.inflate(R.layout.item_weather, null);
                    break;
                case NacionConstants.MODULE_CODE_FIVE:
                    convertView = inflater.inflate(R.layout.item_image_gallery, null);
                    break;
                case NacionConstants.MODULE_CODE_EIGHT:
                    convertView = inflater.inflate(R.layout.item_more_news, null);
                    holder.setReferencesForMoreView(convertView, this, itemList);
                    break;
            }
            convertView.setTag(holder);
        }else{
            holder = (HomeViewHolder)convertView.getTag();
        }

        setHolderViewValuesByCodeType(holder, itemList, codeType);


        return convertView;
    }

    private void setHolderViewValuesByCodeType(HomeViewHolder holder, ContentItemList item, int codeType){
        switch(codeType){
            case NacionConstants.MODULE_CODE_ONE:
                holder.setValuesForHighlightView(item);
                break;
            case NacionConstants.MODULE_CODE_TWO:
                holder.setValuesForArticleView(item);
                break;
            case NacionConstants.MODULE_CODE_THREE:
                holder.setValuesForVideoGalleryView(item, fragmentManager);
                break;
            case NacionConstants.MODULE_CODE_FOURTH:
                break;
            case NacionConstants.MODULE_CODE_FIVE:
                break;
            case NacionConstants.MODULE_CODE_EIGHT:
                holder.setValuesForMoreView(item);
                break;
        }
    }

    @Override
    public int getViewTypeCount() {
        return VIEWS_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        ContentItemList content = getItem(position);
        return content.getModule().getTypeCode();
    }

    public List<Content> getMoreItems() {
        return moreItems;
    }

    public void setMoreItems(List<Content> moreItems) {
        this.moreItems = moreItems;
    }

    public ListView getParentListView() {
        return parentListView;
    }

    public void setParentListView(ListView parentListView) {
        this.parentListView = parentListView;
    }

    public int getStartMorePosition() {
        return startMorePosition;
    }

    public void setStartMorePosition(int startMorePosition) {
        this.startMorePosition = startMorePosition;
    }

    public void displayMoreNews(ContentItemList item){
        ListView listView = getParentListView();
        int startPosition = getStartMorePosition();
        int endPosition = startPosition + BLOCK_MORE;

        Module module = item.getModule();
        if(module != null && startPosition < MAX_MORE_DISPLAY){
            remove(item);
            List<Content> contents = module.getContents();
            for (int i = startPosition ; i < endPosition; i++) {
                add(Content.makeDefensiveCopy(contents.get(i)));
            }
            setStartMorePosition(endPosition);
            add(item);
            notifyDataSetChanged();
            listView.smoothScrollToPosition(getCount());
        }else{
            remove(item);
            notifyDataSetChanged();
        }
    }
}
