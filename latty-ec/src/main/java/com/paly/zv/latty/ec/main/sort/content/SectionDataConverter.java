package com.paly.zv.latty.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.paly.zv.latty.ui.recyler.DataConverter;
import com.paly.zv.latty.ui.recyler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

public class SectionDataConverter {

    final List<SectionBean> converter(String json) {

        final List<SectionBean> dataList = new ArrayList<>();

        final JSONArray jsonArray = JSON.parseObject(json).getJSONArray("data");

        final int size = jsonArray.size();

        for (int i = 0; i < size; i++) {
            final JSONObject data = jsonArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String section = data.getString("section");

            //添加title
            final SectionBean sectionBean = new SectionBean(true,section);
            sectionBean.setId(id);
            sectionBean.setMore(true);
            dataList.add(sectionBean);

            final JSONArray goods =data.getJSONArray("goods");
            final int goodSize = goods.size();
            for (int j = 0 ; j<goodSize;j++){

                final JSONObject contenItem = goods.getJSONObject(j);
                final int goodsID= contenItem.getInteger("goods_id");
                final String goodsName = contenItem.getString("goods_name");
                final String goodsThumb = contenItem.getString("goods_thumb");
                final SectionContenItemEntity itemEntity = new SectionContenItemEntity();
                itemEntity.setGoodID(goodsID);
                itemEntity.setGoodImgUrl(goodsThumb);
                itemEntity.setGoogNme(goodsName);

                dataList.add(new SectionBean(itemEntity));
            }

        }

        return dataList;
    }

}
