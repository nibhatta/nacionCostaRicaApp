package com.nacion.android.nacioncostarica.home.listAdapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.models.Item;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomeListAdapter extends ArrayAdapter<Item> implements HomeListView {
    private HomeListPresenter presenter;

    public HomeListAdapter(Context context, List<Item> argItems) {
        super(context, R.layout.item_module, argItems);
        presenter = new HomeListPresenterImpl(this);
    }
}
