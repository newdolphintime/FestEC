package com.paly.zv.latty.ui.recyler;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

public class MultipleItemEntityBuilder {
    private static final LinkedHashMap<Object,Object> FILEDS = new LinkedHashMap<>();


    public MultipleItemEntityBuilder() {
        FILEDS.clear();
    }

    public final MultipleItemEntityBuilder setItemType(int itemType){
        FILEDS.put(MutipleFields.ITEM_TYPE,itemType);
        return this;
    }
    public final MultipleItemEntityBuilder setField(Object key,Object value){
        FILEDS.put(key,value);
        return this;
    }
    public final MultipleItemEntityBuilder setFields(LinkedHashMap<Object,Object> items){
        FILEDS.putAll(items);
        return this;
    }

    public final MultipleItemEntity build(){
        return new MultipleItemEntity(FILEDS);
    }

}
