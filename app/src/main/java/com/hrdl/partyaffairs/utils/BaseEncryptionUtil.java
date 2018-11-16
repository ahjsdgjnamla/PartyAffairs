package com.hrdl.partyaffairs.utils;

import android.util.Base64;
import java.io.UnsupportedEncodingException;

/**
 * Created by xiaomi on 2018/7/12.
 * Base64加密解密
 */
public class BaseEncryptionUtil {
    /**
     * 加密
     * oldWord：需要加密的文字/比如密码
     */
    public static String setEncryption(String oldWord){
        String encodeWord="";
        if(oldWord!=null){
            try {
                encodeWord = Base64.encodeToString(oldWord.getBytes("utf-8"), Base64.NO_WRAP);
                LogUtils.e("encode wrods="+encodeWord);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return encodeWord;
    }
    /**
     * 解密
     * encodeWord：加密后的文字/比如密码
     */
    public static String setDecrypt(String encodeWord){
        String decodeWord="";
        if(encodeWord!=null){
            try {
                decodeWord = new String(Base64.decode(encodeWord, Base64.NO_WRAP), "utf-8");
                LogUtils.e("decode wrods="+decodeWord);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return decodeWord;
    }
}
