package com.paly.zv.latty.ec.main;

import android.graphics.Color;

import com.paly.zv.latty.bottom.BaseBottomDelegate;
import com.paly.zv.latty.bottom.BottomItemDelegate;
import com.paly.zv.latty.bottom.BottomTabBean;
import com.paly.zv.latty.bottom.ItemBuilder;
import com.paly.zv.latty.ec.main.discover.DiscoverDelegate;
import com.paly.zv.latty.ec.main.index.IndexDelegate;
import com.paly.zv.latty.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

public class ECBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean,BottomItemDelegate> itemDelegateLinkedHashMap = new LinkedHashMap<>();
        itemDelegateLinkedHashMap.put(new BottomTabBean("{fa-home}","首页"),new IndexDelegate());
        itemDelegateLinkedHashMap.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        itemDelegateLinkedHashMap.put(new BottomTabBean("{fa-compass}","发现"),new DiscoverDelegate());
        itemDelegateLinkedHashMap.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new IndexDelegate());
        itemDelegateLinkedHashMap.put(new BottomTabBean("{fa-user}","我的"),new IndexDelegate());

        return builder.addItems(itemDelegateLinkedHashMap).build();
        //return itemDelegateLinkedHashMap;
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setCleckedColor() {
        return Color.BLACK;
    }
}
