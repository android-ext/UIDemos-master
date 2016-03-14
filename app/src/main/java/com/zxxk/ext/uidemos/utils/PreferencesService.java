package com.zxxk.ext.uidemos.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Map;
import java.util.Set;

/**
 * 数据存取类
 */
public class PreferencesService {
    private static SharedPreferences mPreferences;
    private static Context mContext;
    private static String mShareName = "XUE_YI_TONG";

    private PreferencesService() {
    }

    private static PreferencesService mPreferencesService;

    public static PreferencesService getInstance(Context context) {
        if (mPreferencesService == null) {
            mContext = context.getApplicationContext();
            mPreferencesService = new PreferencesService();
            mPreferences = mContext.getSharedPreferences(mShareName,
                    Context.MODE_PRIVATE);
        }
        return mPreferencesService;
    }

    /**
     * 批量保存
     */
    public void saveInfo(Map<String, String> info) {
        Editor editor = mPreferences.edit();
        Set<String> keys = info.keySet();
        for (String str : keys) {
            editor.putString(str, info.get(str));
        }
        editor.commit();
    }

    /**
     * 保存string类型数据
     */
    public void putString(String key, String value) {
        Editor editor = mPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 根据key读取参数
     */
    public String getString(String key) {
        return mPreferences.getString(key, "");
    }


    /**
     * 保存long类型数据
     */
    public void putLong(String key, long value) {
        Editor editor = mPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 保存long类型数据
     */
    public long getLong(String key) {
        return mPreferences.getLong(key, -1);
    }


    /**
     * 保存int类型数据
     *
     * @param key
     * @param value
     */
    public void putInt(String key, int value) {
        Editor editor = mPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 获取int类型数据
     *
     * @param key
     * @return
     */
    public int getInt(String key) {
        return mPreferences.getInt(key, 0);
    }


    /**
     * 读取状态
     */
    public boolean getBoolean(String key, boolean def) {
        return mPreferences.getBoolean(key, def);
    }

    /**
     * 读取状态
     */
    public void putBoolean(String key, boolean value) {
        Editor editor = mPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

}
