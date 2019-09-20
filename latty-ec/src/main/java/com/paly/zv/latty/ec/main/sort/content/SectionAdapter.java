package com.paly.zv.latty.ec.main.sort.content;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.paly.zv.latty.ec.R;

import java.util.List;

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder>
implements BaseQuickAdapter.SpanSizeLookup{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header,item.header);
        helper.setVisible(R.id.more,item.isMore());
        helper.addOnClickListener(R.id.more);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SectionBean item) {
        final String thumb = item.t.getGoodImgUrl();
        final String name =item.t.getGoogNme();
        final int id = item.t.getGoodID();
        final SectionContenItemEntity entity = item.t;
        helper.setText(R.id.tv_good_name,name);
        final AppCompatImageView imageView = helper.getView(R.id.iv_good_img);
        Glide.with(mContext)
                .load(thumb).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                dontAnimate().
                into(imageView);

    }


    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
        return 1;
    }
}
