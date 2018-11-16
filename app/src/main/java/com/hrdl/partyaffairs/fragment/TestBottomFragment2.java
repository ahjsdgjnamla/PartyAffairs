package com.hrdl.partyaffairs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.activity.WorkActivity;
import com.hrdl.partyaffairs.base.BaseLazyFragment;

/**
 * 作者：王健 on 2018/9/30
 * 邮箱：845040970@qq.com
 */
public class TestBottomFragment2 extends BaseLazyFragment implements View.OnClickListener {
    @BindView(R.id.button_back2)
    LinearLayout buttonBack2;
    @BindView(R.id.button_back3)
    LinearLayout buttonBack3;
    @BindView(R.id.button_back4)
    LinearLayout buttonBack4;
    @BindView(R.id.button_back5)
    LinearLayout buttonBack5;
    @BindView(R.id.button_back7)
    LinearLayout buttonBack7;
    @BindView(R.id.button_back6)
    LinearLayout buttonBack6;
    Unbinder unbinder;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_testbottom2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        isLazyLoad();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {
        super.initData();
        buttonBack2.setOnClickListener(this);
        buttonBack4.setOnClickListener(this);
        buttonBack5.setOnClickListener(this);
        buttonBack6.setOnClickListener(this);
        buttonBack3.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_back2:
                showToast("暂未开放");
                break;
            case R.id.button_back4:
                showToast("暂未开放");
                break;
            case R.id.button_back5:
                showToast("暂未开放");
                break;
            case R.id.button_back6:
                showToast("暂未开放");
                break;
            case R.id.button_back3:
                startActivity(new Intent(getActivity(), WorkActivity.class));
                break;
        }
    }
}
