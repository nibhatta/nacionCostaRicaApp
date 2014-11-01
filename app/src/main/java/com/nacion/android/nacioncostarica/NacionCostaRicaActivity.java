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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.nacion.android.nacioncostarica.commons.SharedPreferencesManager;
import com.nacion.android.nacioncostarica.models.LeftMenu;
import com.nacion.android.nacioncostarica.models.Site;
import com.nacion.android.nacioncostarica.views.base.activity.BaseActivity;
import com.nacion.android.nacioncostarica.views.content.ContentActivity;
import com.nacion.android.nacioncostarica.constants.NacionConstants;
import com.nacion.android.nacioncostarica.gui.fonts.Fonts;
import com.nacion.android.nacioncostarica.views.home.HomeFragment;
import com.nacion.android.nacioncostarica.views.home.adapters.HomeListAdapter;
import com.nacion.android.nacioncostarica.views.home.adapters.HomeListForTabletAdapter;
import com.nacion.android.nacioncostarica.views.home.adapters.HomeListPresenterImpl;
import com.nacion.android.nacioncostarica.views.home.adapters.MenuListAdapter;
import com.nacion.android.nacioncostarica.views.home.adapters.SubMenuListAdapter;
import com.nacion.android.nacioncostarica.views.home.listeners.MenuOnDragListener;
import com.nacion.android.nacioncostarica.views.main.MainPresenter;
import com.nacion.android.nacioncostarica.views.main.MainPresenterImpl;
import com.nacion.android.nacioncostarica.views.main.MainView;
import com.nacion.android.nacioncostarica.models.Board;
import com.nacion.android.nacioncostarica.models.ContentItemList;
import com.nacion.android.nacioncostarica.models.Menu;
import com.nacion.android.nacioncostarica.useCases.JSONFeed;
import com.nacion.android.nacioncostarica.useCases.JSONFeedImpl;
import com.nacion.android.nacioncostarica.useCases.JSONReader;
import com.nacion.android.nacioncostarica.useCases.JSONReaderImpl;
import com.nacion.android.nacioncostarica.views.video.VideoActivity;

import java.util.ArrayList;
import java.util.List;

public class NacionCostaRicaActivity extends BaseActivity implements MainView {
    private static int tabsCount;
    private int boardIdSelected;
    private List<NacionFragment> fragments;
    private CoverPagerAdapter coverPagerAdapter;
    private ImageButton sectionsImageButton;
    private ImageButton settingsImageButton;
    private ProgressBar progressBar;
    private ReadJSONFeedTask jsonAsyncTask;
    private JSONReader jsonReader = new JSONReaderImpl();
    private JSONFeed jsonFeed = new JSONFeedImpl();
    private ViewPager mainViewPager;
    private MainPresenter presenter;
    private Site site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nacion_costa_rica);
        preferences = SharedPreferencesManager.getPreferences(getApplicationContext());
        presenter = new MainPresenterImpl(this);
        createOurCustomViewToActionBar();
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        mainFragmentManager = getSupportFragmentManager();
    }

    public Fonts getFontsFromChildrenViews(){
        return Fonts.getInstance(getApplicationContext());
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
            }

            @Override
            public void onPageSelected(int position) {
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
            fragments.add(new HomeFragment().getInstance(homeListAdapter, index, board.getId()));
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
            fragments.add(new HomeFragment().getInstance(homeListForTabletAdapter, index, board.getId()));
            index++;
        }
        setTabsCount(fragments.size());
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
            site = jsonReader.createObjectsFromJSONString(json);
            Globals globals = (Globals)getApplication();
            globals.setSite(site);
            presenter.setSite(site);
            presenter.updateView();
            createDrawerLayout();
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
        if(site == null){
            startAsyncTask();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(site == null) {
            startAsyncTask();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_MENU){
            showLeftDrawLayout();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showContentActivityFromViewHolder(String argSectionTitle, int argArticleId){
        Globals globals = (Globals)getApplication();
        globals.setMainPresenter(presenter);
        Intent intent = new Intent(NacionCostaRicaActivity.this, ContentActivity.class);
        intent.putExtra("sectionTitle", argSectionTitle);
        intent.putExtra("articleId", argArticleId);
        startActivity(intent);
    }

    @Override
    public void showVideoActivityFromViewHolder(){
        Intent intent = new Intent(NacionCostaRicaActivity.this, VideoActivity.class);
        startActivity(intent);
    }

    @Override
    public void showRightSubMenu() {

    }

    @Override
    public void goToSection(int boardId) {
        if(mainViewPager != null){
            int index = getFragmentIndexByCode(boardId);
            mainViewPager.setCurrentItem(index);
            closeLeftMenu();
            boardIdSelected = boardId;
        }
    }

    private int getFragmentIndexByCode(int boardId){
        int find = 0;
        for(NacionFragment fragment : fragments){
            if(fragment.getBoardId() == boardId){
                find = fragment.getFragmentIndex();
                break;
            }
        }
        return find;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void goToLeftSubMenu() {
        MenuListAdapter adapter = (MenuListAdapter)leftList.getAdapter();
        adapter.clear();
        adapter.notifyDataSetChanged();
        List<Menu> subMenu = leftMenu.getSubMenu();
        SubMenuListAdapter subMenuAdapter = new SubMenuListAdapter(this, subMenu);
        subMenuAdapter.setPresenter(presenter);
        leftList.setAdapter(subMenuAdapter);
    }

    @Override
    public void backLeftMenu() {
        SubMenuListAdapter adapter = (SubMenuListAdapter)leftList.getAdapter();
        adapter.clear();
        adapter.notifyDataSetChanged();
        List<Menu> menu = leftMenu.getMainMenu();
        MenuListAdapter menuAdapter = new MenuListAdapter(this, menu, mainFragmentManager);
        menuAdapter.setPresenter(presenter);
        menuAdapter.setParentDrawerLayout(drawerLayout);
        leftList.setAdapter(menuAdapter);
    }

    @Override
    public void addItemToMainMenu(int position){
        super.addItemToMainMenu(position);
    }

    @Override
    public void addMenuToNotification(String name) {
        super.addMenuToNotification(name);
    }

    @Override
    public void removeMenuFromNotification(String name) {
        super.removeMenuFromNotification(name);
    }

    @Override
    public void removeItemFromMainMenu(int position){
        super.removeItemFromMainMenu(position);
    }

    @Override
    public void removeItemFromMainMenu(String name){
        super.removeItemFromMainMenu(name);
    }

    @Override
    public void reorderMenuFromMainMenu(int origin, int end){
        super.reorderMenuFromMainMenu(origin, end);
    }

    private void createDrawerLayout(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftList = (ListView) findViewById(R.id.left_drawer);
        rightList = (ListView) findViewById(R.id.right_drawer);

        leftMenu = new LeftMenu(getApplicationContext());
        leftMenu.setMenus(site.getBoardNames());
        List<Menu> menus = leftMenu.getMainMenu();

        MenuListAdapter adapter = new MenuListAdapter(this, menus, mainFragmentManager);
        adapter.setPresenter(presenter);
        adapter.setParentDrawerLayout(drawerLayout);
        leftList.setAdapter(adapter);
        leftList.setOnDragListener(new MenuOnDragListener(leftList, presenter));

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

    public static int getTabsCount() {
        return tabsCount;
    }

    public static void setTabsCount(int tabsCount) {
        NacionCostaRicaActivity.tabsCount = tabsCount;
    }
}