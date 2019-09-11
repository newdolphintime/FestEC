package com.paly.zv.latty.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.paly.zv.latty.ui.recyler.DataConverter;
import com.paly.zv.latty.ui.recyler.ItemType;
import com.paly.zv.latty.ui.recyler.MultipleItemEntity;
import com.paly.zv.latty.ui.recyler.MultipleItemEntityBuilder;
import com.paly.zv.latty.ui.recyler.MutipleFields;

import java.util.ArrayList;

public class IndexDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getmJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);


            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize =data.getInteger("spanSize");
            final int id  = data.getInteger("goodsId");
            final JSONArray bannners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();

            int type = 0;

            if (imageUrl == null && text != null){
                type = ItemType.TEXT;
            }else if (imageUrl != null && text == null){
                type = ItemType.IMAGE;
            }else if(imageUrl!= null && text != null){
                type = ItemType.TEXT_IMAGE;
            }else if(bannners != null){
                type = ItemType.BANNER;

                final int bannerSize = bannners.size();
                //banner初始化
                for (int j = 0 ; j <bannerSize; j++){
                    final String banner = bannners.getString(j);

                    bannerImages.add(banner);
                }
            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MutipleFields.ITEM_TYPE,type)
                    .setField(MutipleFields.SPAN_SIZE,spanSize)
                    .setField(MutipleFields.ID,id)
                    .setField(MutipleFields.IMAGE_URL,imageUrl)
                    .setField(MutipleFields.TEXT,text)
                    .setField(MutipleFields.BANNERS,bannerImages)
                    .build();

            ENTITIES.add(entity);


        }

        return ENTITIES;
    }
}
