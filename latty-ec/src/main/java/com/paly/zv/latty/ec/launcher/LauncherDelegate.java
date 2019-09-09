package com.paly.zv.latty.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.paly.zv.latty.app.AccountManager;
import com.paly.zv.latty.app.IUserChecker;
import com.paly.zv.latty.delegate.LatteDelegate;
import com.paly.zv.latty.ec.R;
import com.paly.zv.latty.ec.R2;
import com.paly.zv.latty.ui.launcher.ILauncherListener;
import com.paly.zv.latty.ui.launcher.OnLauncherFinishTag;
import com.paly.zv.latty.ui.launcher.ScrollLauncherTag;
import com.paly.zv.latty.util.storage.LattePreference;
import com.paly.zv.latty.util.timer.BaseTimerTask;
import com.paly.zv.latty.util.timer.ItimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

public class LauncherDelegate extends LatteDelegate implements ItimerListener {

    private ILauncherListener iLauncherListener=null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            iLauncherListener = (ILauncherListener)activity;
        }
    }

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer timer = null;

    private int mCount = 5;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerview() {
        if (timer != null) {
            timer.cancel();
            timer=null;
            checkIsShowSroll();
        }

    }

    private void initTimer() {
        timer = new Timer();
        final BaseTimerTask baseTimerTask = new BaseTimerTask(this);
        timer.schedule(baseTimerTask, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void checkIsShowSroll(){
        if(!LattePreference.getAppFlag(ScrollLauncherTag.HAD_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else{
            //检查用户是否登录了APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if(iLauncherListener!=null){
                        iLauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGND);
                    }

                }

                @Override
                public void onNotSignIn() {
                    if(iLauncherListener!=null){
                        iLauncherListener.onLauncherFinish(OnLauncherFinishTag.NOTSIGND);
                    }

                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                }
                mCount--;
                if (mCount < 0) {
                    if (timer != null) {
                        timer.cancel();
                        timer=null;
                        checkIsShowSroll();
                    }
                }
            }
        });
    }
}
