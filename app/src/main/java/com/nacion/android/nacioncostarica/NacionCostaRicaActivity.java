package com.nacion.android.nacioncostarica;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.nacion.android.nacioncostarica.home.HomeFragment;
import com.nacion.android.nacioncostarica.main.MainPresenter;
import com.nacion.android.nacioncostarica.main.MainPresenterImpl;
import com.nacion.android.nacioncostarica.main.MainView;

import java.util.ArrayList;
import java.util.List;

public class NacionCostaRicaActivity extends FragmentActivity implements MainView{
    private final static int TABS_COUNT = 1;

    private ViewPager mainViewPager;
    private MainPresenter presenter;
    private List<NacionFragment> fragments;
    private CoverPagerAdapter coverPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nacion_costa_rica);
        setFragmentsArray();

        presenter = new MainPresenterImpl(this);

        coverPagerAdapter = new CoverPagerAdapter(getSupportFragmentManager());

        mainViewPager = (ViewPager)findViewById(R.id.mainViewPager);
        mainViewPager.setAdapter(coverPagerAdapter);
        mainViewPager.setOffscreenPageLimit(TABS_COUNT);
    }

    private void setFragmentsArray(){
        fragments = new ArrayList<NacionFragment>();
        fragments.add(HomeFragment.getInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nacion_costa_rica, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
