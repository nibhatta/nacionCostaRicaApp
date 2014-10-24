package com.nacion.android.nacioncostarica.views.home.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.views.home.HomePresenter;
import com.nacion.android.nacioncostarica.views.home.HomeView;
import com.nacion.android.nacioncostarica.views.home.adapters.HomeListAdapter;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class ImageFragment extends Fragment implements GalleryFragment {

    private HomePresenter presenter;
    private ImageFragment singleton;
    private int fragmentIndex;
    private HomeListAdapter homeListAdapter;
    private ListView homeListView;


    public ImageFragment getInstance(int argIndex){
        if(singleton == null){
            singleton = new ImageFragment();
            singleton.setFragmentIndex(argIndex);
        }
        return singleton;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
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

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public String getSection() {
        return null;
    }

    @Override
    public void setSection(String argSection) {

    }
}
