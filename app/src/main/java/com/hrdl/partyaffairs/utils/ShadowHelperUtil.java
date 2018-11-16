package com.hrdl.partyaffairs.utils;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.View;

import cn.davidsu.library.ShadowConfig;
import cn.davidsu.library.ShadowHelper;
import com.hrdl.partyaffairs.utils.ZWShadowHelperUtil.ZWShadowConfig;
import com.hrdl.partyaffairs.utils.ZWShadowHelperUtil.ZWShadowHelper;


/**
 * 作者：王健 on 2018/8/28
 * 邮箱：845040970@qq.com
 */
public class ShadowHelperUtil {

    /**
     * @param mContext 上下文
     * @param view      要改变得view
     * @param viewcolor view的背景色
     * @param shadowcolor 阴影颜色
     * @param radius       圆角
     * @param OffsetX      横向偏移
     * @param OffsetY      纵向偏移
     * @param colorArray   如果View是渐变色，则设置color数组
     */
    public static void setshadow(Context mContext, View view, @ColorInt int viewcolor, @ColorInt int shadowcolor, int radius, int OffsetX, int OffsetY, @Nullable int[] colorArray){

        ZWShadowConfig.Builder config = new ZWShadowConfig.Builder()
                .setColor(viewcolor)//View颜色
                .setShadowColor(shadowcolor)//阴影颜色
                .setGradientColorArray(colorArray)//如果View是渐变色，则设置color数组
                .setRadius(Util.dp2px(mContext,radius))//圆角
                .setOffsetX(OffsetX)//横向偏移
                .setOffsetY(OffsetY);//纵向偏移

        ZWShadowHelper.setShadowBgForView(view, config);
    }

    /**
     * @param mContext 上下文
     * @param view      要改变得view
     * @param viewcolor view的背景色
     * @param shadowcolor 阴影颜色
     * @param radius       圆角
     * @param OffsetX      横向偏移
     * @param OffsetY      纵向偏移
     * @param colorArray   如果View是渐变色，则设置color数组
     */
    public static void wjSetshadow(Context mContext, View view, @ColorInt int viewcolor, @ColorInt int shadowcolor, int radius, int OffsetX, int OffsetY, @Nullable int[] colorArray){

        ShadowConfig.Builder config = new ShadowConfig.Builder()
                .setColor(viewcolor)//View颜色
                .setShadowColor(shadowcolor)//阴影颜色
                .setGradientColorArray(colorArray)//如果View是渐变色，则设置color数组
                .setRadius(Util.dp2px(mContext,radius))//圆角
                .setOffsetX(OffsetX)//横向偏移
                .setOffsetY(OffsetY);//纵向偏移

        ShadowHelper.setShadowBgForView(view, config);
    }
}
