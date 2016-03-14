package com.zxxk.ext.uidemos.entity;

import java.io.Serializable;


/**
 * Created by Ext on 2016/3/10.
 */
public class ImageListItem implements Serializable{


    public String picUrl;

    public ImageListItem(JsonData jsonData) {
        picUrl = jsonData.optString("pic");
    }
}
