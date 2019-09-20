package com.paly.zv.latty.web;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.paly.zv.latty.web.chromeclient.WebChromeClientImpl;
import com.paly.zv.latty.web.client.WebViewClientImpl;
import com.paly.zv.latty.web.route.RouteKeys;
import com.paly.zv.latty.web.route.Router;

public class WebDelegateImpl extends WebDelegate{

    public static WebDelegateImpl create (String url){
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }
    @Override
    public IwebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if(getUrl()!=null){
            Router.getInstance().loadPage(this,getUrl());
        }

    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
