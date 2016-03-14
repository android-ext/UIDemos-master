package com.zxxk.ext.uidemos.exception;

/**
 * 网络异常时，抛出此异常
 *
 * @author shitao
 */
public class NetWorkException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 5960351707182935183L;


    /**
     * 错误信息
     */
    private String errorMessage = "无法连接到服务器，请检查网络！";


    /**
     * 直接调用出错提示就可以了
     *
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

}
