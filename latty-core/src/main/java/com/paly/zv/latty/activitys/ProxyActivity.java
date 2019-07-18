package com.paly.zv.latty.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.ContentFrameLayout;

import com.paly.zv.latty.R;
import com.paly.zv.latty.delegate.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class ProxyActivity extends SupportActivity {
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }
    private void initContainer(@Nullable Bundle savedInstanceState){

         final FrameLayout contentFrameLayout = new FrameLayout(this);
         contentFrameLayout.setId(R.id.delegate_continer);
         setContentView(contentFrameLayout);
         if(savedInstanceState == null){
             loadRootFragment(R.id.delegate_continer,setRootDelegate());
         }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
