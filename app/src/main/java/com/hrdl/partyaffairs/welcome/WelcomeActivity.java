package com.hrdl.partyaffairs.welcome;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.activity.LogInActivity;
import com.hrdl.partyaffairs.app.App;
import com.hrdl.partyaffairs.base.BaseActivity;
import com.hrdl.partyaffairs.constant.Const;
import com.hrdl.partyaffairs.utils.SharedPrefsUtil;
import com.hrdl.partyaffairs.utils.UIUtil;

import java.util.ArrayList;

/**
 * 引导页
 *
 * @author dzb
 */
public class WelcomeActivity extends BaseActivity {

    /**
     * 指示器
     */
    private LinearLayout llIndicator;
    /**
     * 当前页面所在位置
     */
    private int currentPosition = 0;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        //引导页集合
        final ArrayList<Integer> urlRes = new ArrayList<>();
        urlRes.add(R.mipmap.item_1);
        urlRes.add(R.mipmap.item_2);
        urlRes.add(R.mipmap.item_3);
        urlRes.add(R.mipmap.item_4);
        urlRes.add(R.mipmap.item_5);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new WelcomeAdapter(urlRes));
        //设置指示器
        llIndicator = findViewById(R.id.llIndicator);
        for (int i = 1; i <= urlRes.size(); i++) {
            addIndicatorPoint();
        }
        llIndicator.getChildAt(0).setEnabled(true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                llIndicator.getChildAt(currentPosition).setEnabled(false);
                llIndicator.getChildAt(position).setEnabled(true);
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 给广告指示器添加小点
     */
    private void addIndicatorPoint() {
        View dot = new View(this);
        dot.setBackgroundResource(R.drawable.point_bg_blue);
        int size = UIUtil.dip2px(this, 6);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
        params.leftMargin = size;
        params.rightMargin = size;
        dot.setLayoutParams(params);
        dot.setEnabled(false);
        llIndicator.addView(dot);
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

    /**
     * 引导页适配器
     */
    class WelcomeAdapter extends PagerAdapter {

        private ArrayList<Integer> res;

        WelcomeAdapter(ArrayList<Integer> res) {
            if (res == null) {
                this.res = new ArrayList<>();
            } else {
                this.res = res;
            }
        }

        @Override
        public int getCount() {
            return res.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_navigation, container, false);
            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(res.get(position));
            if (position == res.size() - 1) {
                TextView tvEnter = view.findViewById(R.id.tvEnter);
                tvEnter.setVisibility(View.VISIBLE);
                tvEnter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //保存第一次使用信息
                        SharedPrefsUtil.putValue(getApplicationContext(), Const.APP_NAME_TAG, Const.SP_IS_FIRST, false);
                        //启动登录页
                        startActivity(new Intent(WelcomeActivity.this, LogInActivity.class));
                        finish();
                    }
                });
            }
            container.addView(view);
            return view;
        }

    }
}
