package com.paly.zv.festec;

import android.app.Application;
import android.content.Context;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.paly.zv.latty.app.Latty;

/**
 * Created by zhangwei on 2019/7/16.
 */

public class ECapplication extends Application {
    //记住要在manifast文件配置application才可以实现全局配置初始化
    @Override
    public void onCreate() {
        super.onCreate();
        Latty.init(this)
                .withApiHost("http://gank.io/")
                .withIcon(new FontAwesomeModule())
                .configure();
    }
}
