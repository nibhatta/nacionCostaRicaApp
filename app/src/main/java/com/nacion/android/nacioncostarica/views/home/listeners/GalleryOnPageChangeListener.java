package com.nacion.android.nacioncostarica.views.home.listeners;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.views.home.fragments.GalleryFragment;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 08/10/2014.
 */
public class GalleryOnPageChangeListener implements ViewPager.OnPageChangeListener {
    private List<GalleryFragment> fragments;
    private TextView titleViewPager;
    private TextView sectionViewPager;

    public GalleryOnPageChangeListener(List<GalleryFragment> argFragments){
        fragments = argFragments;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        changeViewPagerComponents(position);
    }

    private void changeViewPagerComponents(int argPosition){
        for(GalleryFragment fragment : fragments){
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

    public List<GalleryFragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<GalleryFragment> fragments) {
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
