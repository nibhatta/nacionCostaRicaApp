package com.nacion.android.nacioncostarica.home.listAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.models.Item;
import com.nacion.android.nacioncostarica.models.Module;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomeListAdapter extends ArrayAdapter<Content> implements HomeListView {
    private static final int VIEWS_TYPE_COUNT = 2;
    private HomeListPresenter presenter;
    private LayoutInflater inflater;

    public HomeListAdapter(Context context, List<Content> argContents) {
        super(context, R.layout.item_module, argContents);
        presenter = new HomeListPresenterImpl(this);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int codeType = getItemViewType(position);
        if(convertView == null){
            switch(codeType){
                case NacionConstants.MODULE_CODE_ONE:
                    convertView = inflater.inflate(R.layout.item_module, null);
                    break;
                case NacionConstants.MODULE_CODE_TWO:
                    convertView = inflater.inflate(R.layout.item_article, null);
                    break;
            }

        }
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return VIEWS_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        Content content = getItem(position);
        return content.getModule().getTypeCode();
    }
}
