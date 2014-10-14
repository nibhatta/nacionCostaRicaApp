package com.nacion.android.nacioncostarica;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.nacion.android.nacioncostarica.content.ContentActivity;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.holders.Section;
import com.nacion.android.nacioncostarica.home.HomeFragment;
import com.nacion.android.nacioncostarica.home.adapters.HomeListAdapter;
import com.nacion.android.nacioncostarica.home.adapters.HomeListForTabletAdapter;
import com.nacion.android.nacioncostarica.home.adapters.HomeListPresenterImpl;
import com.nacion.android.nacioncostarica.main.MainPresenter;
import com.nacion.android.nacioncostarica.main.MainPresenterImpl;
import com.nacion.android.nacioncostarica.main.MainView;
import com.nacion.android.nacioncostarica.models.Board;
import com.nacion.android.nacioncostarica.models.ContentItemList;
import com.nacion.android.nacioncostarica.models.Site;
import com.nacion.android.nacioncostarica.useCases.JSONFeed;
import com.nacion.android.nacioncostarica.useCases.JSONFeedImpl;
import com.nacion.android.nacioncostarica.useCases.JSONReader;
import com.nacion.android.nacioncostarica.useCases.JSONReaderImpl;
import com.nacion.android.nacioncostarica.video.VideoActivity;

import java.util.ArrayList;
import java.util.List;

