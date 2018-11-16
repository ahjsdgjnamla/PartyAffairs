package com.hrdl.partyaffairs.utils;

import android.content.Context;

/**
 * UI dp 转化工具
 */
public class UIUtil {

    public static int dip2px(Context context, double dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * (double)density + 0.5D);
    }

}
