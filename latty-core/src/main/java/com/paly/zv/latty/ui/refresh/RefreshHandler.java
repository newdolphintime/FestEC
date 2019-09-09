package com.paly.zv.latty.ui.refresh;

import android.os.Handler;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.paly.zv.latty.app.Latty;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout refresh_layout) {
        REFRESH_LAYOUT = refresh_layout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }
    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);

    }
    @Override
    public void onRefresh() {
        refresh();

    }
}
