package com.paly.zv.latty.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.paly.zv.latty.web.event.Event;
import com.paly.zv.latty.web.event.EventManager;

final class LatteWebInterface {
    private final WebDelegate DELEGATE;

    public LatteWebInterface(WebDelegate delegate) {
        DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params) {

        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getTnstance().createEvent(action);
        if(event!=null){
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;

    }
}
