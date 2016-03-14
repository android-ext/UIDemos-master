package com.zxxk.ext.uidemos.entity;


import java.io.Serializable;

/**
 * Created by Ext on 2016/1/7.
 */
public class User implements Serializable {

    private static final long serialVersionUID = -3514234737360968658L;

    /**
     * 用户ID
     */
    private int userId;
    /**
     * 帐号
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 登陆认证
     */
    private String sessionkey;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
    }


}
