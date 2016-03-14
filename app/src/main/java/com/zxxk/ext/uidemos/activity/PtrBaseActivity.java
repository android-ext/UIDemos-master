package com.zxxk.ext.uidemos.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.zxxk.ext.uidemos.R;
import com.zxxk.ext.uidemos.views.TitleHeaderBar;


public abstract class PtrBaseActivity extends AppCompatActivity {

    protected TitleHeaderBar mTitleHeaderBar;
    protected LinearLayout mContentContainer;
    private LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        //无title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //全屏
//        getWindow().setFlags(WindowMan
// ager.LayoutParams. FLAG_FULLSCREEN, WindowManager.LayoutParams. FLAG_FULLSCREEN);

        inflater = LayoutInflater.from(this);

        // 根布局
        ViewGroup view = (ViewGroup) inflater.inflate(getFrameLayoutId(), null);
        // 容器布局
        LinearLayout contentContainer = (LinearLayout) view.findViewById(R.id.cube_mints_content_frame_content);
        // 标题布局
        mTitleHeaderBar = (TitleHeaderBar) view.findViewById(R.id.cube_mints_content_frame_title_header);
        if (enableDefaultBack()) {
            mTitleHeaderBar.setLeftOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } else {
            mTitleHeaderBar.getLeftViewContainer().setVisibility(View.INVISIBLE);
        }

        // 内容布局
        mContentContainer = contentContainer;
        View contentView = createView(inflater, view, savedInstanceState);
        contentView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        contentContainer.addView(contentView);

        setContentView(view);
    }

    protected int getFrameLayoutId() {
        return R.layout.cube_mints_base_content_frame_with_title_header;
    }

    protected abstract View createView(LayoutInflater inflater, ViewGroup view, Bundle savedInstanceState);

    protected abstract boolean enableDefaultBack();

    protected void setHeaderTitle(int id) {
        mTitleHeaderBar.getTitleTextView().setText(id);
    }

    protected void setHeaderTitle(String title) {
        mTitleHeaderBar.getTitleTextView().setText(title);
    }

    public TitleHeaderBar getTitleHeaderBar() {
        return mTitleHeaderBar;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
