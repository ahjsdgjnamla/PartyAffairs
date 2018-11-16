package com.hrdl.partyaffairs.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import com.hrdl.partyaffairs.R;

/**
 * 作者：王健 on 2018/10/8
 * 邮箱：845040970@qq.com
 */
public class GlideUtils {
    /**
     *加载图片(默认)
     * @param context   上下文
     * @param url 图片地址
     * @param imageView 需要加载的imageview
     */
    public static void loadImage(Context context, Object url, ImageView imageView) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                //占位图
                .placeholder(R.color.white)
                //错误图
                .error(R.color.white)
                // .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(options).into(imageView);
    }
    /**
     *加载圆形图片
     */
    public static void loadCircleImage(Context context, Object url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                //设置圆形
                .circleCrop()
                .placeholder(R.color.white)
                .error(R.color.white)
                //.priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(options).into(imageView);
    }
    /**
     *加载圆角图片
     * @param context  上下文
     * @param url 图片加载地址
     * @param imageView 需要加载的imageview
     * @param roundingRadius 圆角大小
     */
    public static void loadCustRoundCircleImage(Context context, Object url, ImageView imageView, int roundingRadius) {
        RequestOptions options=new RequestOptions()
                .centerCrop()
                .circleCrop()
                .placeholder(R.color.white)
                .error(R.color.white)
                .optionalTransform(new RoundedCorners(roundingRadius))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
