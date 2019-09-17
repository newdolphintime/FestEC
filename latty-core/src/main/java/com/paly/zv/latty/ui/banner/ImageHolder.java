package com.paly.zv.latty.ui.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.paly.zv.latty.R;

public class ImageHolder implements Holder<String> {
    private AppCompatImageView imageView = null;

    @Override
    public View createView(Context context) {
        imageView = new AppCompatImageView(context);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context)
                .load(data).
                diskCacheStrategy(DiskCacheStrategy.ALL).
                dontAnimate().
                centerCrop().
                into(imageView);

    }
}
