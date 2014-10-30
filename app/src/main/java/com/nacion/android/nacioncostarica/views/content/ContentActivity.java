package com.nacion.android.nacioncostarica.views.content;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.Globals;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.commons.SharedPreferencesManager;
import com.nacion.android.nacioncostarica.models.LeftMenu;
import com.nacion.android.nacioncostarica.models.Menu;
import com.nacion.android.nacioncostarica.views.base.activity.BaseActivity;
import com.nacion.android.nacioncostarica.views.content.adapters.ContentListAdapter;
import com.nacion.android.nacioncostarica.gui.fonts.Fonts;
import com.nacion.android.nacioncostarica.models.ArticleContentItemList;
import com.nacion.android.nacioncostarica.views.content.adapters.ContentMenuListAdapter;
import com.nacion.android.nacioncostarica.views.content.adapters.ContentSubMenuListAdapter;
import com.nacion.android.nacioncostarica.views.home.adapters.MenuListAdapter;
import com.nacion.android.nacioncostarica.views.home.adapters.SubMenuListAdapter;
import com.nacion.android.nacioncostarica.views.main.MainPresenter;

import java.util.List;
import java.util.jar.Manifest;

/**
 * Created by Gustavo Matarrita on 09/10/2014.
 */
public class ContentActivity extends BaseActivity implements ContentView{
    private ImageView settingsImageButton;
    private TextView sectionTitleTextView;
    private ListView contentListView;
    private int articleId;
    private Context context;
    private ContentPresenter presenter;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_content);
        context = getApplicationContext();
        preferences = SharedPreferencesManager.getPreferences(getApplicationContext());
        setContentPresenter();
        getIntentArguments();
        createContentListView();
        createDrawerLayout();
    }

    private void setContentPresenter(){
        Globals globals = (Globals)getApplication();
        presenter = new ContentPresenterImpl(this);
        presenter.setSite(globals.getSite());
        mainPresenter = globals.getMainPresenter();
    }

    private void createContentListView(){
        if(presenter.articleNotExistsFromView(articleId)){
            String message = "=====> Article with the id %d doesn´t exist!!!", articleId;
            Log.d(ContentActivity.class.getName(), message);
            return;
        }
        contentListView = (ListView)findViewById(R.id.contentListView);
        List<ArticleContentItemList> articleContents = presenter.getArticleContentFromView(articleId);
        ContentListAdapter contentListAdapter = new ContentListAdapter(context, articleContents, getSupportFragmentManager());
        contentListAdapter.setPresenter(presenter);
        contentListView.setAdapter(contentListAdapter);
    }

    private void createDrawerLayout(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftList = (ListView) findViewById(R.id.left_drawer);
        rightList = (ListView) findViewById(R.id.right_drawer);

        leftMenu = new LeftMenu(getApplicationContext());
        leftMenu.setMenus(presenter.getSite().getBoardNames());
        List<Menu> menus = leftMenu.getMainMenu();

        ContentMenuListAdapter adapter = new ContentMenuListAdapter(this, menus, mainFragmentManager);
        adapter.setPresenter(presenter);
        adapter.setParentDrawerLayout(drawerLayout);
        leftList.setAdapter(adapter);
        rightList.setAdapter(adapter);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_launcher, 0, 0){
            public void onDrawerSlide(View drawerView, float slideOffset){
                int moveDirection = drawerView.equals(leftList) ? 1 : -1;
                float moveWidth = drawerView.equals(leftList) ? (leftList.getWidth() * slideOffset) : (rightList.getWidth() * slideOffset);
                float moveFactor = moveDirection * moveWidth;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
                    contentListView.setTranslationX(moveFactor);
                }else{
                    TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                    anim.setDuration(0);
                    anim.setFillAfter(true);
                    contentListView.startAnimation(anim);
                    lastTranslate = moveFactor;
                }
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
    }



    private void getIntentArguments(){
        Intent intentInstance = getIntent();
        String sectionTitle = intentInstance.getStringExtra("sectionTitle");
        articleId = intentInstance.getIntExtra("articleId", 0);
        createOurCustomViewToActionBar(sectionTitle);
    }

    private void createOurCustomViewToActionBar(String argSectionTitle){
        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.custom_general_actionbar, null);
        sectionTitleTextView = (TextView) customView.findViewById(R.id.sectionTitleActivity);
        sectionTitleTextView.setText(argSectionTitle);

        settingsImageButton = (ImageButton) customView.findViewById(R.id.settingsButton);
        settingsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //presenter.onClickSettingsButton();
            }
        });

        ActionBar actionBar = getActionBar();
        if(actionBar != null){
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Fonts getFontsFromChildrenViews(){
        return Fonts.getInstance(getApplicationContext());
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void goToLeftSubMenu() {
        ContentMenuListAdapter adapter = (ContentMenuListAdapter)leftList.getAdapter();
        adapter.clear();
        adapter.notifyDataSetChanged();
        List<Menu> subMenu = leftMenu.getSubMenu();
        ContentSubMenuListAdapter subMenuAdapter = new ContentSubMenuListAdapter(this, subMenu);
        subMenuAdapter.setPresenter(presenter);
        leftList.setAdapter(subMenuAdapter);
    }

    @Override
    public void backLeftMenu() {
        ContentSubMenuListAdapter adapter = (ContentSubMenuListAdapter)leftList.getAdapter();
        adapter.clear();
        adapter.notifyDataSetChanged();
        List<Menu> menu = leftMenu.getMainMenu();
        ContentMenuListAdapter menuAdapter = new ContentMenuListAdapter(this, menu, mainFragmentManager);
        menuAdapter.setPresenter(presenter);
        menuAdapter.setParentDrawerLayout(drawerLayout);
        leftList.setAdapter(menuAdapter);
    }

    @Override
    public void goToSection(int boardId){
        finish();
        mainPresenter.goToSectionFromAdapter(boardId);
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


}
