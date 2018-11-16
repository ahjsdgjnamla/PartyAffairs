package com.hrdl.partyaffairs.utils;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import com.hrdl.partyaffairs.callback.OnRxPermissionsListener;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作者：王健 on 2018/8/28
 * 邮箱：845040970@qq.com
 * 权限申请工具类
 */
public class PermissionUtil {
    /**
     * @param : listener权限请求外部接口调用
     * @param : permissions要请求的权限，可以是多个
     * @作者 :  LT
     * @创建日期 :   2018/6/29 15:12
     * @arams :     Android6.0动态权限管理使用方法
     */
    public static void setOneOrMorePermissions(Activity mActivity, final OnRxPermissionsListener listener, final String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(mActivity);
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
}
