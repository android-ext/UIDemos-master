package com.zxxk.ext.uidemos.datamodel;

import android.util.Log;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zxxk.ext.uidemos.entity.ImageListDataEvent;
import com.zxxk.ext.uidemos.entity.ImageListItem;
import com.zxxk.ext.uidemos.exception.NetWorkException;
import com.zxxk.ext.uidemos.utils.ServerDataAccessUtil;
import com.zxxk.ext.uidemos.utils.ServerDataParseUtil;

import org.json.JSONException;

import de.greenrobot.event.EventBus;
import in.srain.cube.views.entity.ListPageInfo;

public class ImageListDataModel extends PagedListDataModel<ImageListItem> {

    private static final String TAG = "ImageListDataModel";

    public ImageListDataModel(int numPerPage) {
        mListPageInfo = new ListPageInfo(numPerPage);
    }

    @Override
    protected void doQueryData() {

        try {
            ServerDataAccessUtil.getImageListData(null, mListPageInfo.getStart(), mListPageInfo.getNumPerPage(),  new StringCallback() {

                @Override
                public void onError(Request request, Exception e) {

                    setRequestFail();
                }

                @Override
                public void onResponse(String response) {

                    try {
                        ImageListDataEvent event = ServerDataParseUtil.getImageListData(response);
                        setRequestResult(event.getImageList(), event.isHasMore());
                        EventBus.getDefault().post(event);
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    setRequestFail();
                }
            });

        } catch (NetWorkException e) {

            Log.i(TAG, "exception");
        }

    }
}
