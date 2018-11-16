package com.hrdl.partyaffairs.utils;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import com.hrdl.partyaffairs.R;

/**
 * 作者：王健 on 2018/9/29
 * 邮箱：845040970@qq.com
 */
public class CustomToolbarUtil {
    /**
     * @param mQMUITopBarLayout topbar
     * @param context 上下文
     * @param title 标题
     */
    public static void setTopToolbar(QMUITopBarLayout mQMUITopBarLayout,Context context,String title){
        initToobar(mQMUITopBarLayout,context);
        setTitleCenterView(mQMUITopBarLayout,context,title);
    }
    /**
     * @param mQMUITopBarLayout topbar
     * @param context 上下文
     * @param title 标题
     * @param bgColor 背景颜色
     */
    public static void setTopToolbar(QMUITopBarLayout mQMUITopBarLayout,Context context,String title,@ColorInt int bgColor){

        initToobar(mQMUITopBarLayout,context);
        setBackgroundColor(mQMUITopBarLayout, bgColor);
        setTitleCenterView(mQMUITopBarLayout,context,title);
    }

    /**
     * @param mQMUITopBarLayout topbar
     * @param context 上下文
     * @param title 标题
     * @param bgColor 背景颜色
     * @param leftImage 左边按扭图标
     * @param onLeftImageButtonListener 左边按钮监听事件
     */
    public static void setTopToolbar(QMUITopBarLayout mQMUITopBarLayout,Context context,String title,@ColorInt int bgColor,int leftImage,View.OnClickListener onLeftImageButtonListener){

        initToobar(mQMUITopBarLayout,context);
        setBackgroundColor(mQMUITopBarLayout,bgColor);
        setTitleCenterView(mQMUITopBarLayout,context,title);
        setLeftImageButton(mQMUITopBarLayout,leftImage,onLeftImageButtonListener);
    }

    /**
     * @param mQMUITopBarLayout topbar
     * @param context 上下文
     * @param title 标题
     * @param bgColor 背景颜色
     * @param leftImage 左边图标按钮
     * @param rightImage 右边图标按钮
     * @param onLeftImageButtonListener 左边图标按钮监听事件
     * @param onRightImageButtonListener 右边图标按钮监听事件
     */
    public static void setTopToolbar(QMUITopBarLayout mQMUITopBarLayout, Context context, String title, @ColorInt int bgColor, int leftImage, int rightImage, View.OnClickListener onLeftImageButtonListener, View.OnClickListener onRightImageButtonListener){
        initToobar(mQMUITopBarLayout,context);
        setBackgroundColor(mQMUITopBarLayout,bgColor);
        setTitleCenterView(mQMUITopBarLayout,context,title);
        setLeftImageButton(mQMUITopBarLayout,leftImage,onLeftImageButtonListener);
        setRightImageButton(mQMUITopBarLayout,rightImage,onRightImageButtonListener);
    }

    /**
     * @param mQMUITopBarLayout topbar
     * @param leftImage 左边图标按钮
     * @param onLeftImageButtonListener 左边图标按钮监听事件
     */
    private static void setLeftImageButton(QMUITopBarLayout mQMUITopBarLayout, int leftImage, View.OnClickListener onLeftImageButtonListener) {

        mQMUITopBarLayout.addLeftImageButton(leftImage,0).setOnClickListener( onLeftImageButtonListener);
    }

    /**
     * @param mQMUITopBarLayout top
     * @param rightImage 右边图标按钮
     * @param onRightImageButtonListener 右边图标按钮监听事件
     */
    private static void setRightImageButton(QMUITopBarLayout mQMUITopBarLayout, int rightImage, View.OnClickListener onRightImageButtonListener) {
        mQMUITopBarLayout.addRightImageButton(rightImage,0).setOnClickListener((View.OnClickListener) onRightImageButtonListener);
    }

    /**
     * @param mQMUITopBarLayout topbar
     * @param context 上下文
     * 设置topbar基础属性
     */
    private static void initToobar(QMUITopBarLayout mQMUITopBarLayout, Context context) {

        // 控制标题的显示位置，使用了setTitleCenterView后此方法无效
        mQMUITopBarLayout.setTitleGravity(Gravity.CENTER);
        // 设置背景颜色
        mQMUITopBarLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
    }

    /**
     * @param mQMUITopBarLayout topbar
     * @param context 上下文
     * @param title 标题
     *  设置标题的样式
     */
    private static void setTitleCenterView(QMUITopBarLayout mQMUITopBarLayout, Context context, String title) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_toobar_center_titletext, null);
        //为中间标题设置自己的view
        mQMUITopBarLayout.setCenterView(inflate);
        TextView tvTitle = inflate.findViewById(R.id.id_tvTitle);
        tvTitle.setText(title);
        tvTitle.setTextSize(18);
        tvTitle.setTextColor(ContextCompat.getColor(context,R.color.qmui_config_color_black));
    }
    /**
     * 设置背景颜色
     */
    public static void setBackgroundColor(QMUITopBarLayout mQMUITopBarLayout, @ColorInt int color) {
        mQMUITopBarLayout.setBackgroundColor(color);
    }
}
