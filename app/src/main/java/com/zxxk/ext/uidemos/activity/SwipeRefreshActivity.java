package com.zxxk.ext.uidemos.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ViewAnimator;

import com.zxxk.ext.uidemos.R;
import com.zxxk.ext.uidemos.fragment.SwipeRefreshFragment;

public class SwipeRefreshActivity extends BaseActivity {


    public static final String TAG = "MainActivity";

    // Whether the Log Fragment is currently shown
    private boolean mLogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SwipeRefreshFragment fragment = SwipeRefreshFragment.newInstance();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
    }

    /** Create a chain of targets that will receive log data */
    @Override
    public void initializeLogging() {
        Log.i(TAG, "Ready");
    }
}
