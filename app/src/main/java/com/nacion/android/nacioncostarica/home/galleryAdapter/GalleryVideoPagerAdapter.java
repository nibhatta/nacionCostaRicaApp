package com.nacion.android.nacioncostarica.home.galleryAdapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.NacionFragment;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 26/09/2014.
 */

public class GalleryVideoPagerAdapter extends FragmentStatePagerAdapter {
    private List<NacionFragment> fragments;
    private int tabsCount;

    public GalleryVideoPagerAdapter(FragmentManager argManager, List<NacionFragment> argFragments){
        super(argManager);
        fragments = argFragments;
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
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
        return tabsCount;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    public int getTabsCount() {
        return tabsCount;
    }

    public void setTabsCount(int tabsCount) {
        this.tabsCount = tabsCount;
    }
}
