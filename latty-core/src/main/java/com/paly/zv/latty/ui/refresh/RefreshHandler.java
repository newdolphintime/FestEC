package com.paly.zv.latty.ui.refresh;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.paly.zv.latty.app.Latty;
import com.paly.zv.latty.net.RestClient;
import com.paly.zv.latty.net.callback.ISuccess;
import com.paly.zv.latty.ui.recyler.DataConverter;
import com.paly.zv.latty.ui.recyler.MultipleRecyclerAdapter;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener
, BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter multipleRecyclerAdapter = null;
    private final DataConverter CONVERTER;
    public static RefreshHandler create (SwipeRefreshLayout refresh_layout,RecyclerView recyclerView,DataConverter dataConverter){
        return new RefreshHandler(refresh_layout,recyclerView,new PagingBean(),dataConverter);
    }
    public RefreshHandler(SwipeRefreshLayout refresh_layout,RecyclerView recyclerView,PagingBean pagingBean,DataConverter dataConverter) {
        REFRESH_LAYOUT = refresh_layout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
        RECYCLERVIEW = recyclerView;
        BEAN = pagingBean;
        CONVERTER = dataConverter;
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
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //Toast.makeText(Latty.getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                        final JSONObject object = JSON.parseObject(response);
                        Log.d("拿到数据了",response);
                        BEAN.setTotal(object.getInteger("total"))
                        .setPageSize(object.getInteger("page_size"));
                        multipleRecyclerAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        multipleRecyclerAdapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(multipleRecyclerAdapter);
                        BEAN.addIndex();

                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
