package com.paly.zv.latty.ui.recyler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

public class MultipleItemEntity implements MultiItemEntity {
    MultipleItemEntity(LinkedHashMap<Object,Object> items) {
        FILEDS_REFERENCE.get().putAll(items);
    }

    public static MultipleItemEntityBuilder builder(){
        return new MultipleItemEntityBuilder();
    }

    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object,Object> MUTIPLE_FILED = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object,Object>> FILEDS_REFERENCE = new SoftReference<>(MUTIPLE_FILED,ITEM_QUEUE);

    @Override
    public int getItemType() {
        return (int) FILEDS_REFERENCE.get().get(MutipleFields.ITEM_TYPE);
    }
    public final <T> T getField(Object key){
        return (T)FILEDS_REFERENCE.get().get(key);
    }
    public final LinkedHashMap<?,?> getFields(){
        return FILEDS_REFERENCE.get();
    }
    public final MultiItemEntity setFiled(Object key ,Object value){
        FILEDS_REFERENCE.get().put(key, value);
        return this;
    }
}
