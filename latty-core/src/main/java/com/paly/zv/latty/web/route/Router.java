package com.paly.zv.latty.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.URLUtil;
import android.webkit.WebView;

import androidx.core.content.ContextCompat;

import com.paly.zv.latty.delegate.LatteDelegate;
import com.paly.zv.latty.web.WebDelegate;
import com.paly.zv.latty.web.WebDelegateImpl;

public class Router {
    public Router() {
    }

    private static class Holder {
        private static final Router INSTANCE = new Router();

    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handleWeburl(WebDelegate delegate, String url) {

        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }
        final LatteDelegate topDelegate = delegate.getTopDelegate();

        //final LatteDelegate parentDelegate = delegate.getParentDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
//        if (parentDelegate != null) {
//            delegate.start(webDelegate);
//        } else {
//            parentDelegate.start(webDelegate);
//        }
        topDelegate.start(webDelegate);

        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null!");
        }

    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);

    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    private void callPhone(Context context, String url) {
        final Intent intent = new Intent((Intent.ACTION_DIAL));
        final Uri data = Uri.parse(url);
        intent.setData(data);
        context.startActivity(intent);
        ContextCompat.startActivity(context, intent, null);

    }
}
