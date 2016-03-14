package com.zxxk.ext.uidemos.utils;

import android.text.TextUtils;

import com.zxxk.ext.uidemos.entity.ImageListDataEvent;
import com.zxxk.ext.uidemos.entity.ImageListItem;
import com.zxxk.ext.uidemos.entity.JsonData;
import com.zxxk.ext.uidemos.entity.User;
import com.zxxk.ext.uidemos.exception.ResponseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * 响应数据
 */
public class ServerDataParseUtil {


    private static final String TAG = ServerDataParseUtil.class.getName();

    private static final String RESPONSE_STATUS = "statuscode";
    private static final String RESPONSE_ERROR_MESSAGE = "message";
    private static final String RESPONSE_DATA = "result";
    private static final String ERROR_MESSAGWE = "类型转换异常";
    private static final String RESPONSE_OK = "ok";

    /**
     * 访问接口正常，取得响应的数据
     *
     * @return
     * @throws JSONException
     * @throws ResponseException
     */
    public static String getResponseData(String response) throws JSONException,
            ResponseException {

        JSONObject responseJsonObject = JsonUtil.loadJSON(response);

        if (responseJsonObject == null) {
            CLog.d(TAG, "response string may be null or not json format!"
                    + response);
            throw new JSONException(
                    "response string may be null or not json format!");
        }

        CLog.d(TAG, "response: " + response);
        String status;
        try {
            status = responseJsonObject.getString(RESPONSE_STATUS);
        } catch (NumberFormatException e) {

            return ERROR_MESSAGWE;
        }
        if (!status.equals(RESPONSE_OK)) {
            throw new ResponseException(
                    String.valueOf(status),
                    responseJsonObject.getString(RESPONSE_ERROR_MESSAGE));
        }

        return responseJsonObject.getString(RESPONSE_DATA);
    }

    /**
     * 判断数据是否正常
     *
     * @param response 网络数据
     * @return 结果 true 正常 抛一个异常 标示不正常
     * @throws JSONException
     * @throws ResponseException
     */
    public  static boolean isStatusOk(String response)
            throws JSONException, ResponseException {
        if (TextUtils.isEmpty(response)) {
            return false;
        }
        JSONObject jsonObject = JsonUtil.loadJSON(response);
        String status;
        try {
            status = JsonUtil.getString(jsonObject, "statuscode");
        } catch (NumberFormatException e) {

            return false;
        }
        if (status.equals(RESPONSE_OK)) {
            return true;
        } else {
            // {"statuscode":"-1","message":"手机验证错误！"}
            throw new ResponseException(
                    jsonObject.getString(RESPONSE_ERROR_MESSAGE),
                    jsonObject.getString(RESPONSE_ERROR_MESSAGE));
        }
    }

    /**
     * 获取如更新等操作的结果
     *
     * @param responseData
     * @param response
     * @return
     * @throws JSONException
     */
    public static boolean getOperationStatus(String responseData,
                                             String response) throws JSONException {

        if (responseData == null || response == null || "".equals(responseData)
                || "".equals(response)) {
            CLog.d(TAG,
                    "response string or response data is null! responseData:"
                            + responseData + "\tresponse:" + response);
            return false;
        }

        JSONObject jsonObject = JsonUtil.loadJSON(responseData);
        int done = JsonUtil.getInt(jsonObject, "done");

        return done == 1;// 是否操作成功
    }



    /**
     * 解析注册后的数据
     *
     * @param response
     * @return
     * @throws JSONException
     * @throws ResponseException
     */
    public static String getBindingPhoneResult(String response) throws JSONException,
            ResponseException {
        String responseData = getResponseData(response);
        JSONObject jsonObject = JsonUtil.loadJSON(responseData);
        if (jsonObject == null) {
            CLog.d(TAG, "response data to json object is null! response data:"
                    + responseData);
            return null;
        }

        return JsonUtil.getString(jsonObject, "message");
    }

    /**
     * 解析获取图片的数据
     * @param response
     * @return
     * @throws JSONException
     */
    public static ImageListDataEvent getImageListData(String response) throws JSONException {

        JsonData jsonData = JsonData.create(response);
        String data = jsonData.optString("data");
        // data节点对应的JSONObject
        JsonData dataJson = JsonData.create(data);
        String time = dataJson.optString("time");
        // list节点对应的JSONArray
        String list = dataJson.optString("list");
        JSONArray listJson = JsonData.create(list).optArrayOrNew();
        ArrayList<ImageListItem> imageListItemList = new ArrayList();
        ImageListItem imageListItem = null;
        for (int i = 0, len = listJson.length(); i < len; i++) {

            imageListItem = new ImageListItem(JsonData.create(listJson.get(i)));
            imageListItemList.add(imageListItem);
        }

        ImageListDataEvent event = new ImageListDataEvent();
        event.setHasMore(dataJson.optBoolean("has_more"));
        event.setImageList(imageListItemList);

        return event;
    }
}
