package com.paly.zv.latty.app;

import android.util.Log;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by zhangwei on 2019/7/16.
 */

public class Configurator {
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    //iconify初始化
    //////////////iconify代码初始化///////////////
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    //初始化icon
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    //////////////////////////////
    private Configurator() {

        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
    }

    /////////懒汉式加载///////////
    public static class Holder {
        public static final Configurator INSTANCE = new Configurator();

    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    ////////////////////
    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    //检查是否调用上面的方法初始化没有
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigKeys> key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key.name());
        if (value == null) {
            throw new NullPointerException(key.name() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key.name());
    }

    ////初始化host
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    ////初始化interceptor
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        Log.d("拦截器数量",Integer.toString( INTERCEPTORS.size()));
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTORS.name(), INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTORS.name(), INTERCEPTORS);
        return this;
    }
}
