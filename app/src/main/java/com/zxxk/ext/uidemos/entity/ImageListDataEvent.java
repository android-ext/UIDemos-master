package com.zxxk.ext.uidemos.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ext on 2016/3/10.
 */
public class ImageListDataEvent implements Serializable {

    private boolean hasMore;
    private ArrayList<ImageListItem> imageList;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public ArrayList<ImageListItem> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<ImageListItem> imageList) {
        this.imageList = imageList;
    }
}
