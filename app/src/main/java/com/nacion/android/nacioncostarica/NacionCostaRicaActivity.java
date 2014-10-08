package com.nacion.android.nacioncostarica;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.holders.Section;
import com.nacion.android.nacioncostarica.holders.Setting;
import com.nacion.android.nacioncostarica.home.HomeFragment;
import com.nacion.android.nacioncostarica.home.listAdapter.HomeListAdapter;
import com.nacion.android.nacioncostarica.home.listAdapter.HomeListForTabletAdapter;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NacionCostaRicaActivity extends FragmentActivity implements MainView{
    private final static int TABS_COUNT = 4;

    private ViewPager mainViewPager;
    private MainPresenter presenter;
    private List<NacionFragment> fragments;
    private CoverPagerAdapter coverPagerAdapter;

    private ImageButton sectionsImageButton;
    private ImageButton settingsImageButton;

    private FragmentManager mainFragmentManager;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    private ListView leftList;
    private ListView rightList;
    private float lastTranslate = 0.0f;

    private JSONReader jsonReader = new JSONReaderImpl();;
    private JSONFeed jsonFeed = new JSONFeedImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nacion_costa_rica);
        addOurCustomViewToActionBar();

        presenter = new MainPresenterImpl(this);

        mainFragmentManager = getSupportFragmentManager();
        presenter.run();

        coverPagerAdapter = new CoverPagerAdapter(mainFragmentManager);

        mainViewPager = (ViewPager)findViewById(R.id.mainViewPager);
        mainViewPager.setAdapter(coverPagerAdapter);
        mainViewPager.setOffscreenPageLimit(TABS_COUNT);
        setMainViewPagerOtherEvents();

        String[] menu = new String[]{"Home","Android","Windows","Linux","Raspberry Pi","WordPress","Videos","Contact Us"};
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        leftList = (ListView) findViewById(R.id.left_drawer);
        rightList = (ListView) findViewById(R.id.right_drawer);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        leftList.setAdapter(adapter);
        rightList.setAdapter(adapter);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_launcher, R.string.dummy_text_date, R.string.dummy_text_title){
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


    private void addOurCustomViewToActionBar(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.custom_actionbar, null);
        //titleTextView = (TextView) customView.findViewById(R.id.title_text);
        //titleTextView.setText(R.string.title_section1);

        settingsImageButton = (ImageButton) customView.findViewById(R.id.settingsButton);
        settingsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickSettingsButton();
            }
        });

        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setCustomView(customView);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            presenter.onClickHomeButton();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("deprecation")
    private PopupWindow initiateSectionsPopupWindow(PopupWindow argPopupWindow, int argLayout, boolean argRight) {
        try {
            int x = argRight ? 100 : 0;

            LayoutInflater inflater = LayoutInflater.from(this);
            View layout = inflater.inflate(argLayout, null);

            /*
            SectionPopupListAdapter popupListAdapter = new SectionPopupListAdapter(getApplicationContext(), getSections());
            ListView listView = (ListView)layout.findViewById(R.id.sectionListView);
            listView.setAdapter(popupListAdapter);
            */

            //setShareOptionsEvents(layout);

            argPopupWindow = new PopupWindow(layout, 400, FrameLayout.LayoutParams.MATCH_PARENT,true);
            argPopupWindow.setOutsideTouchable(true);
            argPopupWindow.setBackgroundDrawable(new BitmapDrawable());

            if(isTablet(getApplicationContext())) {
                argPopupWindow.showAsDropDown(sectionsImageButton, x, 0);
            }else{
                argPopupWindow.showAsDropDown(sectionsImageButton, x, -100);
            }

        } catch (Exception e) {
            Log.e(NacionCostaRicaActivity.class.getName(), e.getLocalizedMessage());
        }
        return argPopupWindow;
    }

    @SuppressWarnings("deprecation")
    private PopupWindow initiateSettingsPopupWindow(PopupWindow argPopupWindow, int argLayout, boolean argRight) {
        try {
            int x = argRight ? 100 : 0;

            LayoutInflater inflater = LayoutInflater.from(this);
            View layout = inflater.inflate(argLayout, null);

            /*
            SettingPopupListAdapter popupListAdapter = new SettingPopupListAdapter(getApplicationContext(), Setting.createDummySettingList());
            ListView listView = (ListView)layout.findViewById(R.id.settingsListView);
            listView.setAdapter(popupListAdapter);
            */

            //setShareOptionsEvents(layout);

            argPopupWindow = new PopupWindow(layout, 400, FrameLayout.LayoutParams.MATCH_PARENT,true);
            argPopupWindow.setOutsideTouchable(true);
            argPopupWindow.setBackgroundDrawable(new BitmapDrawable());

            if(isTablet(getApplicationContext())) {
                argPopupWindow.showAsDropDown(sectionsImageButton, x, 0);
            }else{
                argPopupWindow.showAsDropDown(sectionsImageButton, x, -100);
            }

        } catch (Exception e) {
            Log.e(NacionCostaRicaActivity.class.getName(), e.getLocalizedMessage());
        }
        return argPopupWindow;
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

    @Override
    public void updateViewFromModel() {
        Context context = getApplicationContext();
        ArrayAdapter<ContentItemList> homeListAdapter = isTablet(context) ?
                new HomeListForTabletAdapter(context, getContentsForTablet(), mainFragmentManager) :
                new HomeListAdapter(context, getContentsForPhone(), mainFragmentManager);

        fragments = new ArrayList<NacionFragment>();
        fragments.add(new HomeFragment().getInstance(homeListAdapter, 0));
        /*
        fragments.add(new HomeFragment().getInstance(homeListAdapter, 1));
        fragments.add(new HomeFragment().getInstance(homeListAdapter, 2));
        fragments.add(new HomeFragment().getInstance(homeListAdapter, 3));
        */
    }

    private List<ContentItemList> getContentsForPhone(){
        List<ContentItemList> list = Collections.emptyList();
        for(Board board:presenter.getSite().getBoards()){
            list = board.getAllContentsForPhoneDevice();
            break;
        }
        return list;
    }

    private List<ContentItemList> getContentsForTablet(){
        Board board = Board.createDummyBoardCoreForTablet();
        return board.getAllContents();
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
            return TABS_COUNT;
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
            presenter.setSite(site);
            presenter.run();
        }
    }
}
