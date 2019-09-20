package com.paly.zv.latty.ec.main.sort.list;

import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.paly.zv.latty.delegate.LatteDelegate;
import com.paly.zv.latty.ec.R;
import com.paly.zv.latty.ec.main.sort.SortDelegate;
import com.paly.zv.latty.ec.main.sort.content.ContentDelegate;
import com.paly.zv.latty.ui.recyler.ItemType;
import com.paly.zv.latty.ui.recyler.MultipleItemEntity;
import com.paly.zv.latty.ui.recyler.MultipleRecyclerAdapter;
import com.paly.zv.latty.ui.recyler.MultipleViewHolder;
import com.paly.zv.latty.ui.recyler.MutipleFields;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;


public class SortRecyclerAdapter extends MultipleRecyclerAdapter {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    private final SortDelegate DELEGATE;

    private int mPrePosition =0;

    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate sortDelegate) {
        super(data);
        DELEGATE = sortDelegate;

        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);

    }

    @Override
    protected void convert(@NonNull final MultipleViewHolder holder, final MultipleItemEntity item) {
        super.convert(holder, item);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String name = item.getField(MutipleFields.TEXT);
                final boolean isClicked = item.getField(MutipleFields.TAG);
                final AppCompatTextView textView = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemview = holder.itemView;

                textView.setText(name);
                itemview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentpositon = holder.getAdapterPosition();
                        if(mPrePosition != currentpositon){
                            getData().get(mPrePosition).setFiled(MutipleFields.TAG,false);
                            notifyItemChanged(mPrePosition);

                            item.setFiled(MutipleFields.TAG,true);
                            notifyItemChanged(currentpositon);
                            mPrePosition = currentpositon;


                            final int contenId = getData().get(currentpositon).getField(MutipleFields.ID);
                            showContent(contenId);

                        }



                    }
                });

                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    textView.setTextColor(Color.BLACK);
                    itemview.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));

                } else {
                    line.setVisibility(View.VISIBLE);
                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemview.setBackgroundColor(Color.WHITE);
                }

                break;
            default:
                break;
        }
    }
    private void showContent(int contentID){
        final ContentDelegate contentDelegate = ContentDelegate.newInstance(contentID);
        switchContent(contentDelegate);

    }
    private void switchContent(ContentDelegate delegate){
        final LatteDelegate content = SupportHelper.findFragment(DELEGATE.getChildFragmentManager(), ContentDelegate.class);
        if(content!=null){
            content.replaceFragment(delegate,false);
        }
    }
}
