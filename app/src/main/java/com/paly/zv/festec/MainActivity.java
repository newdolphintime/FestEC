package com.paly.zv.festec;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.paly.zv.latty.activitys.ProxyActivity;
import com.paly.zv.latty.app.Latty;
import com.paly.zv.latty.delegate.LatteDelegate;

public class MainActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
