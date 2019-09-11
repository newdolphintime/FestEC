package com.paly.zv.latty.ui.refresh;

import android.os.Handler;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.paly.zv.latty.app.Latty;
import com.paly.zv.latty.net.RestClient;
import com.paly.zv.latty.net.callback.ISuccess;

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
    public void firstPage(String url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //Toast.makeText(Latty.getApplicationContext(),response,Toast.LENGTH_SHORT).show();

                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();

    }
}
