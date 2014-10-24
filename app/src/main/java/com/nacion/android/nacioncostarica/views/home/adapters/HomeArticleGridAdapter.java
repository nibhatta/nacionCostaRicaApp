package com.nacion.android.nacioncostarica.views.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.views.home.holder.GridViewHolder;
import com.nacion.android.nacioncostarica.models.Content;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 13/10/2014.
 */
public class HomeArticleGridAdapter extends BaseAdapter{
    private List<Content> items;
    private LayoutInflater inflater;
    private HomeListPresenter presenter;

    public HomeArticleGridAdapter(Context argContext, List<Content> argItems){
        inflater = LayoutInflater.from(argContext);
        items = argItems;
    }

    public HomeListPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(HomeListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View getView(int argPosition, View argConvertView, ViewGroup argParent) {
        Content item = (Content)getItem(argPosition);
        GridViewHolder holder;
        if(argConvertView == null){
            holder = new GridViewHolder(presenter);
            argConvertView = inflater.inflate(R.layout.item_article_news, null);
            holder.setComponentsReferencesForArticleGrid(argConvertView);
            argConvertView.setTag(holder);
        }else{
            holder = (GridViewHolder)argConvertView.getTag();
        }
        holder.setValuesForArticleGrid(item);
        return argConvertView;
    }

    @Override
    public long getItemId(int argPosition) {
        return 0;
    }

    @Override
    public Object getItem(int argPosition) {
        return items.get(argPosition);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
