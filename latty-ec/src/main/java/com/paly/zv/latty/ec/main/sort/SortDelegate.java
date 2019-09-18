package com.paly.zv.latty.ec.main.sort;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.paly.zv.latty.bottom.BottomItemDelegate;
import com.paly.zv.latty.ec.R;
import com.paly.zv.latty.ec.main.sort.content.ContentDelegate;
import com.paly.zv.latty.ec.main.sort.list.VerticalListDelegate;

public class SortDelegate extends BottomItemDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container,listDelegate);
        loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));

    }
}
