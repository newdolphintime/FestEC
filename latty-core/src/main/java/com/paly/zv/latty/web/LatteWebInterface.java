package com.paly.zv.latty.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;

public class LatteWebInterface {
    private final WebDelegate DELEGATE;

    public LatteWebInterface(WebDelegate delegate) {
        DELEGATE = delegate;
    }

    static LatteWebInterface create (WebDelegate delegate){
        return new LatteWebInterface(delegate);
    }
    @JavascriptInterface
    public String  event(String params){

        final String action = JSON.parseObject(params).getString("action");
        return null;

    }
}
