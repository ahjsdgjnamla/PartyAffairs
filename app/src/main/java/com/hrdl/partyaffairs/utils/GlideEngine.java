package com.hrdl.partyaffairs.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.zhihu.matisse.engine.ImageEngine;

/**
 * 作者：王健 on 2018/10/23
 * 邮箱：845040970@qq.com
 */
public class GlideEngine implements ImageEngine {

    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholder)
                .override(resize,resize)
                //.priority(Priority.HIGH)
                ;
        Glide.with(context).load(uri).apply(options).into(imageView);
//        Glide.with(context)
//                .load(uri)
//                .asBitmap()  // some .jpeg files are actually gif
//                .placeholder(placeholder)
//                .override(resize, resize)
//                .centerCrop()
//                .into(imageView);
    }

    @Override
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView,
                                 Uri uri) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholder)
                .override(resize,resize)
                //.priority(Priority.HIGH)
                ;
        Glide.with(context).load(uri).apply(options).into(imageView);
//        Glide.with(context)
//                .load(uri)
//                .asBitmap()
//                .placeholder(placeholder)
//                .override(resize, resize)
//                .centerCrop()
//                .into(imageView);
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .fitCenter()
                .override(resizeX, resizeY)
                .priority(Priority.HIGH)
                ;
        Glide.with(context).load(uri).apply(options).into(imageView);
//        Glide.with(context)
//                .load(uri)
//                .override(resizeX, resizeY)
//                .priority(Priority.HIGH)
//                .fitCenter()
//                .into(imageView);
    }

    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .override(resizeX,resizeY)
                .priority(Priority.HIGH)
                ;
        Glide.with(context).load(uri).apply(options).into(imageView);
//        Glide.with(context)
//                .load(uri)
//                .asGif()
//                .override(resizeX, resizeY)
//                .priority(Priority.HIGH)
//                .into(imageView);
    }

    @Override
    public boolean supportAnimatedGif() {
        return true;
    }

}