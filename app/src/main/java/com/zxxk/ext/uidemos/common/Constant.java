package com.zxxk.ext.uidemos.common;

import android.os.Environment;

/**
 * 系统中使用的常量
 */
public interface Constant {

    /**
     * 请求类型
     */
    public static final int ACCESS_TYPE_GET = 0;
    public static final int ACCESS_TYPE_POST = 1;

    /**
     * sdcard文件存储路径
     */
    public static final String BASEFILEPATH = Environment.getExternalStorageDirectory().getPath() + "/www.zxxk.com/tiku";



}
