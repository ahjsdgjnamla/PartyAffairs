package com.hrdl.partyaffairs.utils.ZWShadowHelperUtil;

import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * Created by zhangwei on 2018/9/14.
 */
public class ZWShadowHelper {
    public ZWShadowHelper() {
    }

    public static void setShadowBgForView(View view, ZWShadowConfig.Builder config) {
        view.setLayerType(1, (Paint)null);
        ViewCompat.setBackground(view, config.builder());
    }
}
