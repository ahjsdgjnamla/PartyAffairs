package com.hrdl.partyaffairs.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.base.BaseLazyFragment;
import com.hrdl.partyaffairs.callback.CallBackUtils;
import com.hrdl.partyaffairs.callback.ValueInterface;
import com.hrdl.partyaffairs.fragment.usersMessageFragment.ActivityFragment;
import com.hrdl.partyaffairs.fragment.usersMessageFragment.SystemFragment;
import com.hrdl.partyaffairs.view.CustomViewPager;
import com.hrdl.partyaffairs.view.SimpleToolbar;

/**
 * 作者：王健 on 2018/9/30
 * 邮箱：845040970@qq.com
 */
public class TestBottomFragment3 extends BaseLazyFragment implements ValueInterface {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    CustomViewPager viewpager;
    Unbinder unbinder;
    @BindView(R.id.left_image_id)
    ImageButton leftImageId;
    @BindView(R.id.txt_main_title)
    TextView txtMainTitle;
    @BindView(R.id.txt_right_title)
    TextView txtRightTitle;
    @BindView(R.id.simple_toolbar)
    SimpleToolbar simpleToolbar;

    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;
    private SystemFragment systemFragment;
    private ActivityFragment activityFragment;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_testbottom3;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        isLazyLoad();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * 设置Title，方法内的参数可自己定义，如左边文字，颜色，图片
     *
     * @param title：中间标题
     */
    private void setToolBar(String title) {
        simpleToolbar.setMainTitle(title);
        /*simpleToolbar.setLeftTitleDrawable(LeftImages);
        //返回键
        simpleToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
        simpleToolbar.setRightTitleText("全选");
        //全选操作
        simpleToolbar.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtRightTitle.getText().equals("全选") || txtRightTitle.getText().equals("取消")) {
                    if (tabs.getSelectedTabPosition() == 0) {
                        simpleToolbar.setRightTitleText(systemFragment.updataEditMode());
                    } else {
                        simpleToolbar.setRightTitleText(activityFragment.updataEditMode());
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        CallBackUtils.setCallBack(this);

        setToolBar("通知");

        //初始化Tab，和viewpager绑定
        tabs.setTabMode(TabLayout.MODE_FIXED);
        //tabs.setSelectedTabIndicatorHeight(5);
        tabs.setTabTextColors(Color.WHITE, Color.parseColor("#F3F5F4"));
        ViewCompat.setElevation(tabs, 10);
        tabs.setupWithViewPager(viewpager);

        //添加Tab标签
        tabIndicators = new ArrayList<>();
        tabIndicators.add("系统消息");
        tabIndicators.add("任务消息");

        //viewpager添加fragment
        tabFragments = new ArrayList<>();
        systemFragment = new SystemFragment();
        activityFragment = new ActivityFragment();
        tabFragments.add(systemFragment);
        tabFragments.add(activityFragment);

        //viewpager设置适配器
        contentAdapter = new ContentPagerAdapter(getActivity().getSupportFragmentManager());
        viewpager.setAdapter(contentAdapter);
        viewpager.setScroll(true);


        //初始设置Tab0位为加粗选中状态
        TextView title = (TextView) (((LinearLayout) ((LinearLayout) tabs.getChildAt(0)).getChildAt(0)).getChildAt(1));
        title.setTextAppearance(getActivity(), R.style.TabLayoutStyle_Select);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView title = (TextView) (((LinearLayout) ((LinearLayout) tabs.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
                title.setTextAppearance(getActivity(), R.style.TabLayoutStyle_Select);
                if (tab.getText().toString().equals("系统消息")) {
                    if (systemFragment.mData.size() == 0) {
                        simpleToolbar.setRightTitleText("");
                    } else {
                        if (systemFragment.fragment1 == 0) {
                            simpleToolbar.setRightTitleText("全选");
                        } else {
                            simpleToolbar.setRightTitleText("取消");
                        }
                    }
                } else {
                    if (activityFragment.mData.size() == 0) {
                        simpleToolbar.setRightTitleText("");
                    } else {
                        if (activityFragment.fragment2 == 0) {
                            simpleToolbar.setRightTitleText("全选");
                        } else {
                            simpleToolbar.setRightTitleText("取消");
                        }
                    }
                }
                /*showToast("当前选中页："+viewpager.getCurrentItem());
                showToast("选择的Tag标为："+tabs.getSelectedTabPosition());*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView title = (TextView) (((LinearLayout) ((LinearLayout) tabs.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
                title.setTextAppearance(getActivity(), R.style.TabLayoutStyle_UnSelect);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void Send(String sign) {

        if (sign.equals("oneUpData")) {
            if (systemFragment.mData.size() == 0) {
                simpleToolbar.setRightTitleText("");
            } else {
                if (systemFragment.fragment1 == 0) {
                    simpleToolbar.setRightTitleText("全选");
                } else {
                    simpleToolbar.setRightTitleText("取消");
                }
            }
        }

        if (sign.equals("twoUpData")) {
            if (activityFragment.mData.size() == 0) {
                simpleToolbar.setRightTitleText("");
            } else {
                if (activityFragment.fragment2 == 0) {
                    simpleToolbar.setRightTitleText("全选");
                } else {
                    simpleToolbar.setRightTitleText("取消");
                }
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        CallBackUtils.setCallBack(null);
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }

}
