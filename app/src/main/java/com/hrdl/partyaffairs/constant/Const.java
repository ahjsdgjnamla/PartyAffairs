package com.hrdl.partyaffairs.constant;

import android.os.Environment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 常量类
 */
public class Const {
    /**
     * 消息类型：系统消息
     */
    public static final String SYSTEM_MESSAGE_TYPE = "0";
    /**
     * 消息类型：案件消息
     */
    public static final String CASE_MESSAGE_TYPE = "1";
    /**
     * 消息类型：活动消息
     */
    public static final String ACTIVIT_MESSAGE_TYPE = "2";
    /**
     * app名称标签
     */
    public static final String APP_NAME_TAG = "PartyAffairs";
    /**
     * key
     */
    public static final String KEY = "KEY";
    /**
     * spkey
     */
    public static final String SP_KEY = "PartyAffairs";
    /**
     * 是否第一次使用
     */
    public static final String SP_IS_FIRST = "isFirst";
    /**
     * 是否是登录状态
     */
    public static final String SP_LOGIN = "isLogin";
    /**
     * 登录名
     */
    public static final String SP_USER_NAME = "userName";
    /**
     * sessionKey 会话标识
     */
    public static final String SP_SESSION_KEY = "sessionKey";
    /**
     * ToKen 会话标识
     */
    public static final String SP_ToKen = "token";
    /**
     * SharedPreferences 保存用户信息的key
     */
    public static final String SP_USER_KEY = "UserInfo";
    /**
     * SharedPreferences 保存用户信息的key
     */
    public static final String SP_ACCIDENT_TYPE_KEY = "AccidentType";
    /**
     * 总收入
     */
    public static final String SP_INCOME = "income";
    /**
     * 余额
     */
    public static final String SP_BALANCE = "balance";
    /**
     * 提现
     */
    public static final String SP_PUTFORWARD = "putForward";
    /**
     * 是否刷新用户信息
     */
    public static final String SP_IS_REFRESH_USER_INFO = "isRefreshUserInfo";
    /**
     * 是否是否刷新案件数量
     */
    public static final String SP_IS_REFRESH_ACCIDENT_COUNT = "isRefreshAccidentCount";
    /**
     * 是否是否刷新红包数量
     */
    public static final String SP_IS_REFRESH_PACKET_COUNT = "isRefreshPacketCount";
    /**
     * 是否是否刷新点赞数量
     */
    public static final String SP_IS_REFRESH_NUMBER_LIKE = "isRefreshPacketCount";
    /**
     * 是否是否刷新全部案件列表
     */
    public static final String SP_IS_REFRESH_ACCIDENT_LIST_ALL = "isRefreshAccidentListAll";
    /**
     * 是否是否刷新待评价列表
     */
    public static final String SP_IS_REFRESH_ACCIDENT_LIST_EVALUATE = "isRefreshAccidentListEvaluate";
    /**
     * 是否是否刷新已结束列表
     */
    public static final String SP_IS_REFRESH_ACCIDENT_LIST_END = "isRefreshAccidentListEnd";
    /**
     * 是否是否刷新已领取红包列表
     */
    public static final String SP_IS_REFRESH_RED_LIST = "isRefreshRedList";
    /**
     * 是否是否刷新未领取红包列表
     */
    public static final String SP_IS_REFRESH_NO_RED_LIST = "isRefreshNoRedList";
    /**
     * 是否是否刷新靓丽城市点赞列表
     */
    public static final String SP_IS_REFRESH_LIKE = "isRefreshNoRedList";
    /**
     * 是否是否刷新消息数量
     */
    public static final String SP_IS_REFRESH_MESSAGE_COUNT = "isRefreshMessageCount";
    /**
     * 微信APPID
     */
    public static final String WeiXin_APP_ID = "wxfe875a1a54dec286";
    /**
     * 微信 AppSecret
     */
    static final String WeiXin_APP_SECRET = "9772f31b059e0dfd755df5127e410629";
    //微信授权结果
    /**
     * 同意授权
     */
    public static final int WX_AUTH_OK = 0;
    /**
     * 拒绝授权
     */
    public static final int WX_AUTH_DENIED = -4;
    /**
     * 取消授权
     */
    public static final int WX_AUTH_CANCEL = -2;
    /**
     * 授权失败
     */
    public static final int WX_AUTH_FAIL = -1;
    /**
     * 读取授权结果码
     */
    public static final String SP_WX_AUTH_CODE = "WX_AUTH_RESULT";
    /**
     * 读取授权openId
     */
    public static final String SP_WX_AUTH_OPEN_ID = "WX_AUTH_OPEN_ID";
    /**
     * 读取授权微信昵称
     */
    public static final String SP_WX_AUTH_NAME = "WX_AUTH_NAME";
    /**
     * 读取授权微信头像地址
     */
    public static final String SP_WX_AUTH_HEAD_URL = "WX_AUTH_HEAD_URL";
    /**
     * SD卡的文件路径
     */
    private static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
    /**
     * 下载目录
     */
    public static final String DOWNLOAD_PATH = SDCARD_ROOT + "/" + APP_NAME_TAG + "/download/";
    /**
     * 临时文件缓存目录
     */
    public static final String CACHE_PATH = SDCARD_ROOT + "/" + APP_NAME_TAG + "/cache/";
    /**
     * 图片存放目录
     */
    public static final String IMG_PATH = SDCARD_ROOT + "/" + APP_NAME_TAG + "/img/";

    public static String getContent(JSONObject object, String ject){
        String content=null;
        try {
            if(object.has(ject) && object.getString(ject)!=null && !object.getString(ject).equals("") && !object.getString(ject).equals("null")){
                content=object.getString(ject);
            }else{
                content="暂无";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return content;
    }
}