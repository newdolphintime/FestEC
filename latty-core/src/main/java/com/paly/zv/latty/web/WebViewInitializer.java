package com.paly.zv.latty.web;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewInitializer {
    @SuppressLint("SetJavaScriptEnabled")
    public WebView createWebView(WebView webView){

        WebView.setWebContentsDebuggingEnabled(true);

        webView.setHorizontalScrollBarEnabled(false);

        webView.setVerticalScrollBarEnabled(false);

        webView.setDrawingCacheEnabled(true);

        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        //初始化websetting

        final WebSettings settings = webView.getSettings();

        final String ua = settings.getUserAgentString();

        settings.setUserAgentString(ua+"Latte");

        //设置可以使用JS
        settings.setJavaScriptEnabled(true);

        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);

        settings.setSupportZoom(false);

        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);

        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true );
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        return webView;
    }
}
