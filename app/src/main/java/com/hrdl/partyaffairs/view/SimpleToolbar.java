package com.hrdl.partyaffairs.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hrdl.partyaffairs.R;


/**
 * create to by : CatLoveEatFish .
 * 2017/8/31 Mr:Chen
 */

public class SimpleToolbar extends Toolbar {
    /**
     * 左侧Title
     */
    //private TextView mTxtLeftTitle;
    /**
     * 左侧image
     */
    private ImageButton mTxtLeftImage;
    /**
     * 左侧Layout
     */
    //private LinearLayout mTxtLeftLayout;
    /**
     * 中间Title
     */
    private TextView mTxtMiddleTitle;
    /**
     * 右侧Title
     */
    private TextView mTxtRightTitle;

    public SimpleToolbar(Context context) {
        this(context,null);
    }

    public SimpleToolbar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //mTxtLeftLayout = findViewById(R.id.left_layout_id);
        mTxtLeftImage = findViewById(R.id.left_image_id);
        //mTxtLeftTitle = findViewById(R.id.txt_left_title);
        mTxtMiddleTitle = findViewById(R.id.txt_main_title);
        mTxtRightTitle = findViewById(R.id.txt_right_title);
    }

    //设置中间title的内容
    public void setMainTitle(String text) {
        this.setTitle(" ");
        mTxtMiddleTitle.setVisibility(View.VISIBLE);
        mTxtMiddleTitle.setText(text);
    }

    //设置中间title的内容文字的颜色
    public void setMainTitleColor(int color) {
        mTxtMiddleTitle.setTextColor(color);
    }

    //设置title左边文字
    public void setLeftTitleText(String text) {
        /*mTxtLeftTitle.setVisibility(View.VISIBLE);
        mTxtLeftTitle.setText(text);*/
    }

    //设置title左边文字颜色
    public void setLeftTitleColor(int color) {
        //mTxtLeftTitle.setTextColor(color);
    }

    //设置title左边图标
    public void setLeftTitleDrawable(int res) {
        Drawable dwLeft = ContextCompat.getDrawable(getContext(), res);
        mTxtLeftImage.setImageDrawable(dwLeft);
        /*dwLeft.setBounds(0, 0, 60, 60);
        mTxtLeftTitle.setCompoundDrawables(dwLeft, null, null, null);*/
    }
    //设置title左边点击事件
    public void setLeftTitleClickListener(OnClickListener onClickListener){
        mTxtLeftImage.setOnClickListener(onClickListener);
    }

    //设置title右边文字
    public void setRightTitleText(String text) {
        mTxtRightTitle.setVisibility(View.VISIBLE);
        mTxtRightTitle.setText(text);
    }

    //设置title右边文字颜色
    public void setRightTitleColor(int color) {
        mTxtRightTitle.setTextColor(color);
    }

    //设置title右边图标
    public void setRightTitleDrawable(int res) {
        Drawable dwRight = ContextCompat.getDrawable(getContext(), res);
        dwRight.setBounds(0, 0, 60, 60);
        mTxtRightTitle.setCompoundDrawables(null, null, dwRight, null);
    }

    //设置title右边点击事件
    public void setRightTitleClickListener(OnClickListener onClickListener){
        mTxtRightTitle.setOnClickListener(onClickListener);
    }
}
