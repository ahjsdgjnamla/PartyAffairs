package com.hrdl.partyaffairs.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.gyf.barlibrary.ImmersionBar;
import com.hrdl.partyaffairs.app.ActivityStackManager;
import com.hrdl.partyaffairs.callback.OnRxPermissionsListener;
import com.hrdl.partyaffairs.utils.ExceptionUtil;
import com.hrdl.partyaffairs.utils.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * 作者：王健 on 2018/9/29
 * 邮箱：845040970@qq.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context context = this;
    private Unbinder unbinder;
    protected ImmersionBar mImmersionBar;
    private InputMethodManager imm;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将Activity添加到队列方便管理
        ActivityStackManager.getInstance().add(this);

        setContentView(setLayoutId());
        //绑定控件
        unbinder = ButterKnife.bind(this);
        //初始化沉浸式
        if (isImmersionBarEnabled()){
            initImmersionBar();
        }
        //绑定控件
        //初始化view
        initView();
        //初始化数据
        initData();
        //设置监听
        setListener();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        this.imm = null;
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
        try {
            ActivityStackManager.getInstance().remove(this);
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }
    protected abstract int setLayoutId();
    protected void initData() {
    }
    protected void initView() {
    }
    protected void setListener() {
    }
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }
    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }
    @Override
    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    public void showToast(String msg) {
        ToastUtils.showToast(getApplicationContext(), msg);
    }
    /**
     * 如果不知道之前是谁请求显示的软键盘，可以随便传入一个当前布局中存在的View的windowToken。特别的，可以传入一个Activity的顶层View的windowToken，即getWindow().getDecorView().getWindowToken()，来隐藏当前Activity中显示的软键盘，而不用管之前调用showSoftInput()的究竟是哪个View。
     * */
    public void wjHideInputMethod() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }
    /**
     * 高级隐藏软键盘
     */
    public void zwHideInputMethod() {
        //判断软键盘是否显示
        if (getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE) {
            //如果输入法在窗口上已经显示，则隐藏，反之则显示，所以必须先判定键盘是显示状态，否则不做任何操作
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    /**
     * 隐藏软键盘(可用于Activity，Fragment)
     * viewList：能调起软键盘的view，统一添加到list组，循环检查键盘，进行隐藏
     */
    public void hideSoftKeyboard(Context context, List<View> viewList) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        for (View v : viewList) {
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }
    /**
     * 高效加载字体包需重写此方法
     * */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * @param : listener权限请求外部接口调用
     * @param : permissions要请求的权限，可以是多个
     * @作者 :  LT
     * @创建日期 :   2018/6/29 15:12
     * @arams :     Android6.0动态权限管理使用方法
     */
    public void setOneOrMorePermissions(final OnRxPermissionsListener listener, final String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(permissions)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        listener.onSubscribe(d);
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        listener.onNext(aBoolean);
//                        if (aBoolean) {
//                          //同意权限
//                        } else {
//                            showToast("拒绝请求权限");
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }

    /**
     * 开启进度框
     */
    public void showLoading(String msg, DialogInterface.OnCancelListener onCancelListener) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        dialog = new ProgressDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        if (TextUtils.isEmpty(msg)) {
            dialog.setMessage("正在加载...");
        } else {
            dialog.setMessage(msg);
        }
        if (onCancelListener != null) {
            dialog.setOnCancelListener(onCancelListener);
        }
        dialog.show();
    }

    /*
     *关闭对话框
     * */
    public void dismissLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}