package com.paly.zv.latty.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.paly.zv.latty.app.Latty;

public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources = Latty.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics =resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
    public static int getScreenHeight(){
        final Resources resources = Latty.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics =resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
