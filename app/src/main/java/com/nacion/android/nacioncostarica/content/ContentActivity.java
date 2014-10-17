package com.nacion.android.nacioncostarica.content;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.Globals;
import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.content.listAdapter.ContentListAdapter;
import com.nacion.android.nacioncostarica.models.ArticleContentItemList;

import java.util.List;

/**
 * Created by Gustavo Matarrita on 09/10/2014.
 */
public class ContentActivity extends FragmentActivity implements ContentView{
    private ImageView settingsImageButton;
    private TextView sectionTitleTextView;
    private ListView contentListView;
    private int articleId;
    private IContentPresenter presenter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_content);
        context = getApplicationContext();
        setContentPresenter();
        getIntentArguments();
        createContentListView();
    }

    private void setContentPresenter(){
        Globals globals = (Globals)getApplication();
        presenter = new ContentPresenterImpl(this);
        presenter.setSite(globals.getSite());
    }

    private void getIntentArguments(){
        Intent intentInstance = getIntent();
        String sectionTitle = intentInstance.getStringExtra("argSectionTitle");
        articleId = intentInstance.getIntExtra("argArticleId", 0);
        createOurCustomViewToActionBar(sectionTitle);
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
}
