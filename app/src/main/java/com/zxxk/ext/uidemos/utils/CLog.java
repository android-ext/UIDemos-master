package com.zxxk.ext.uidemos.utils;

import android.util.Log;

/**
 * 使用该类方便统一关闭日志输出
 */
public class CLog {
    public static boolean isPrintLog = false;

    public static int d(String tag, String msg) {
        if (isPrintLog)
            return Log.d(tag, msg);
        return 0;
    }

    public static int i(String tag, String msg) {
        if (isPrintLog)
            return Log.i(tag, msg);
        return 0;
    }

    public static int e(String tag, String msg) {
        if (isPrintLog)
            return Log.e(tag, msg);
        return 0;
    }

    public static int e(String tag, String msg, Exception e) {
        if (isPrintLog)
            return Log.e(tag, msg, e);
        return 0;
    }

    public static int v(String tag, String msg) {
        if (isPrintLog)
            return Log.v(tag, msg);
        return 0;
    }

    public static int w(String tag, String msg) {
        if (isPrintLog)
            return Log.w(tag, msg);
        return 0;
    }
}
