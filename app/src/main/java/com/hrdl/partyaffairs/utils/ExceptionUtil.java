package com.hrdl.partyaffairs.utils;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常统一处理
 */
public class ExceptionUtil {

    /**
     * 异常信息打印
     */
    public static void handleException(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String string = stringWriter.toString();
        Log.e("异常信息", string);
    }

}
