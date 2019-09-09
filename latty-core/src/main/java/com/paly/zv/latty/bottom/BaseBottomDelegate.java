package com.paly.zv.latty.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.joanzapata.iconify.widget.IconTextView;
import com.paly.zv.latty.R;
import com.paly.zv.latty.R2;
import com.paly.zv.latty.delegate.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();

    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();

    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.BLACK;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;


    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setCleckedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setCleckedColor() != 0) {
            mClickedColor = setCleckedColor();
        }
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean, BottomItemDelegate> itemDelegateEntry : ITEMS.entrySet()) {
            final BottomTabBean bottomTabBean = itemDelegateEntry.getKey();
            final BottomItemDelegate bottomItemDelegate = itemDelegateEntry.getValue();
            TAB_BEANS.add(bottomTabBean);
            ITEM_DELEGATES.add(bottomItemDelegate);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i=0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout,mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);

            //设置item点击事件
            item.setTag(i);

            item.setOnClickListener(this);

            final IconTextView iconTextView = (IconTextView) item.getChildAt(0);

            final AppCompatTextView  itemTitle = (AppCompatTextView) item.getChildAt(1);

            final BottomTabBean bean =TAB_BEANS.get(i);

            iconTextView.setText(bean.getICON());
            itemTitle.setText(bean.getTITLE());

            if (i == mIndexDelegate){
                iconTextView.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }

        }
        final SupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_delegate_contanier,mIndexDelegate,delegateArray);
    }
    private void resetColor(){
        final int count = mBottomBar.getChildCount();
        for (int i = 0 ;i<count;i++){
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);

            final IconTextView iconTextView = (IconTextView) item.getChildAt(0);

            final AppCompatTextView  itemTitle = (AppCompatTextView) item.getChildAt(1);
            iconTextView.setTextColor(Color.GRAY);
            itemTitle.setTextColor(Color.GRAY);

        }
    }

    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView iconTextView = (IconTextView) item.getChildAt(0);

        final AppCompatTextView  itemTitle = (AppCompatTextView) item.getChildAt(1);
        iconTextView.setTextColor(mClickedColor);
        itemTitle.setTextColor(mClickedColor);

        showHideFragment(ITEM_DELEGATES.get(tag),ITEM_DELEGATES.get(mCurrentDelegate));

        mCurrentDelegate=tag;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }
}
