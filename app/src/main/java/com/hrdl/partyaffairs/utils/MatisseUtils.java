package com.hrdl.partyaffairs.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import com.hrdl.partyaffairs.BuildConfig;
import com.hrdl.partyaffairs.R;

/**
 * 作者：王健 on 2018/10/23
 * 邮箱：845040970@qq.com
 */
public class MatisseUtils {
    public static void showMatisse(Activity context, int count){
        Matisse.from(context)
                //相册显示类型
                .choose(MimeType.ofImage(), false)
                //只显示一种类型
                .showSingleMediaType(true)
                // 开启相机，和 captureStrategy 一并使用否则报错
                .capture(true)
                // 拍照的图片路径
                .captureStrategy(new CaptureStrategy(true, BuildConfig.APPLICATION_ID + ".fileprovider"))
                //设置主题为黑色
                .theme(R.style.Matisse_Dracula)
                //显示选择数量
                .countable(true)
                //图片选择的最多数量
                .maxSelectable(count)
                .gridExpectedSize(context.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                // 缩略图的比例
                .thumbnailScale(0.85f)
                //使用Glide图片加载引擎
                .imageEngine(new GlideEngine())
                .forResult(101);
    }
    public static void showMatisse(Fragment context, int count){
        Matisse.from(context)
                //相册显示类型
                .choose(MimeType.ofImage(), false)
                //只显示一种类型
                .showSingleMediaType(true)
                // 开启相机，和 captureStrategy 一并使用否则报错
                .capture(true)
                // 拍照的图片路径
                .captureStrategy(new CaptureStrategy(true, BuildConfig.APPLICATION_ID + ".fileprovider"))
                //设置主题为黑色
                .theme(R.style.Matisse_Dracula)
                //显示选择数量
                .countable(true)
                //图片选择的最多数量
                .maxSelectable(1)
                .gridExpectedSize(context.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                // 缩略图的比例
                .thumbnailScale(0.85f)
                //使用Glide图片加载引擎
                .imageEngine(new GlideEngine())
                .forResult(101);
    }
}
