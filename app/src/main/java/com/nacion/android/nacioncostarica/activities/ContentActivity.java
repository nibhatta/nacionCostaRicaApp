package com.nacion.android.nacioncostarica.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nacion.android.nacioncostarica.R;

/**
 * Created by Gustavo Matarrita on 09/10/2014.
 */
public class ContentActivity extends Activity{
    private ImageView settingsImageButton;
    private TextView sectionTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_content);

        Intent intentInstance = getIntent();
        String sectionTitle = intentInstance.getStringExtra("argSectionTitle");
        int articleId = intentInstance.getIntExtra("argArticleId", 0);
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
}
