package com.hrdl.partyaffairs.callback;

import io.reactivex.disposables.Disposable;

/**
 * 进度完成监听接口
 * Created by xinyang on 2017/12/15.
 */

public interface OnRxPermissionsListener {
    void onSubscribe(Disposable d);
    /**
     * b=true同意权限请求
     * b=false 拒绝权限请求
     * */
    void onNext(Boolean b);
    /**
     * 权限请求错误
     * */
    void onError(Throwable e);
    void onComplete();
}
