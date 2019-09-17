package com.paly.zv.latty.ui.banner;

import android.widget.AdapterView;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.paly.zv.latty.R;

import java.util.ArrayList;

public class BannerCreator {
    public static void setDefault(ConvenientBanner<String> convenientBanner,
                                  ArrayList<String> url,
                                  OnItemClickListener clickListener) {
        convenientBanner.setPages(new HolderCreator(),url)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);



    }
}
