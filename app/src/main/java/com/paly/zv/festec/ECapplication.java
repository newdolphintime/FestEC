package com.paly.zv.festec;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.paly.zv.latty.app.Latty;
import com.paly.zv.latty.ec.database.DabaseManager;
import com.paly.zv.latty.net.interceptors.DebugIntercepor;
import com.paly.zv.latty.web.event.TestEvent;

/**
 * Created by zhangwei on 2019/7/16.
 */

public class ECapplication extends Application {
    //记住要在manifast文件配置application才可以实现全局配置初始化
    @Override
    public void onCreate() {
        super.onCreate();
        Latty.init(this)
                .withApiHost("https://www.baidu.com/")
                .withIcon(new FontAwesomeModule())
                //.withInterceptor(new DebugIntercepor("13882735553418783435",R.raw.test))
                .withWebEvent("test", new TestEvent())
                .withJavascripInterface("latte")
                .configure();
        DabaseManager.getInstance().init(this);
        Stetho.initializeWithDefaults(this);
    }
}
