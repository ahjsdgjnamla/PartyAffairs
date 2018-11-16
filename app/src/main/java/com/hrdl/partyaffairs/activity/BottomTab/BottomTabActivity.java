package com.hrdl.partyaffairs.activity.BottomTab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;

import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.base.BaseActivity;
import com.hrdl.partyaffairs.callback.CallBackUtils;
import com.hrdl.partyaffairs.fragment.TestBottomFragment1;
import com.hrdl.partyaffairs.fragment.TestBottomFragment2;
import com.hrdl.partyaffairs.fragment.TestBottomFragment3;
import com.hrdl.partyaffairs.fragment.TestBottomFragment4;
import com.hrdl.partyaffairs.view.CustomViewPager;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王健 on 2018/9/30
 * 邮箱：845040970@qq.com
 * 使用QMUI实现的viewpager+fragment底部导航
 */
public class BottomTabActivity extends BaseActivity {
    @BindView(R.id.vp_content)
    CustomViewPager vpContent;
    @BindView(R.id.tabs)
    QMUITabSegment tabs;
    List<Fragment> mListFragment = new ArrayList<>();
    @Override
    protected int setLayoutId() {
        return R.layout.activity_testbottomtab;
    }

    @Override
    protected void initView() {
        super.initView();
        inittab();
        CallBackUtils.doCallBackMethod("true");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    private void inittab() {
        TestBottomFragment1 tb1 = new TestBottomFragment1();
        TestBottomFragment2 tb2 = new TestBottomFragment2();
        TestBottomFragment3 tb3 = new TestBottomFragment3();
        TestBottomFragment4 tb4 = new TestBottomFragment4();
        mListFragment.add(tb1);
        mListFragment.add(tb2);
        mListFragment.add(tb3);
        mListFragment.add(tb4);

        vpContent.setAdapter(new MyAdapter(getSupportFragmentManager()));
        //设置是否可以滑动
        vpContent.setScroll(true);
        //设置预加载数
        vpContent.setOffscreenPageLimit(3);
        QMUITabSegment.Tab tab1=new QMUITabSegment.Tab(ContextCompat.getDrawable(this, R.mipmap.ic_tab_strip_icon_feed),
                ContextCompat.getDrawable(this, R.mipmap.ic_tab_strip_icon_feed_selected),
                "首页", false);
        QMUITabSegment.Tab tab2=new QMUITabSegment.Tab(ContextCompat.getDrawable(this, R.mipmap.ic_tab_strip_icon_follow),
                ContextCompat.getDrawable(this, R.mipmap.ic_tab_strip_icon_follow_selected),
                "待处理",false);
        QMUITabSegment.Tab tab3=new QMUITabSegment.Tab(ContextCompat.getDrawable(this, R.mipmap.ic_tab_strip_icon_category),
                ContextCompat.getDrawable(this, R.mipmap.ic_tab_strip_icon_category_selected),
                "未通过",false);
        QMUITabSegment.Tab tab4=new QMUITabSegment.Tab(ContextCompat.getDrawable(this, R.mipmap.ic_tab_strip_icon_profile),
                ContextCompat.getDrawable(this, R.mipmap.ic_tab_strip_icon_profile_selected),
                "已结束",false);
        tabs.addTab(tab1);
        tabs.addTab(tab2);
        tabs.addTab(tab3);
        tabs.addTab(tab4);
        upDataUi(tab3);
        //int normalColor = QMUIResHelper.getAttrColor(this, R.attr.qmui_config_color_gray_6);
        //int selectColor = QMUIResHelper.getAttrColor(this, R.attr.qmui_config_color_blue);

        tabs.setDefaultNormalColor(ContextCompat.getColor(this,R.color.colortestBottomtabText));    //设置tab正常下的颜色
        tabs.setDefaultSelectedColor(ContextCompat.getColor(this,R.color.colortestBottomtabTextSelected));    //设置tab选中下的颜色
        tabs.setBackgroundColor(ContextCompat.getColor(this,R.color.white));

        /*获取sp值*/
        //float pxValue = getResources().getDimension(R.dimen.sp_18);//获取对应资源文件下的sp值
        //int spValue = ConvertUtils.px2sp(this, pxValue);//将px值转换成sp值

        tabs.setTabTextSize((int) getResources().getDimension(R.dimen.sp_10));
        tabs.setHasIndicator(false);//代码中设置是否需要显示 indicator
        tabs.setIndicatorPosition(false);//代码中设置 indicator 的位置
        //ScrollMode下item的间隙
        tabs.setItemSpaceInScrollMode(0);
        tabs.setIndicatorWidthAdjustContent(false);//代码中设置 indicator的宽度是否随内容宽度变化
        //QMUITabSegment.MODE_SCROLLABLE表示item内容自适应，超过父容器则滚动,QMUITabSegment.MODE_FIXED表示固定宽度，item内容均分
        tabs.setMode(QMUITabSegment.MODE_FIXED);
        tabs.setupWithViewPager(vpContent, false);


        tabs.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {//mTabSegment选项被选中的监听
            /**
             * 当某个 Tab 被选中时会触发
             *
             * @param index 被选中的 Tab 下标
             */
            @Override
            public void onTabSelected(int index) {
                tabs.hideSignCountView(index);//隐藏红点
            }
            /**
             * 当某个 Tab 被取消选中时会触发
             *
             * @param index 被取消选中的 Tab 下标
             */
            @Override
            public void onTabUnselected(int index) {

            }
            /**
             * 当某个 Tab 处于被选中状态下再次被点击时会触发
             *
             * @param index 被再次点击的 Tab 下标
             */
            @Override
            public void onTabReselected(int index) {
                tabs.hideSignCountView(index);
            }

            /**
             * 当某个 Tab 被双击时会触发
             *
             * @param index 被双击的 Tab 下标
             */
            @Override
            public void onDoubleTap(int index) {

            }
        });

    }
    /**
     * 设置小红点的数量
     * */
    private void upDataUi(QMUITabSegment.Tab tabs) {
        //设置红点显示位置
        tabs.setSignCountMargin(-3, 0);
        // 设置红点中数字显示的最大位数
        tabs.setmSignCountDigits(5);
        // 第二个参数表示：显示的消息数
        tabs.showSignCountView(this,3);
    }
    class MyAdapter extends FragmentStatePagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mListFragment.get(position);
        }

        @Override
        public int getCount() {
            return mListFragment.size();
        }
    }
}
