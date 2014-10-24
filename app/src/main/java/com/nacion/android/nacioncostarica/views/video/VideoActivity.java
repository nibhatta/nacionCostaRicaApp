package com.nacion.android.nacioncostarica.views.video;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.nacion.android.nacioncostarica.R;
import com.nacion.android.nacioncostarica.constants.NacionConstants;

/**
 * Created by Gustavo Matarrita on 10/10/2014.
 */
public class VideoActivity extends Activity{
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getActionBar().hide();
        createWebView();
    }

    private void createWebView(){
        webView = (WebView) findViewById(R.id.videoPlayerWebView);
        //webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadData(NacionConstants.IFRAME_FOR_TESTING, "text/html", "utf-8");
    }

}
