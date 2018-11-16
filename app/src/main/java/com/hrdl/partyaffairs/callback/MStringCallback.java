package com.hrdl.partyaffairs.callback;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.hrdl.partyaffairs.activity.BottomTab.BottomTabActivity1;
import com.hrdl.partyaffairs.activity.LogInActivity;
import com.hrdl.partyaffairs.app.ActivityStackManager;
import com.hrdl.partyaffairs.app.App;
import com.hrdl.partyaffairs.app.UserManager;
import com.hrdl.partyaffairs.constant.HTTPJSONConstant;
import com.hrdl.partyaffairs.utils.ToastUtils;
import com.hrdl.partyaffairs.utils.Tools;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 网络请求统一回调类
 * 过滤掉服务器不是标准JSON格式情况与token过期情况和其他情况，
 * onSuccess方法执行情况：JSON格式数据，code = 0 与 code = -1中除去 user not login(用户未登录) session error(SESSION错误) 的情况
 * onError 方法执行情况 网络不通与其他特殊失败情况
 *
 * @author dzb
 */
public abstract class MStringCallback extends StringCallback {
    @Override
    public final void onSuccess(Response<String> response) {
        // 接口统一处理
        try {
            JSONObject object = new JSONObject(response.body());
            String code = object.getString("success");
            //token 过期过滤
            switch (code) {
                case HTTPJSONConstant.CODE_RESULT_OK:
                    // 0 数据返回成功
                    onSuccess(response.body());
                    break;
                case HTTPJSONConstant.CODE_RESULT_FAIL:
                    // -1 数据返回失败
                    String message = object.getString("message");
                    if ("session error SESSION".equals(message) || "user not login".equals(message)) {
                        onLoginInvalid();
                    } else {
                        onSuccess(response.body());
                    }
                    break;
                default:
                    onError(response);
                    break;
            }
        } catch (JSONException e) {
            onError(response);
        }
    }

    /**
     * 登陆状态失效，sessionKey过期时调用
     * 可以重写以满足自己的业务逻辑
     */
    protected void onLoginInvalid() {
        //OkGo.getInstance().cancelAll();
        UserManager.logout();
        if (!UserManager.isLogin(App.get())) {
            return;
        }
        // 清除用户信息
        UserManager.clearLoginInfo(App.get());
        //必须是Activity类型
        final Context context = ActivityStackManager.getInstance().getTop();
        if (context == null) {
            //提示用户
            ToastUtils.showToast(App.get(), "登录身份过期，请重新登录");
            return;
        }
        //启动首页
        Tools.showLoginInvalidDialog(context, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (ActivityStackManager.getInstance().existsTop(BottomTabActivity1.class)) {
                    context.startActivity(new Intent(context, LogInActivity.class));
                    return;
                }
                if (ActivityStackManager.getInstance().exists(BottomTabActivity1.class)) {
                    context.startActivity(new Intent(context, BottomTabActivity1.class));
                    context.startActivity(new Intent(context, LogInActivity.class));
                    return;
                }
                ActivityStackManager.getInstance().finishActivityList();
                context.startActivity(new Intent(context, BottomTabActivity1.class));
                context.startActivity(new Intent(context, LogInActivity.class));
            }
        });
        // TODO sessionKey过期后续处理
        //取消网络请求
        //OkGo.getInstance().cancelAll();
        /*Intent intent = new Intent(App.get(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("loginout", 404);
        UserManager.clearLoginDate(App.get());
        App.get().startActivity(intent);*/
    }

    /**
     * 数据返回正常调用该方法
     */
    public abstract void onSuccess(String response);
}
