package com.hrdl.partyaffairs.utils;

import android.content.Context;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

/**
 * 作者：王健 on 2018/10/16
 * 邮箱：845040970@qq.com
 * dialog工具类
 */
public class DialogUtils {
    private OnLeftButtonListener onLeftButtonListener;
    private OnRightButtonListener onRightButtonListener;

    /**
     * @param mContext 上下文
     * @param title 标题
     * @param messge 内容
     * @param leftText 左边按钮
     * @param rightText 右边按钮
     */
    public void setQmuiDialog(Context mContext,String title,String messge,String leftText,String rightText){
        new QMUIDialog.MessageDialogBuilder(mContext)
                .setTitle(title)
                .setMessage(messge)
                .addAction(leftText, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        if(onLeftButtonListener!=null){
                            onLeftButtonListener.onClick(dialog,index);
                        }
                    }
                })
                .addAction(rightText,new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        if(onRightButtonListener!=null){
                            onRightButtonListener.onClick(dialog,index);
                        }
                    }
                })
                .show();
    }
    /**
     * @param mContext 上下文
     * @param title 标题
     * @param messge 内容
     * @param leftText 左边按钮
     * @param rightText 右边按钮
     * @param actionListener 按钮监听事件
     */
    public static void setQmuiDialog(Context mContext,String title,String messge,String leftText,String rightText,QMUIDialogAction.ActionListener actionListener){
        new QMUIDialog.MessageDialogBuilder(mContext)
                .setTitle(title)
                .setMessage(messge)
                .addAction(leftText, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction(0,rightText,QMUIDialogAction.ACTION_PROP_NEGATIVE, actionListener)
                .show();
    }
    public interface OnLeftButtonListener {
        /**
         * 接口回调
         * @param dialog
         * @param index
         * */
//        void onClick(View view);

        void onClick(QMUIDialog dialog, int index);
    }

    public interface OnRightButtonListener {
        /**
         * 接口回调
         * @param
         * */
//        void onClick(View view);

        void onClick(QMUIDialog dialog, int index);
    }
    /**
     * 设置左边按钮点击事件的方法
     */
    public void setOnLeftButtonListener(OnLeftButtonListener itemClickListener) {
        this.onLeftButtonListener = itemClickListener;
    }

    /**
     * 设置右边按钮点击事件的方法
     */
    public void setOnRightButtonListener(OnRightButtonListener itemClickListener) {
        this.onRightButtonListener = itemClickListener;
    }
}
