package com.zxxk.ext.uidemos.exception;

/**
 * 响应数据含error状态，抛出此异常
 * @author chenwenbiao
 *
 */
public class ResponseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -496296277635057011L;

	/**
	 * 错误码
	 */
	private String errorCode = null;
	
	/**
	 * 错误信息
	 */
	private String errorMessage = null;
	
	public ResponseException(String errorCode, String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
