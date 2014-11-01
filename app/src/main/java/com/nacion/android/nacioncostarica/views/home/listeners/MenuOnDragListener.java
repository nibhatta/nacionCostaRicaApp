package com.nacion.android.nacioncostarica.views.home.listeners;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nacion.android.nacioncostarica.views.home.adapters.MenuListAdapter;
import com.nacion.android.nacioncostarica.views.main.MainPresenter;

/**
 * Created by Gustavo Matarrita on 31/10/2014.
 */
public class MenuOnDragListener extends MenuListener implements View.OnDragListener{
    private ListView listView;
    private MainPresenter presenter;

    public MenuOnDragListener(ListView listView, MainPresenter presenter){
        this.listView = listView;
        this.presenter = presenter;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        final int action = dragEvent.getAction();
        boolean handleEvent = true;
        switch(action){
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DROP:
                int itemPosition = listView.pointToPosition((int)dragEvent.getX(), (int)dragEvent.getY());
                Log.e(MenuOnTouchListener.class.getName(), "=====> End position: " + itemPosition);
                View itemView = listView.findViewById(itemPosition );
                Log.e(MenuOnTouchListener.class.getName(), "=====> Id drag and drop view: " + getStartPosition());

                int origin = getStartPosition();
                int end = itemPosition;
                presenter.reorderMenuFromMainMenu(origin, end);
                MenuListAdapter adapter = (MenuListAdapter)listView.getAdapter();
                adapter.notifyDataSetChanged();
                break;
        }
        return handleEvent;
    }
}
