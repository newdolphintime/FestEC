package com.paly.zv.latty.util.storage;



import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.paly.zv.latty.app.Latty;


/**
 * Created by 傅令杰 on 2017/4/22
 */

public final class LattePreference {

    /**
     * 提示:
     * Activity.getPreferences(int mode)生成 Activity名.xml 用于Activity内部存储
     * PreferenceManager.getDefaultSharedPreferences(Context)生成 包名_preferences.xml
     * Context.getSharedPreferences(String name,int mode)生成name.xml
     */
    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(Latty.getApplicationContext());
    private static final String APP_PREFERENCES_KEY = "profile";

    private static SharedPreferences getAppPreference() {
        return PREFERENCES;
    }

    public static void setAppProfile(String val) {
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCES_KEY, val)
                .apply();
    }

    public static String getAppProfile() {
        return getAppPreference().getString(APP_PREFERENCES_KEY, null);
    }

    public static JSONObject getAppProfileJson() {
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile() {
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void clearAppPreferences() {
        getAppPreference()
                .edit()
                .clear()
                .apply();
    }

    public static void setAppFlag(String key, boolean flag) {
        getAppPreference()
                .edit()
                .putBoolean(key, flag)
                .apply();
    }

    public static boolean getAppFlag(String key) {
        return getAppPreference()
                .getBoolean(key, false);
    }

    public static void addCustomAppProfile(String key, String val) {
        getAppPreference()
                .edit()
                .putString(key, val)
                .apply();
    }

    public static void addCustomAppProfile(Enum key, String val) {
        addCustomAppProfile(key.name(), val);
    }

    public static String getCustomAppProfile(String key) {
        return getAppPreference().getString(key, null);
    }

    public static String getCustomAppProfile(Enum key) {
        return getCustomAppProfile(key.name());
    }

    public static void removeCustomAppProfile(String key) {
        getAppPreference()
                .edit()
                .remove(key)
                .apply();
    }

    public static void removeCustomAppProfile(Enum key) {
        removeCustomAppProfile(key.name());
    }

}