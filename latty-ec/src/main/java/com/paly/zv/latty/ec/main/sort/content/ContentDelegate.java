package com.paly.zv.latty.ec.main.sort.content;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.paly.zv.latty.delegate.LatteDelegate;
import com.paly.zv.latty.ec.R;
import com.paly.zv.latty.ec.R2;
import com.paly.zv.latty.net.RestClient;
import com.paly.zv.latty.net.callback.ISuccess;

import java.util.List;

import butterknife.BindView;

public class ContentDelegate extends LatteDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;

    private List<SectionBean> data =null;

    @BindView(R2.id.rv_list)
    RecyclerView recyclerView=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentID) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentID);
        final ContentDelegate contentDelegate = new ContentDelegate();
        contentDelegate.setArguments(args);
        return contentDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        final StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2
        );
        recyclerView.setLayoutManager(gridLayoutManager);




    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
    }

    private void initData(){
        RestClient.builder().url("http://mock.fulingjie.com/mock-android/data/sort_content_data_1.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        data=new SectionDataConverter().converter(response);
                       final SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content,
                               R.layout.item_section_header,data);

                       recyclerView.setAdapter(sectionAdapter);

                    }
                })

                .build()
                .get();
    }
}
