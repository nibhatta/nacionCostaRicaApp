package com.nacion.android.nacioncostarica.home.listeners;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.NacionFragment;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 08/10/2014.
 */
public class GalleryOnPageChangeListener implements ViewPager.OnPageChangeListener {
    private List<NacionFragment> fragments;
    private TextView titleViewPager;
    private TextView sectionViewPager;

    public GalleryOnPageChangeListener(List<NacionFragment> argFragments){
        fragments = argFragments;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.i(GalleryOnPageChangeListener.class.getName(), "=====> GalleryOnPageChangeListener onPageScrolled - position: " + position);
    }

    @Override
    public void onPageSelected(int position) {
        Log.i(GalleryOnPageChangeListener.class.getName(), "=====> GalleryOnPageChangeListener onPageSelected - position: " + position);
        changeViewPagerComponents(position);
    }

    private void changeViewPagerComponents(int argPosition){
        for(NacionFragment fragment : fragments){
            if(fragment.getFragmentIndex() == argPosition){
                titleViewPager.setText(fragment.getTitle());
                sectionViewPager.setText(fragment.getSection());
                break;
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public List<NacionFragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<NacionFragment> fragments) {
        this.fragments = fragments;
    }

    public TextView getTitleViewPager() {
        return titleViewPager;
    }

    public void setTitleViewPager(TextView titleViewPager) {
        this.titleViewPager = titleViewPager;
    }

    public TextView getSectionViewPager() {
        return sectionViewPager;
    }

    public void setSectionViewPager(TextView sectionViewPager) {
        this.sectionViewPager = sectionViewPager;
    }
}
