package com.nacion.android.nacioncostarica.views.content.listeners;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.views.content.fragments.ContentFragment;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 08/10/2014.
 */
public class WeightGalleryOnPageChangeListener implements ViewPager.OnPageChangeListener {
    private List<ContentFragment> fragments;
    private TextView titleTextView;

    public WeightGalleryOnPageChangeListener(List<ContentFragment> argFragments){
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
        for(ContentFragment fragment : fragments){
            if(fragment.getFragmentIndex() == argPosition){
                titleTextView.setText(fragment.getTitle());
                break;
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public List<ContentFragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<ContentFragment> fragments) {
        this.fragments = fragments;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public void setTitleTextView(TextView titleTextView) {
        this.titleTextView = titleTextView;
    }
}
