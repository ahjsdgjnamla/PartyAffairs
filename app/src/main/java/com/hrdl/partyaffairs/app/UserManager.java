package com.hrdl.partyaffairs.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.hrdl.partyaffairs.constant.Const;
import com.hrdl.partyaffairs.entity.User;
import com.hrdl.partyaffairs.utils.SharedPrefsUtil;


/**
 * 用户数据相关操作类
 *
 * @author dzb
 */
public class UserManager {

    /**
     * 获取当前SESSION_KEY
     *
     * @return token 或 ""
     */
    public static String getSessionKey(Context context) {
        return SharedPrefsUtil.getValue(context, Const.SP_KEY, Const.SP_SESSION_KEY, "");
    }

    /**
     * 保存并设置全局SP_SESSION_KEY
     */
    public static void saveSessionKey(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) {
            return;
        }
        //保存token
        SharedPrefsUtil.putValue(context, Const.SP_KEY, Const.SP_SESSION_KEY, key);
        //设置全局token
        //header不支持中文，不允许有特殊字符(请求头)
        /*HttpHeaders headers = new HttpHeaders();
        headers.put("auth-token", key);
        OkGo.getInstance().addCommonHeaders(headers);*/
    }

    /**
     * 用户登录时执行方法
     */
    public static void login() {

    }

    /**
     * 用户退出登录或其他因业务逻辑使用户信息失效时执行该方法
     */
    public static void logout() {

    }

    /**
     * 获取当前用户登录状态
     *
     * @return true 登录  false 未登录
     */
    public static boolean isLogin(Context context) {
        return SharedPrefsUtil.getValue(context, Const.SP_KEY, Const.SP_LOGIN, false);
    }

    /**
     * 清除用户登录信息
     */
    public static void clearLoginInfo(Context context) {
        //清除登录状态
        SharedPrefsUtil.putValue(context, Const.SP_KEY, Const.SP_LOGIN, false);
        //清除token信息
        SharedPrefsUtil.putValue(context, Const.SP_KEY, Const.SP_SESSION_KEY, "");
        //清除用户信息
        clearUserInfoToSP(context);
        //清除账户信息
        SharedPrefsUtil.putValue(context, Const.SP_KEY, Const.SP_INCOME, 0f);
        SharedPrefsUtil.putValue(context, Const.SP_KEY, Const.SP_BALANCE, 0f);
        SharedPrefsUtil.putValue(context, Const.SP_KEY, Const.SP_PUTFORWARD, 0f);
    }

    /**
     * 将用户信息保存到sp中
     *
     * @param user 用户信息
     * @return 是否保存成功
     */
    public static boolean saveUserInfoToSP(Context context, User user) {
        if (user == null) {
            return false;
        }
        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences(Const.SP_USER_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("a", user.getId());
        editor.putString("b", user.getLoginName());
        editor.putString("c", user.getEmail());
        //editor.putFloat("d", user.getBalance());
        editor.putString("e", user.getMobile());
        editor.putString("f", user.getName());
        editor.putString("g", user.getSex());
        editor.putLong("h", user.getBirthday());
        editor.putString("i", user.getRegion());
        editor.putString("n", user.getHeadUrl());
        //editor.putInt("o", user.getScore());
        editor.putBoolean("p", user.isChangedLoginName());
        return editor.commit();
    }

    /**
     * 获取本地用户信息
     *
     * @return 用户信息
     */
    public static User getSPUser(Context context) {
        User user = new User();
        if (context == null) {
            return user;
        }
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(Const.SP_USER_KEY, Context.MODE_PRIVATE);
        user.setId(sp.getString("a", ""));
        user.setLoginName(sp.getString("b", ""));
        user.setEmail(sp.getString("c", ""));
        //user.setBalance(sp.getFloat("d", 0));
        user.setMobile(sp.getString("e", ""));
        user.setName(sp.getString("f", ""));
        user.setSex(sp.getString("g", ""));
        user.setBirthday(sp.getLong("h", 0));
        user.setRegion(sp.getString("i", ""));
        user.setHeadUrl(sp.getString("n", ""));
        //user.setScore(sp.getInt("o", 0));
        user.setChangedLoginName(sp.getBoolean("p", true));
        return user;
    }

    /**
     * 清除sp中的用户信息
     *
     * @return 是否清除成功
     */
    private static boolean clearUserInfoToSP(Context context) {
        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences(Const.SP_USER_KEY, Context.MODE_PRIVATE);
        return preferences.edit().clear().commit();
    }

    /**
     * 极光设置别名
     */
    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    /*public void setAlias() {
        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, "zwdemo"));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    LogUtils.e(logs);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    LogUtils.e(logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    LogUtils.e(logs);
            }
            ExampleUtil.showToast(logs,App.get().getApplicationContext());
        }
    };

    private static final int MSG_SET_ALIAS = 1001;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    LogUtils.e("Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(App.get().getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    LogUtils.e("Unhandled msg - " + msg.what);
            }
        }
    };*/

}
