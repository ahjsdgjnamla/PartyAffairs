package com.hrdl.partyaffairs.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.base.BaseActivity;
import com.hrdl.partyaffairs.view.SimpleToolbar;

public class WorkDataActivity extends BaseActivity {
    @BindView(R.id.left_image_id)
    ImageButton leftImageId;
    @BindView(R.id.txt_main_title)
    TextView txtMainTitle;
    @BindView(R.id.txt_right_title)
    TextView txtRightTitle;
    @BindView(R.id.simple_toolbar)
    SimpleToolbar simpleToolbar;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_workdata;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        ImmersionBar.setTitleBar(this,simpleToolbar);
        setToolBar("工作任务", R.mipmap.ic_jiantou_white_left);
    }

    /**
     * 设置Title，方法内的参数可自己定义，如左边文字，颜色，图片
     *
     * @param title：中间标题
     * @param LeftImages：左边图标
     */
    private void setToolBar(String title, int LeftImages) {
        simpleToolbar.setMainTitle(title);
        simpleToolbar.setLeftTitleDrawable(LeftImages);
        //返回键
        simpleToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
