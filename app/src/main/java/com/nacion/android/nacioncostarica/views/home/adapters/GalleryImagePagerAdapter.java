package com.nacion.android.nacioncostarica.views.home.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
