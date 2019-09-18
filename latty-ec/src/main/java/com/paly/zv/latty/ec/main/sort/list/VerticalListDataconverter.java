package com.paly.zv.latty.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.paly.zv.latty.ui.recyler.DataConverter;
import com.paly.zv.latty.ui.recyler.ItemType;
import com.paly.zv.latty.ui.recyler.MultipleItemEntity;
import com.paly.zv.latty.ui.recyler.MutipleFields;


import java.util.ArrayList;

public final class VerticalListDataconverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getmJsonData()).getJSONObject("data").getJSONArray("list");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            MultipleItemEntity itemEntity =MultipleItemEntity.builder()
                    .setField(MutipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MutipleFields.ID,id)
                    .setField(MutipleFields.TEXT,name)
                    .setField(MutipleFields.TAG,false)
                    .build();
            dataList.add(itemEntity);
            //

        }
        dataList.get(0).setFiled(MutipleFields.TAG,true);

        return dataList;
    }
}
