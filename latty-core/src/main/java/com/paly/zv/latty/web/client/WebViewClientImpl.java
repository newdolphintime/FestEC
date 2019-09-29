package com.paly.zv.latty.web.client;

import android.graphics.Bitmap;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.paly.zv.latty.ui.loader.LatteLoader;
import com.paly.zv.latty.web.WebDelegate;
import com.paly.zv.latty.web.route.Router;

public class WebViewClientImpl extends WebViewClient {
    private final WebDelegate DELEGATE;



    private  IpageLoadListener iPageLoadListener = null;

    public WebViewClientImpl(WebDelegate delegate) {
        DELEGATE = delegate;
    }


    public void setIpageLoadListener(IpageLoadListener ipageLoadListener){
        this.iPageLoadListener = ipageLoadListener;
    }






    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        return Router.getInstance().handleWeburl(DELEGATE,url);
    }


    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (iPageLoadListener !=null){
            iPageLoadListener.onLoadStart();
        }
        LatteLoader.showloading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (iPageLoadListener !=null){
            iPageLoadListener.onLoadFinish();
        }
        LatteLoader.stopLoading();
    }
}
