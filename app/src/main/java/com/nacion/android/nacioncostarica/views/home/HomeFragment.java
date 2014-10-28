package com.nacion.android.nacioncostarica.views.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nacion.android.nacioncostarica.NacionFragment;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.models.ContentItemList;

/**
 * Created by Gustavo Matarrita on 22/09/2014.
 */
public class HomeFragment extends Fragment implements NacionFragment {
    private int fragmentIndex;
    private int boardId;
    private HomeFragment instance;
    private WebView webView;
    private ArrayAdapter<ContentItemList> homeListAdapter;
    private ListView homeListView;

    public HomeFragment getInstance(ArrayAdapter<ContentItemList> homeListAdapter, int index, int boardId){
        if(instance == null){
            instance = new HomeFragment();
            instance.setFragmentIndex(index);
            instance.setBoardId(boardId);
            instance.homeListAdapter = homeListAdapter;
        }
        return instance;
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
        createWebView(rootView);
        createHomeListView(rootView);
        return rootView;
    }

    private void createWebView(View argRootView){
        webView = (WebView)argRootView.findViewById(R.id.webViewAdvertisement);
        webView.loadUrl(NacionConstants.ADVERTISEMENT_URL);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    private void createHomeListView(View argRootView){
        homeListView = (ListView)argRootView.findViewById(R.id.homeListView);
        if(homeListAdapter != null){
            homeListView.setAdapter(homeListAdapter);
        }
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

    @Override
    public int getBoardId() {
        return boardId;
    }

    @Override
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
}
