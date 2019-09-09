package com.paly.zv.latty.bottom;

import java.util.LinkedHashMap;

public final class ItemBuilder {
    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    static ItemBuilder builder(){
        return new ItemBuilder();
    }
    public final ItemBuilder addItem(BottomTabBean bean,BottomItemDelegate delegate){
        ITEMS.put(bean,delegate);
        return this;
    }
    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemDelegate> itemDelegateLinkedHashMap){
        ITEMS.putAll(itemDelegateLinkedHashMap);
        return this;

    }
    public final LinkedHashMap<BottomTabBean,BottomItemDelegate> build(){
        return ITEMS;
    }
}
