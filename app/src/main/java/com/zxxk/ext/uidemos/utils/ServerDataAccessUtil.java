package com.zxxk.ext.uidemos.utils;

import android.content.Context;
import android.util.Log;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zxxk.ext.uidemos.MyApplication;
import com.zxxk.ext.uidemos.common.Constant;
import com.zxxk.ext.uidemos.common.RequestUrl;
import com.zxxk.ext.uidemos.exception.NetWorkException;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by Ext on 2015/12/17.
 * OKHttp的参考链接：
 * http://blog.csdn.net/lmj623565791/article/details/49734867
 * https://github.com/hongyangAndroid/okhttp-utils
 */
public class ServerDataAccessUtil {

    private static String TAG = "ServerDataAccessUtil";

    public static void goAccess(Context context, String url, Map<String, String> params, int type,
                                StringCallback callback) throws NetWorkException {

//        if (!NetUtil.checkNet(MyApplication.getInstance())) {
//            throw new NetWorkException();
//        }

        params.put("phoneType", "android");
        // 除了登录或注册,其他请求都必须带上sessionKey供服务器做验证
//        User user = MyApplication.getUser();
//        if (user != null)
//            params.put("session_key", user.getSessionkey());
        CLog.d(TAG, url + "?" + params);
        if (type == Constant.ACCESS_TYPE_GET) {
            OkHttpUtils.get().tag(context).url(url).params(params).build().execute(callback);
        } else {
            OkHttpUtils.post().tag(context).url(url).params(params).build().execute(callback);
        }
    }

    /**
     * OkHttpUtils.get().tag(context).url(url).params(params).build().execute(callback);
     *
     * OkHttpUtils OKHttp请求工具类：封装了OKHttp请求客户端对象属性和线程调度Handler对象属性
     *
     * OkHttpRequestBuilder 请求类参数构建类：构建 OkHttpRequest类对象的请求url、参数、头部信息、取消标识
     *
     * OkHttpRequest 请求类：封装了请求所需的参数，能构建RequestCall对象
     *
     * RequestCall 请求发起类：当调用RequestCall#execute其实是调用的是OkHttpUtils#execute
     *
     * 最终封装的请求参数还是会传入到框架内部去
     *
     * OkHttpUtils.get() 实例化一个GetBuilder(请求参数构建)对象
     * tag(context).url(url).params(params) 实例化GetBuilder(请求参数构建)对象的请求参数属性
     * build() 先构建一个Get请求对象 再构建一个RequestCall请求调用实例对象
     * execute() 执行OkHttpUtils类的execute方法将请求调用对象入队列
     *
     */


    /**
     * 获取验证码
     *
     * @param phoneNumber
     * @param type
     * @param callback
     */
    public static void getVerificationCode(Context context, String phoneNumber, String type,
                                           StringCallback callback) throws NetWorkException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", phoneNumber);
        params.put("type", type);
        goAccess(context, RequestUrl.GET_VER_CODE, params, Constant.ACCESS_TYPE_GET, callback);

    }

    /**
     * 用户密码加密
     *
     * @param password
     * @return
     */
    public static String encryptionPassword(String password) {
        return MD5StringUtil.MD5("zxxk" + password + "yanwu");
    }


    /**
     * 用户注册
     *
     * @param nickName
     * @param phone
     * @param password
     * @param verificationCode
     * @param callback
     */
    public static void register(Context context, String nickName, String phone, String password,
                                String verificationCode, StringCallback callback) throws NetWorkException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("nick_name", nickName);
        params.put("phone", phone);
        params.put("password", encryptionPassword(password));
        params.put("verification", verificationCode);
        goAccess(context, RequestUrl.REGISTER, params, Constant.ACCESS_TYPE_GET, callback);

    }


    /**
     * 用户登录
     *
     * @param account
     * @param password
     * @param type     type=1表示手机号注册的用户，2表示邮箱注册的用户
     * @param callback
     */
    public static void login(Context context, String account, String password, String type, StringCallback callback) throws NetWorkException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("account", account);
        params.put("password", encryptionPassword(password));
        params.put("type", type);
        Log.i("TAG", encryptionPassword(password));
        goAccess(context, RequestUrl.LOGIN, params, Constant.ACCESS_TYPE_GET, callback);
    }

    /**
     * @description: 获取图片列表数据
     * @author: Ext
     * @time: 2016/3/11 8:27
     */
    public static void getImageListData(Context context, int start, int num, StringCallback callback) throws NetWorkException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("start", String.valueOf(start));
        params.put("num", String.valueOf(num));
        goAccess(context, RequestUrl.API_IMAGE_LIST, params, Constant.ACCESS_TYPE_GET, callback);
    }

}
