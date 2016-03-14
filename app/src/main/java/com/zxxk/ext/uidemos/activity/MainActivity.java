package com.zxxk.ext.uidemos.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zxxk.ext.uidemos.R;
import com.zxxk.ext.uidemos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.mainNavDrawerBtn.setOnClickListener(this);
        binding.mainSwipeRefreshBtn.setOnClickListener(this);
        binding.mainPtrRefreshBtn.setOnClickListener(this);
    }

    /**
     * @description: 切换到导航抽屉页面
     * @author: Ext
     * @time: 2016/3/10 10:29
     */
    private void gotoPage(Class clazz) {

        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.main_nav_drawer_btn:
                gotoPage(NavDrawerActivity.class);
                break;

            case R.id.main_swipe_refresh_btn:
                gotoPage(SwipeRefreshActivity.class);
                break;

            case R.id.main_ptr_refresh_btn:
                gotoPage(PtrRefreshActivity.class);
                break;
            default:
                break;
        }
    }
}
