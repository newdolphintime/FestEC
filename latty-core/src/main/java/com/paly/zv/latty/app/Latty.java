package com.paly.zv.latty.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by zhangwei on 2019/7/16.
 */

public final class Latty {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(),context);
        return Configurator.getInstance();

    }
    public static final HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT.name());
    }
}
