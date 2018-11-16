package com.hrdl.partyaffairs;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.hrdl.partyaffairs.activity.BottomTab.BottomTabActivity1;
import com.hrdl.partyaffairs.activity.LogInActivity;
import com.hrdl.partyaffairs.app.App;
import com.hrdl.partyaffairs.app.UserManager;
import com.hrdl.partyaffairs.base.BaseActivity;
import com.hrdl.partyaffairs.callback.MStringCallback;
import com.hrdl.partyaffairs.callback.OnRxPermissionsListener;
import com.hrdl.partyaffairs.constant.Const;
import com.hrdl.partyaffairs.constant.HTTPJSONConstant;
import com.hrdl.partyaffairs.utils.ExceptionUtil;
import com.hrdl.partyaffairs.utils.LogUtils;
import com.hrdl.partyaffairs.utils.SharedPrefsUtil;
import com.hrdl.partyaffairs.welcome.WelcomeActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {
    String url = "http://img.zcool.cn/community/01700557a7f42f0000018c1bd6eb23.jpg";
    @BindView(R.id.id_btn)
    Button idBtn;
    @BindView(R.id.iv_image)
    ImageView ivImage;

    /**
     * TAG
     */
    private static final String TAG = "MainActivity";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this).keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN).transparentStatusBar();
        mImmersionBar.init();
    }

    @Override
    protected void initView() {
        super.initView();

        //申请权限
        //申请多个权限分别做处理
        setOneOrMorePermissions(new OnRxPermissionsListener() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Boolean b) {
                                        if (b) {
                                            next();
                                        } else {
                                            showToast("获取权限失败，请授权后使用");
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    App.get().exit();
                                                }
                                            }, 1500);
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        showToast("获取权限失败，请授权后使用");
                                        App.get().exit();
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                },
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.INTERNET);
    }

    /**
     * 调起动态权限管理
     */
    /*private void deleteCalendarEvent1() {
        setOnPermissionListener(new OnPermissionListener() {
            @Override
            public void openIntent() {//设置权限监听之后，执行自己的操作
                Update();
            }
        });
        openPermission(new int[]{PermissionUtils1.CODE_STORAGE});//请求拍照权限，这里可设置多个请求权限
    }*/

    /**
     * 获取ToKen
     */
    private void getToken() {
        OkGo.<String>get(HTTPJSONConstant.Get_ToKen_URL)
                .tag(TAG)
                .execute(new MStringCallback() {
                    @Override
                    public void onSuccess(String response) {
                        LogUtils.e("获取token数据："+response);
                        try {
                            JSONObject object = new JSONObject(response);
                            // 解析数据
                            if (HTTPJSONConstant.CODE_RESULT_OK.equals(object.getString("success"))) {
                                //保存token
                                SharedPrefsUtil.putValue(MainActivity.this, Const.SP_KEY, Const.SP_ToKen, object.getString("data"));

                                // 判断是否第一次使用
                                boolean isFirst = SharedPrefsUtil.getValue(MainActivity.this, Const.APP_NAME_TAG, Const.SP_IS_FIRST, true);
                                if (isFirst) {
                                    //跳转引导页
                                    startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                                    finish();
                                    LogUtils.e(SharedPrefsUtil.getValue(MainActivity.this, Const.SP_KEY,Const.SP_ToKen,""));
                                    return;
                                }
                                if (UserManager.isLogin(MainActivity.this)) {
                                    //跳转首页
                                    startActivity(new Intent(MainActivity.this, BottomTabActivity1.class));
                                } else {
                                    //跳转登录页
                                    startActivity(new Intent(MainActivity.this, LogInActivity.class));
                                }

                                LogUtils.e(SharedPrefsUtil.getValue(MainActivity.this, Const.SP_KEY,Const.SP_ToKen,""));
                                finish();

                            } else {
                                showToast(object.getString("message"));
                            }
                        } catch (Exception e) {
                            showToast("无法连接服务器");
                            ExceptionUtil.handleException(e);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast("无法连接到网络");
                    }

                    @Override
                    public void onFinish() {
                    }
                });
    }

    /**
     * 申请权限后执行
     */
    private void next() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                getToken();
                // 判断是否第一次使用
                /*boolean isFirst = SharedPrefsUtil.getValue(MainActivity.this, Const.APP_NAME_TAG, Const.SP_IS_FIRST, true);
                if (isFirst) {
                    //跳转引导页
                    startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                    finish();
                    return;
                }
                if (UserManager.isLogin(MainActivity.this)) {
                    //跳转首页
                    startActivity(new Intent(MainActivity.this, BottomTabActivity.class));
                } else {
                    //跳转登录页
                    startActivity(new Intent(MainActivity.this, LogInActivity.class));
                }
                finish();*/
            }
        }, 1500);
    }


    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /*private void setBar() {
        CustomToolbarUtil.setTopToolbar(qmuitop, this, "测试标题", ContextCompat.getColor(this, R.color.qmui_config_color_50_blue)
                , R.mipmap.ic_jiantou_white_left, R.mipmap.ic_jiantou_white_left, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("返回按键左");
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("返回按键右");
                    }
                });
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
