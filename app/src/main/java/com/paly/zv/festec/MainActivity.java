package com.paly.zv.festec;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.paly.zv.latty.activitys.ProxyActivity;
import com.paly.zv.latty.app.Latty;
import com.paly.zv.latty.delegate.LatteDelegate;
import com.paly.zv.latty.ec.launcher.LauncherDelegate;
import com.paly.zv.latty.ec.launcher.LauncherScrollDelegate;
import com.paly.zv.latty.ec.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        //return new LauncherDelegate();
        return new SignUpDelegate();
    }
}
