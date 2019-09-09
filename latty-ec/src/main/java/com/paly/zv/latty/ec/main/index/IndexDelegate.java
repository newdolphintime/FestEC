package com.paly.zv.latty.ec.main.index;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.paly.zv.latty.bottom.BottomItemDelegate;
import com.paly.zv.latty.ec.R;
import com.paly.zv.latty.ec.R2;
import com.paly.zv.latty.ui.refresh.RefreshHandler;

import butterknife.BindView;

public class IndexDelegate extends BottomItemDelegate {

    private RefreshHandler refreshHandler = null;

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout swipeRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar toolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView iconTextView = null;
    @BindView(R2.id.et_serch_view)
    AppCompatEditText appCompatEditText = null;

    private void initRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.darker_gray, android.R.color.holo_blue_bright);
        swipeRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        refreshHandler = new RefreshHandler(swipeRefreshLayout);

    }
}