package com.paly.zv.latty.ec.main.index;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.paly.zv.latty.bottom.BottomItemDelegate;
import com.paly.zv.latty.ec.R;
import com.paly.zv.latty.ec.R2;
import com.paly.zv.latty.net.RestClient;
import com.paly.zv.latty.net.callback.ISuccess;
import com.paly.zv.latty.ui.recyler.ItemType;
import com.paly.zv.latty.ui.recyler.MultipleItemEntity;
import com.paly.zv.latty.ui.recyler.MutipleFields;
import com.paly.zv.latty.ui.refresh.RefreshHandler;

import java.util.ArrayList;

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
        refreshHandler.firstPage("http://mock.fulingjie.com/mock-android/api/");

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        refreshHandler = new RefreshHandler(swipeRefreshLayout);

        RestClient.builder()
                .url("http://mock.fulingjie.com/mock-android/api/")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final IndexDataConverter converter = new IndexDataConverter();
                        final ArrayList<MultipleItemEntity> list = converter.setJsonData(response).convert();
                        final String image = list.get(1).getField(MutipleFields.IMAGE_URL);
                        Toast.makeText(getContext(),image,Toast.LENGTH_SHORT).show();

                    }
                })
                .build()
                .get();

    }
}
