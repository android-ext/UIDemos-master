package com.zxxk.ext.uidemos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zxxk.ext.uidemos.MyApplication;
import com.zxxk.ext.uidemos.R;
import com.zxxk.ext.uidemos.entity.ImageListItem;

import in.srain.cube.views.adapter.ViewHolderBase;


/**
 * ViewHolder
 * Created by Ext on 2016/3/10.
 */
public class ImageListItemSmallImageViewHolder extends ViewHolderBase<ImageListItem> {

    private ImageView mImageView;

    private ImageListItemSmallImageViewHolder() {

    }

    @Override
    public View createView(LayoutInflater inflater) {
        View v = inflater.inflate(R.layout.load_small_image_list_item, null);
        mImageView = (ImageView) v.findViewById(R.id.load_small_image_list_item_image_view);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return v;
    }

    @Override
    public void showData(int position, ImageListItem itemData) {

        ImageLoader.getInstance().displayImage(itemData.picUrl, mImageView, MyApplication.OPTION);

    }
}
