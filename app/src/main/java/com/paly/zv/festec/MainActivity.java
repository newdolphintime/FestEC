package com.paly.zv.festec;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.paly.zv.latty.activitys.ProxyActivity;
import com.paly.zv.latty.delegate.LatteDelegate;
import com.paly.zv.latty.ec.launcher.LauncherDelegate;
import com.paly.zv.latty.ec.main.ECBottomDelegate;
import com.paly.zv.latty.ec.sign.ISignListener;
import com.paly.zv.latty.ec.sign.SignUpDelegate;
import com.paly.zv.latty.ui.launcher.ILauncherListener;
import com.paly.zv.latty.ui.launcher.OnLauncherFinishTag;

import qiu.niorgai.StatusBarCompat;

public class MainActivity extends ProxyActivity implements ISignListener, ILauncherListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        StatusBarCompat.translucentStatusBar(this,true);

    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
        //return new SignUpDelegate();
    }

    @Override
    public void onSignInsuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpsuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag onLauncherFinishTag) {
        switch (onLauncherFinishTag) {
            case SIGND:
                Toast.makeText(this, "启动结束用户登录了", Toast.LENGTH_SHORT).show();
                startWithPop(new ECBottomDelegate());
                break;
            case NOTSIGND:
                Toast.makeText(this, "启动结束用户没有登录", Toast.LENGTH_SHORT).show();
                startWithPop(new SignUpDelegate());
                break;
            default:
                break;
        }
    }
}