public class NacionCostaRicaActivity extends FragmentActivity implements MainView{
    private static int tabsCount;
    private ViewPager mainViewPager;
    private WebView webView;
    private MainPresenter presenter;
    private List<NacionFragment> fragments;
    private CoverPagerAdapter coverPagerAdapter;
    private ImageButton sectionsImageButton;
    private ImageButton settingsImageButton;
    private FragmentManager mainFragmentManager;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private ProgressBar progressBar;
    private ListView leftList;
    private ListView rightList;
    private float lastTranslate = 0.0f;
    private ReadJSONFeedTask jsonAsyncTask;
    private JSONReader jsonReader = new JSONReaderImpl();
    private JSONFeed jsonFeed = new JSONFeedImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nacion_costa_rica);
        createOurCustomViewToActionBar();
        createDrawerLayout();
        createWebView();
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        presenter = new MainPresenterImpl(this);
        mainFragmentManager = getSupportFragmentManager();
    }

    private void createWebView(){
        webView = (WebView) findViewById(R.id.webViewAdvertisement);
        webView.loadUrl(NacionConstants.ADVERTISEMENT_URL);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    private void createOurCustomViewToActionBar(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.custom_actionbar, null);
        //titleTextView = (TextView) customView.findViewById(R.id.title_text);
        //titleTextView.setText(R.string.title_section1);

        settingsImageButton = (ImageButton) customView.findViewById(R.id.settingsButton);
        settingsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRightDrawLayout();
            }
        });

        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setCustomView(customView);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }

    private void createDrawerLayout(){
        String[] menu = new String[]{"Home","Android","Windows","Linux","Raspberry Pi","WordPress","Videos","Contact Us"};
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        leftList = (ListView) findViewById(R.id.left_drawer);
        rightList = (ListView) findViewById(R.id.right_drawer);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        leftList.setAdapter(adapter);
        rightList.setAdapter(adapter);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_launcher, 0, 0){
            public void onDrawerSlide(View drawerView, float slideOffset){
                int moveDirection = drawerView.equals(leftList) ? 1 : -1;
                float moveWidth = drawerView.equals(leftList) ? (leftList.getWidth() * slideOffset) : (rightList.getWidth() * slideOffset);
                float moveFactor = moveDirection * moveWidth;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
                    mainViewPager.setTranslationX(moveFactor);
                }else{
                    TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                    anim.setDuration(0);
                    anim.setFillAfter(true);
                    mainViewPager.startAnimation(anim);
                    lastTranslate = moveFactor;
                }
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
    }

    private List<Section> getSections(){
        List<Section> sections = new ArrayList<Section>();
        for(int i=0; i<10; i++){
            Section section = Section.createDummySectionCore(i < 5 ? true : false);
            sections.add(section);
        }
        return sections;
    }

    private static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private void createCoverPagerAdapter(){
        coverPagerAdapter = new CoverPagerAdapter(mainFragmentManager);
        mainViewPager = (ViewPager)findViewById(R.id.mainViewPager);
        mainViewPager.setAdapter(coverPagerAdapter);
        mainViewPager.setOffscreenPageLimit(tabsCount);
        setMainViewPagerOtherEvents();
    }

    private void setMainViewPagerOtherEvents() {
        mainViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(NacionCostaRicaActivity.class.getName(), "*****>> onPageScrolled - position: " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(NacionCostaRicaActivity.class.getName(), "*****>> onPageSelected - position: " + position);
            }

            private Fragment searchFragmentToDisplay(int argPosition){
                Fragment find = null;
                for(NacionFragment fragment : fragments){
                    if(fragment.getFragmentIndex() == argPosition){
                        find = (Fragment)fragment;
                    }
                }
                return find;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void updateViewFromModel() {
        Context context = getApplicationContext();
        if(!isTablet(context)){
            setSectionsFragmentForPhone();
        }else{
            setSectionsFragmentForTablet();
        }
        createCoverPagerAdapter();
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void setSectionsFragmentForPhone(){
        Context context = getApplicationContext();
        int index = 0;
        fragments = new ArrayList<NacionFragment>();
        for(Board board:presenter.getSite().getBoards()){
            List<ContentItemList> items = board.getAllContentsForPhoneDevice();
            HomeListAdapter homeListAdapter = new HomeListAdapter(context, items, mainFragmentManager);
            homeListAdapter.setPresenter(new HomeListPresenterImpl(this));
            fragments.add(new HomeFragment().getInstance(homeListAdapter, index));
            index++;
        }
        setTabsCount(fragments.size());
    }

    private void setSectionsFragmentForTablet(){
        Context context = getApplicationContext();
        int index = 0;
        fragments = new ArrayList<NacionFragment>();
        for(Board board:presenter.getSite().getBoards()){
            List<ContentItemList> items = board.getAllContentsForTabletDevice();
            HomeListForTabletAdapter homeListForTabletAdapter = new HomeListForTabletAdapter(context, items, mainFragmentManager);
            homeListForTabletAdapter.setPresenter(new HomeListPresenterImpl(this));
            fragments.add(new HomeFragment().getInstance(homeListForTabletAdapter, index));
            index++;
        }
        setTabsCount(fragments.size());
    }

    private List<ContentItemList> getContentsForTablet(){
        return null;
    }

    private void startAsyncTask(){
        cancelAsyncTask();
        jsonAsyncTask = new ReadJSONFeedTask();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            jsonAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, NacionConstants.JSON_URL);
        }else{
            jsonAsyncTask.execute(NacionConstants.JSON_URL);
        }
        progressBar.setVisibility(View.VISIBLE);
    }

    private void cancelAsyncTask(){
        if(jsonAsyncTask != null && jsonAsyncTask.getStatus() != ReadJSONFeedTask.Status.FINISHED){
            jsonAsyncTask.cancel(true);
        }
    }

    private class CoverPagerAdapter extends FragmentPagerAdapter{
        public CoverPagerAdapter(FragmentManager argManager){
            super(argManager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = searchFragmentToDisplay(position);
            return fragment;
        }

        private Fragment searchFragmentToDisplay(int argPosition){
            Fragment find = null;
            for(NacionFragment fragment : fragments){
                if(fragment.getFragmentIndex() == argPosition){
                    find = (Fragment)fragment;
                    break;
                }
            }
            return find;
        }

        @Override
        public int getCount() {
            return getTabsCount();
        }
    }

    private class ReadJSONFeedTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls) {
            return jsonFeed.readJSONFeed(urls[0]);
        }

        @Override
        protected void onPostExecute(String json) {
            Site site = jsonReader.createObjectsFromJSONString(json);
            Globals globals = (Globals)getApplication();
            globals.setSite(site);
            presenter.setSite(site);
            presenter.updateView();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelAsyncTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAsyncTask();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startAsyncTask();
    }



    @Override
    public void showContentActivityFromViewHolder(String argSectionTitle, int argArticleId){
        Intent intent = new Intent(NacionCostaRicaActivity.this, ContentActivity.class);
        intent.putExtra("argSectionTitle", argSectionTitle);
        intent.putExtra("argArticleId", argArticleId);
        startActivity(intent);
    }

    @Override
    public void showVideoActivityFromViewHolder(){
        Intent intent = new Intent(NacionCostaRicaActivity.this, VideoActivity.class);
        startActivity(intent);
    }

    @Override
    public void showRightDrawLayout() {
        if(drawerLayout != null && rightList != null){
            if(drawerLayout.isDrawerOpen(leftList)){
                drawerLayout.closeDrawer(leftList);
            }
            drawerLayout.openDrawer(rightList);
        }
    }

    @Override
    public void showLeftDrawLayout() {
        if(drawerLayout != null && leftList != null){
            if(drawerLayout.isDrawerOpen(rightList)){
                drawerLayout.closeDrawer(rightList);
            }
            drawerLayout.openDrawer(leftList);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            showLeftDrawLayout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static int getTabsCount() {
        return tabsCount;
    }

    public static void setTabsCount(int tabsCount) {
        NacionCostaRicaActivity.tabsCount = tabsCount;
    }
}
