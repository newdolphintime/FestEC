package com.paly.zv.latty.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

import androidx.annotation.Nullable;

import com.paly.zv.latty.app.ConfigKeys;
import com.paly.zv.latty.app.Latty;
import com.paly.zv.latty.delegate.LatteDelegate;
import com.paly.zv.latty.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public abstract class WebDelegate extends LatteDelegate implements IwebViewInitializer{

    private WebView webView = null;

    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;

    private boolean mIswebViewAvailable = false;

    private LatteDelegate mTopDelegate = null;

    public WebDelegate() {

    }

    public abstract IwebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebview();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebview() {
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
        } else {
            final IwebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                webView = webViewWeakReference.get();
                webView = initializer.initWebView(webView);
                webView.setWebViewClient(initializer.initWebViewClient());
                webView.setWebChromeClient(initializer.initWebChromeClient());
                final String name = (String) Latty.getConfigurations().get(ConfigKeys.JAVASCRIPT_INTERFACE.name());
                //Log.d("javascrtpi", name);
                webView.addJavascriptInterface(LatteWebInterface.create(this), name);

                mIswebViewAvailable = true;

            } else {
                throw new NullPointerException("IwebViewInitializer is null!");
            }
        }
    }

    public void setTopDelegate(LatteDelegate delegate){
        mTopDelegate=delegate;
    }
    public LatteDelegate getTopDelegate(){
        if (mTopDelegate==null){
            mTopDelegate=this;
        }
        return mTopDelegate;
    }

    public WebView getWebView() {
        if (webView == null) {
            throw new NullPointerException("webView is null");
        }
        return mIswebViewAvailable ? webView : null;
    }

    public String getUrl(){
        if (mUrl == null) {
            throw new NullPointerException("mUrl is null");
        }
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (webView != null) {
            webView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (webView != null) {
            webView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIswebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
        }
    }
}

