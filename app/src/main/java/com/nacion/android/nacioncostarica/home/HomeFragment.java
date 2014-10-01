package com.nacion.android.nacioncostarica.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nacion.android.nacioncostarica.NacionFragment;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.home.listAdapter.HomeListAdapter;
import com.nacion.android.nacioncostarica.models.Board;
import com.nacion.android.nacioncostarica.models.Content;
import com.nacion.android.nacioncostarica.models.ContentItemList;
import com.nacion.android.nacioncostarica.models.Item;
import com.nacion.android.nacioncostarica.models.Module;
import com.nacion.android.nacioncostarica.models.Site;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomeFragment extends Fragment implements HomeView, NacionFragment {

    private HomePresenter presenter;
    private static HomeFragment singleton;
    private int fragmentIndex;
    private ArrayAdapter<ContentItemList> homeListAdapter;
    private ListView homeListView;

    public static HomeFragment getInstance(ArrayAdapter<ContentItemList> homeListAdapter){
        if(singleton == null){
            singleton = new HomeFragment();
            singleton.setFragmentIndex(NacionConstants.HOME_FRAGMENT_INDEX);
            singleton.homeListAdapter = homeListAdapter;
        }
        return singleton;
    }

    public HomeFragment(){
        presenter = new HomePresenterImpl(this);
    }

    public ArrayAdapter<ContentItemList> getHomeListAdapter() {
        return homeListAdapter;
    }

    public void setHomeListAdapter(ArrayAdapter<ContentItemList> homeListAdapter) {
        this.homeListAdapter = homeListAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        homeListView = (ListView)rootView.findViewById(R.id.homeListView);
        if(homeListAdapter != null) {
            homeListView.setAdapter(homeListAdapter);
        }
        return rootView;
    }

    @Override
    public int getFragmentIndex() {
        return fragmentIndex;
    }

    @Override
    public void setFragmentIndex(int argIndex) {
        fragmentIndex = argIndex;
    }
}
