package com.hrdl.partyaffairs.utils.pinying;

import java.util.ArrayList;

/**
 * 作者：王健 on 2018/8/28
 * 邮箱：845040970@qq.com
 */
public class LanguageConvent {

    /**
     * 返回中文拼音及英文大写，
     *
     * @return
     */
    public static String getPinYin(String input) {
        ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get(input);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            for (HanziToPinyin.Token token : tokens) {
                if (HanziToPinyin.Token.PINYIN == token.type) {
                    sb.append(token.target);
                } else {
                    sb.append(token.source);
                }
            }
        }else{
            //如果获取不到实例，则返回一个特殊字符
            sb.append("%");
        }
        return sb.toString().toUpperCase();
    }

}