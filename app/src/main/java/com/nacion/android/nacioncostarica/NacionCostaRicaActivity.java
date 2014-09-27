package com.nacion.android.nacioncostarica;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.nacion.android.nacioncostarica.holders.Section;
import com.nacion.android.nacioncostarica.holders.Setting;
import com.nacion.android.nacioncostarica.home.HomeFragment;
import com.nacion.android.nacioncostarica.main.MainPresenter;
import com.nacion.android.nacioncostarica.main.MainPresenterImpl;
import com.nacion.android.nacioncostarica.main.MainView;
import com.nacion.android.nacioncostarica.popups.SectionPopupListAdapter;
import com.nacion.android.nacioncostarica.popups.SettingPopupListAdapter;


import java.util.ArrayList;
import java.util.List;

public class NacionCostaRicaActivity extends FragmentActivity implements MainView{
    private final static int TABS_COUNT = 1;

    private ViewPager mainViewPager;
    private MainPresenter presenter;
    private List<NacionFragment> fragments;
    private CoverPagerAdapter coverPagerAdapter;

    private ImageButton sectionsImageButton;
    private ImageButton settingsImageButton;
    private PopupWindow sectionsPopupWindow;
    private PopupWindow settingsPopupWindow;

    private FragmentManager mainFragmentManager;

    /*
    String[] menu;
    DrawerLayout dLayout;
    ListView dList;
    ArrayAdapter<String> adapter;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nacion_costa_rica);
        addOurCustomViewToActionBar();




        presenter = new MainPresenterImpl(this);

        mainFragmentManager = getSupportFragmentManager();

        setFragmentsArray();

        coverPagerAdapter = new CoverPagerAdapter(mainFragmentManager);

        mainViewPager = (ViewPager)findViewById(R.id.mainViewPager);
        mainViewPager.setAdapter(coverPagerAdapter);
        mainViewPager.setOffscreenPageLimit(TABS_COUNT);
    }

    private void addOurCustomViewToActionBar(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.custom_actionbar, null);
        //titleTextView = (TextView) customView.findViewById(R.id.title_text);
        //titleTextView.setText(R.string.title_section1);


        sectionsImageButton = (ImageButton) customView.findViewById(R.id.sectionsButton);
        sectionsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sectionsPopupWindow == null) {
                    initiateSectionsPopupWindow(sectionsPopupWindow, R.layout.custom_sections_popup_window, false);
                }else{
                    sectionsPopupWindow.dismiss();
                    sectionsPopupWindow = null;
                }
            }
        });

        settingsImageButton = (ImageButton) customView.findViewById(R.id.settingsButton);
        settingsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (settingsPopupWindow == null) {
                    initiateSettingsPopupWindow(settingsPopupWindow, R.layout.custom_settings_popup_window, true);
                }else{
                    settingsPopupWindow.dismiss();
                    settingsPopupWindow = null;
                }
            }
        });


        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setCustomView(customView);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }

    @SuppressWarnings("deprecation")
    private PopupWindow initiateSectionsPopupWindow(PopupWindow argPopupWindow, int argLayout, boolean argRight) {
        try {
            int x = argRight ? 100 : 0;

            LayoutInflater inflater = LayoutInflater.from(this);
            View layout = inflater.inflate(argLayout, null);

            SectionPopupListAdapter popupListAdapter = new SectionPopupListAdapter(getApplicationContext(), getSections());
            ListView listView = (ListView)layout.findViewById(R.id.sectionListView);
            listView.setAdapter(popupListAdapter);

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

            SettingPopupListAdapter popupListAdapter = new SettingPopupListAdapter(getApplicationContext(), Setting.createDummySettingList());
            ListView listView = (ListView)layout.findViewById(R.id.settingsListView);
            listView.setAdapter(popupListAdapter);

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

    private void setFragmentsArray(){
        fragments = new ArrayList<NacionFragment>();
        fragments.add(HomeFragment.getInstance(mainFragmentManager));
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
                }
            }
            return find;
        }

        @Override
        public int getCount() {
            return TABS_COUNT;
        }
    }
}
