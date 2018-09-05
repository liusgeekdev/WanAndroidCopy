package com.lius.wanandroidcopy.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lius.wanandroidcopy.R;

/**
 * 图片加载管理
 */
public class ImageLoaderManager {
    public static void LoadImage(Context context, String imgUrl, ImageView imageView) {
        Glide.with(context)
                .load(imgUrl)
                .placeholder(R.mipmap.default_img)
                .dontAnimate() //解决圆形图显示占位图问题
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
