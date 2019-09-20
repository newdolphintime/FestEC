package com.paly.zv.latty.web.client;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.paly.zv.latty.web.WebDelegate;
import com.paly.zv.latty.web.route.Router;

public class WebViewClientImpl extends WebViewClient {
    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        DELEGATE = delegate;
    }



    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        return Router.getInstance().handleWeburl(DELEGATE,url);
    }
}
