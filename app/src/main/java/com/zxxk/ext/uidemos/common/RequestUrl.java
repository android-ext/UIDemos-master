package com.zxxk.ext.uidemos.common;

/**
 * 服务请求URL管理类
 */
public class RequestUrl {

    public final static String DOMAIN = "http://kky.zxxk.com";

    /**
     * 获取验证码
     */
    public static String GET_VER_CODE = DOMAIN + "/user/verification.do";

    /**
     * 注册
     */
    public static String REGISTER = DOMAIN + "/user/phone_register.do";


    /**
     * 登陆
     */
    public static String LOGIN = DOMAIN + "/user/login.do";

    /** 图片路径 */
    public static final String API_IMAGE_LIST = "http://www.android-cube-app-server.liaohuqiu.net/api/image-list.php";

}