package com.paly.zv.latty.ui.recyler;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.paly.zv.latty.R;
import com.paly.zv.latty.ui.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements
        BaseQuickAdapter.SpanSizeLookup, OnItemClickListener {
    private boolean isInitBanner = false;

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter dataConverter) {

        return new MultipleRecyclerAdapter(dataConverter.convert());
    }

    //不同布局
    private void init() {
        addItemType(ItemType.TEXT, R.layout.item_mutiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);

        setSpanSizeLookup(this);

        openLoadAnimation();

        //多次执行动画

        isFirstOnly(false);


    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);

    }

    @Override
    protected void convert(@NonNull MultipleViewHolder holder, MultipleItemEntity item) {

        final String text;
        final String imgUrl;
        final ArrayList<String> bannerImages;
        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = item.getField(MutipleFields.TEXT);
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imgUrl = item.getField(MutipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imgUrl).
                        diskCacheStrategy(DiskCacheStrategy.ALL).
                        dontAnimate().
                        centerCrop().
                        into((ImageView) holder.getView(R.id.img_single));
                break;
            case ItemType.TEXT_IMAGE:
                text = item.getField(MutipleFields.TEXT);
                imgUrl = item.getField(MutipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imgUrl).
                        diskCacheStrategy(DiskCacheStrategy.ALL).
                        dontAnimate().
                        centerCrop().
                        into((ImageView) holder.getView(R.id.img_multiple));
                holder.setText(R.id.text_multiple, text);
                break;
            case ItemType.BANNER:
                if (!isInitBanner) {
                    bannerImages = item.getField(MutipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recyler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    isInitBanner = true;
                }
                break;
            default:
                break;
        }

    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
        return getData().get(i).getField(MutipleFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int position) {

    }
}

