package com.nacion.android.nacioncostarica.home.galleryAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.nacion.android.nacioncostarica.NacionFragment;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 26/09/2014.
 */

public class GalleryImagePagerAdapter extends FragmentPagerAdapter {
    private List<NacionFragment> fragments;
    private static int TABS_COUNT = 4;

    public GalleryImagePagerAdapter(FragmentManager argManager, List<NacionFragment> argFragments){
        super(argManager);
        fragments = argFragments;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = searchFragmentToDisplay(position);
        return fragment;
    }

    private Fragment searchFragmentToDisplay(int argPosition){
        Fragment find = null;
        Log.e(this.getClass().getName(), "===========> POSITION:" + argPosition);
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
