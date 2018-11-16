package com.hrdl.partyaffairs.activity.BottomTab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.app.App;
import com.hrdl.partyaffairs.base.BaseActivity;
import com.hrdl.partyaffairs.fragment.TestBottomFragment1;
import com.hrdl.partyaffairs.fragment.TestBottomFragment2;
import com.hrdl.partyaffairs.fragment.TestBottomFragment3;
import com.hrdl.partyaffairs.fragment.TestBottomFragment4;
import com.hrdl.partyaffairs.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王健 on 2018/9/30
 * 邮箱：845040970@qq.com
 * viewpager+fragment实现底部导航
 */
public class BottomTabActivity1 extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    List<Fragment> mListFragment = new ArrayList<>();
    @BindView(R.id.vp_content)
    CustomViewPager vpContent;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ll_category)
    LinearLayout llCategory;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.ll_service)
    LinearLayout llService;
    @BindView(R.id.ll_service_my)
    LinearLayout llServiceMy;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_testbottomtab1;
    }

    @Override
    protected void initView() {
        super.initView();
        inittab();
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this).keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN).transparentStatusBar();
        mImmersionBar.init();
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
        vpContent.setScroll(true);//禁止滑动
        vpContent.setAdapter(new MyAdapter(getSupportFragmentManager()));
        vpContent.setOffscreenPageLimit(2);
        llHome.setSelected(true);
    }

    @Override
    protected void setListener() {
        super.setListener();
        llHome.setOnClickListener(this);
        llCategory.setOnClickListener(this);
        llService.setOnClickListener(this);
        vpContent.addOnPageChangeListener(this);
        llServiceMy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                vpContent.setCurrentItem(0);
                tabSelected(llHome);
                break;
            case R.id.ll_category:
                vpContent.setCurrentItem(1);
                tabSelected(llCategory);
                break;
            case R.id.ll_service:
                vpContent.setCurrentItem(2);
                tabSelected(llService);
                break;
            case R.id.ll_service_my:
                vpContent.setCurrentItem(3);
                tabSelected(llServiceMy);
                break;
            default:
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i) {
            case 0:
                tabSelected(llHome);
                break;
            case 1:
                tabSelected(llCategory);
                break;
            case 2:
                tabSelected(llService);
                break;
            case 3:
                tabSelected(llServiceMy);
                break;
            default:
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private void tabSelected(LinearLayout linearLayout) {
        llHome.setSelected(false);
        llCategory.setSelected(false);
        llService.setSelected(false);
        llServiceMy.setSelected(false);
        linearLayout.setSelected(true);
    }

    /**
     * 退出程序时间
     */
    private long exit;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - exit > 2000) {
            exit = currentTime;
            showToast("再按一次退出应用");
        } else {
            super.onBackPressed();
            App.get().exit();
        }
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
