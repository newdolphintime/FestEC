package com.paly.zv.latty.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

import com.paly.zv.latty.R;
import com.paly.zv.latty.util.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class LatteLoader {
    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;

    public static final String DEFULT_LOADER = LoaderStyle.BallSpinFadeLoaderIndicator.name();

    private static final ArrayList<AppCompatDialog>  LOADERS = new ArrayList<>();
    public static void showloading(Context context,String type){
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type,context);

        dialog.setContentView(avLoadingIndicatorView);

        int diviceWidth = DimenUtil.getScreenWidth();
        int diviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = diviceWidth/LOADER_SIZE_SCALE;
            lp.height = diviceHeight/LOADER_SIZE_SCALE;
            lp.height = lp.height+diviceHeight/LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }
    public static void showloading(Context context){
        showloading(context,DEFULT_LOADER);
    }
    public static void showloading(Context context,Enum<LoaderStyle> loaderStyleEnum){
        showloading(context,loaderStyleEnum.name());
    }
    public static void stopLoading(){
        for (AppCompatDialog dialog :LOADERS){
            if(dialog != null){
                if (dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }
}
