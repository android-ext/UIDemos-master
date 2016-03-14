package com.zxxk.ext.uidemos.eventbus;


/**
 * Created by Ext on 2016/3/11.
 */
public class ErrorMessageDataEvent {

    public String dataTag;
    public String message;

    public ErrorMessageDataEvent(Object failData, String dataTag, String msg) {
        this.dataTag = dataTag;
        this.message = msg;
    }
}
