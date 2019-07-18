package com.paly.zv.festec;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.paly.zv.latty.delegate.LatteDelegate;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Integer setLayout() {
        return R.layout.activity_main1;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
