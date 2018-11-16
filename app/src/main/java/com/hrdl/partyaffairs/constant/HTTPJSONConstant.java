package com.hrdl.partyaffairs.constant;

/**
 * 网络接口相关常量
 */
public class HTTPJSONConstant {

    /*
     * 测试服务器
     */
    private static final String LOCALHOST = "party_api.lijingbo.top";
    /**
     * 请求成功
     */
    public static final String CODE_RESULT_OK = "1";
    /**
     * 请求失败
     */
    public static final String CODE_RESULT_FAIL = "0";


    /**
     * 获取token
     */
    public static final String Get_ToKen_URL = "http://"+LOCALHOST+"/api/token";
    /**
     * 用户登录
     */
    public static final String USERS_LogIn_URL = "http://"+LOCALHOST+"/api/login";

}
