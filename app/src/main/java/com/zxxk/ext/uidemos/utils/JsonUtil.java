package com.zxxk.ext.uidemos.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class JsonUtil {

    private static String TAG = "JsonUtil";

    // Constant values
    public static final int ERROR_INTEGER = 0;
    public static final double ERROR_DOUBLE = 0f;
    public static final boolean ERROR_BOOLEAN = false;

    private JsonUtil() {
    }


    /**
     *
     * @param jsonString
     * @return
     */
    public static JSONObject loadJSON(String jsonString) {
        try {
            if (jsonString != null && jsonString.length() != 0) {
                return new JSONObject(jsonString);
            }
        } catch (JSONException e) {
            CLog.e(TAG, "parse json string to object failed! jsonString:" + jsonString, e);
        }
        return null;
    }


    /**
     *
     * @param jsonArrayString
     * @return
     */
    public static JSONArray loadJsonArray(String jsonArrayString) {
        try {
            if (jsonArrayString != null && jsonArrayString.length() != 0) {

//            	CLog.e(TAG,  "data---->" + jsonArrayString);
                return new JSONArray(jsonArrayString);
            }
        } catch (JSONException e) {
            CLog.e(TAG, "parse json array to object failed! jsonString:" + jsonArrayString, e);
        }
        return null;
    }


    /**
     *
     * @param jsonObject
     * @param aName
     * @return
     */
    public static int getInt(JSONObject jsonObject, String aName) {
        try {
            if (jsonObject != null && jsonObject.has(aName)) {
                return jsonObject.getInt(aName);
            }
            return 0;
        } catch (JSONException e) {
            CLog.e(TAG, "get int from json object failed! jsonObject:" + jsonObject + "\tname:" + aName, e);
        }
        return ERROR_INTEGER;
    }

    /**
     * Get long from JSON object
     *
     * @param aJoObj
     * @param aName
     * @return
     */
    public static long getLong(JSONObject aJoObj, String aName) {
        try {
            if (aJoObj != null && aJoObj.has(aName)) {
                return aJoObj.getLong(aName);
            }
        } catch (JSONException e) {
            CLog.e(TAG, "get long from json object failed! jsonObject:" + aJoObj + "\tname:" + aName, e);
        }
        return ERROR_INTEGER;
    }

    /**
     * Get string from JSON object
     *
     * @param aJoObj
     * @param aName
     * @return
     */
    public static String getString(JSONObject aJoObj, String aName) {
        try {
            if (aJoObj != null && aJoObj.has(aName)) {
                String value = aJoObj.getString(aName);
                if (!value.equals(JSONObject.NULL)) {

                    /**
                     * 因为数据库直接返回会有null的返回
                     */
                    return value.replace("null", "").replace("<null>", "");
                }
            }
        } catch (JSONException e) {
            CLog.e(TAG, "get string from json object failed! jsonObject:" + aJoObj + "\tname:" + aName, e);
        }
        return null;
    }

    /**
     * Get boolean from JSON object
     *
     * @param aJoObj
     * @param aName
     * @return
     */
    public static boolean getBoolean(JSONObject aJoObj, String aName) {
        try {
            if (aJoObj != null && aJoObj.has(aName)) {
                return aJoObj.getBoolean(aName);
            }
        } catch (JSONException e) {
            CLog.e(TAG, "get boolean from json object failed! jsonObject:" + aJoObj + "\tname:" + aName, e);
        }
        return ERROR_BOOLEAN;
    }


    /**
     * Get double from JSON object
     *
     * @param aJoObj
     * @param aName
     * @return
     */
    public static double getDouble(JSONObject aJoObj, String aName) {
        try {
            if (aJoObj != null && aJoObj.has(aName)) {
                return aJoObj.getDouble(aName);
            }
        } catch (JSONException e) {
            CLog.e(TAG, "get double from json object failed! jsonObject:" + aJoObj + "\tname:" + aName, e);
        }
        return ERROR_DOUBLE;
    }

    /**
     * Get sub object from JSON object
     *
     * @param aJoObj
     * @param aName
     * @return
     */
    public static JSONObject getJSONObject(JSONObject aJoObj, String aName) {
        try {
            if (aJoObj != null && aJoObj.has(aName)) {
                return aJoObj.getJSONObject(aName);
            }
        } catch (JSONException e) {
            CLog.e(TAG, "get jsonObject from json object failed! jsonObject:" + aJoObj + "\tname:" + aName, e);
        }
        return null;
    }

    /**
     * Get sub JSON array from JSON object
     *
     * @param aJoObj
     * @param aName
     * @return
     */
    public static JSONArray getJSONArray(JSONObject aJoObj, String aName) {
        try {
            if (aJoObj != null && aJoObj.has(aName)) {
                return aJoObj.getJSONArray(aName);
            }
        } catch (JSONException e) {
            CLog.e(TAG, "get json array from json object failed! jsonObject:" + aJoObj + "\tname:" + aName, e);
        }
        return null;
    }

    /**
     * Get the length of a JSON array
     *
     * @param aJoArray
     * @return
     */
    public static int getArrayLength(JSONArray aJoArray) {
        if (aJoArray != null) {
            return aJoArray.length();
        }
        return 0;
    }

    /**
     * Get JSON object from JSON array
     *
     * @param aJoArray
     * @param aIndex
     * @return
     */
    public static JSONObject getJSONObject(JSONArray aJoArray, int aIndex) {
        try {
            if (aJoArray != null && !aJoArray.isNull(aIndex)) {
                return aJoArray.getJSONObject(aIndex);
            }
        } catch (JSONException e) {
            CLog.e(TAG, "get string from json object failed! jsonArray:" + aJoArray.toString() + "\taIndex:" + aIndex, e);
        }
        return null;
    }

    /**
     * Get string from JSON array
     *
     * @param aJoArray
     * @param aIndex
     * @return
     */
    public static String getStringFromArray(JSONArray aJoArray, int aIndex) {
        try {
            if (aJoArray != null && !aJoArray.isNull(aIndex)) {
                String value = aJoArray.getString(aIndex);
                if (!value.equals(JSONObject.NULL)) {
                    return value;
                }
            }
        } catch (JSONException e) {
            CLog.e(TAG, "get string from json Array failed! jsonArray:" + aJoArray.toString() + "\taIndex:" + aIndex, e);
        }
        return null;
    }

    /**
     * Get integer from JSON array
     *
     * @param aJoArray
     * @param aIndex
     * @return
     */
    public static int getIntFromArray(JSONArray aJoArray, int aIndex) {
        try {
            if (aJoArray != null && !aJoArray.isNull(aIndex)) {
                return aJoArray.getInt(aIndex);
            }
        } catch (JSONException e) {
            CLog.e(TAG, "get int from json array failed! jsonArray:" + aJoArray.toString() + "\taIndex:" + aIndex, e);
        }
        return ERROR_INTEGER;
    }

    /**
     * Get boolean from JSON array
     *
     * @param aJoArray
     * @param aIndex
     * @return
     */
    public static boolean getBoolFromArray(JSONArray aJoArray, int aIndex) {
        try {
            if (aJoArray != null && !aJoArray.isNull(aIndex)) {
                return aJoArray.getBoolean(aIndex);
            }
        } catch (JSONException e) {
            CLog.e(TAG, "get boolean from json array failed! jsonArray:" + aJoArray.toString() + "\taIndex:" + aIndex, e);
        }
        return ERROR_BOOLEAN;
    }

    public static String object2json(Object obj) {
        StringBuilder json = new StringBuilder();
        if (obj == null) {
            json.append("\"\"");
        } else if (obj instanceof String || obj instanceof Integer || obj instanceof Float
                || obj instanceof Boolean || obj instanceof Short || obj instanceof Double
                || obj instanceof Long || obj instanceof BigDecimal || obj instanceof BigInteger
                || obj instanceof Byte) {
            json.append("\"").append(string2json(obj.toString())).append("\"");
        } else if (obj instanceof Object[]) {
            json.append(array2json((Object[]) obj));
        } else if (obj instanceof List) {
            json.append(list2json((List<?>) obj));
        } else if (obj instanceof Map) {
            json.append(map2json((Map<?, ?>) obj));
        } else if (obj instanceof Set) {
            json.append(set2json((Set<?>) obj));
        } else {
            json.append(bean2json(obj));
        }
        CLog.i("json", json.toString());
        return json.toString();
    }

    public static String bean2json(Object bean) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        Field[] fileds = null;
        try {
            fileds = bean.getClass().getDeclaredFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fileds != null) {
            for (int i = 0; i < fileds.length; i++) {
                try {
                    String name = object2json(fileds[i].getName());

                    Method m = (Method) bean.getClass().getMethod(
                            "get" + getMethodName(fileds[i].getName()));
                    String value = object2json(m.invoke(bean));
                    json.append(name);
                    json.append(":");
                    json.append(value);
                    json.append(",");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    public static String list2json(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    public static String array2json(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (Object obj : array) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    public static String map2json(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            for (Object key : map.keySet()) {
                json.append(object2json(key));
                json.append(":");
                json.append(object2json(map.get(key)));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    public static String set2json(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            for (Object obj : set) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    public static String string2json(String s) {
        if (s == null)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                default:
                    if (ch >= '\u0000' && ch <= '\u001F') {
                        String ss = Integer.toHexString(ch);
                        sb.append("\\u");
                        for (int k = 0; k < 4 - ss.length(); k++) {
                            sb.append('0');
                        }
                        sb.append(ss.toUpperCase());
                    } else {
                        sb.append(ch);
                    }
            }
        }
        return sb.toString();
    }
    private static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

}
