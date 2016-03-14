package com.zxxk.ext.uidemos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zxxk.ext.uidemos.R;
import com.zxxk.ext.uidemos.adapter.ImageListItemSmallImageViewHolder;
import com.zxxk.ext.uidemos.adapter.PagedListViewDataAdapter;
import com.zxxk.ext.uidemos.datamodel.ImageListDataModel;
import com.zxxk.ext.uidemos.entity.ImageListDataEvent;
import com.zxxk.ext.uidemos.entity.ImageListItem;
import com.zxxk.ext.uidemos.eventbus.ErrorMessageDataEvent;
import com.zxxk.ext.uidemos.utils.LocalDisplay;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class PtrRefreshActivity extends PtrBaseActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "LoadMoreListViewFragment";
    private PagedListViewDataAdapter<ImageListItem> mAdapter;
    private ImageListDataModel mDataModel;

    private PtrFrameLayout mPtrFrameLayout;
    private ListView mListView;

    private View mRootView;
    private LoadMoreListViewContainer loadMoreListViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        initView();
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup view, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_ptr_refresh, null);
        return mRootView;
    }

    @Override
    protected boolean enableDefaultBack() {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    private void initView() {

        // set up data
        mDataModel = new ImageListDataModel(5);

        /**
         * 适配器：ListViewDataAdapterBase拥有2个ViewHolder创建者属性
         * setViewHolderClass(): 实例化一个延迟加载的LazyViewHolderCreator创建者; params(外围实例对象， ViewHolder类类型， 加载图片的对象)
         * LazyViewHolderCreator#create() --> new LazyViewHolderCreator(constructor, instanceObjects);
         *      params(参数0: public com.zxxk.ext.uidemos.adapter.ImageListItemSmallImageViewHolder(in.srain.cube.image.ImageLoader) 一个ViewHolder类的构造方法签名: 参数1：参数0方法签名的入参)
         */
        mAdapter = new PagedListViewDataAdapter();
        mAdapter.setViewHolderClass(this, ImageListItemSmallImageViewHolder.class);
        mAdapter.setListPageInfo(mDataModel.getListPageInfo());


        // pull to refresh
        mPtrFrameLayout = (PtrFrameLayout) mRootView.findViewById(R.id.load_more_list_view_ptr_frame);

        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                in.srain.cube.util.CLog.i(TAG, "checkCanDoRefresh");
                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
            }

            /**
             * PtrFrameLayout#autoRefresh()  --> ScrollChecker#tryToScrollTo() --> 1003行调用ScrollChecker#run()
             *
             * --> finish() --> onPtrScrollFinish() --> onRelease() --> tryToPerformRefresh()
             *
             * --> PtrFrameLayout#performRefresh()
             *
             * PtrFrameLayout#  406 --> 517 --> 437 --> 531
             *
             * ScrollChecker#run()#movePos()->updatePos() ->
             */

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                in.srain.cube.util.CLog.i(TAG, "onRefreshBegin-1");
                mDataModel.queryFirstPage();
            }
        });

        // list view
        mListView = (ListView) mRootView.findViewById(R.id.load_more_small_image_list_view);

        mListView.setOnItemClickListener(this);

        // header place holder
        View headerMarginView = new View(this);
        headerMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LocalDisplay.dp2px(20)));
        mListView.addHeaderView(headerMarginView);

        // load more container
        loadMoreListViewContainer = (LoadMoreListViewContainer) mRootView.findViewById(R.id.load_more_list_view_container);
        loadMoreListViewContainer.useDefaultHeader();

        // binding view and data
        mListView.setAdapter(mAdapter);
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                mDataModel.queryNextPage();
            }
        });


        // auto load data
        mPtrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrameLayout.autoRefresh(false);
                in.srain.cube.util.CLog.i(TAG, "postDelayed-0");
            }
        }, 150);
    }


    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEvent(ImageListDataEvent event) {

        in.srain.cube.util.CLog.i(TAG, "onEvent success-2");
        // ptr
        mPtrFrameLayout.refreshComplete();

        // load more
        loadMoreListViewContainer.loadMoreFinish(mDataModel.getListPageInfo().isEmpty(), mDataModel.getListPageInfo().hasMore());

        mAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEvent(ErrorMessageDataEvent event) {
        in.srain.cube.util.CLog.i(TAG, "onEvent error");
        loadMoreListViewContainer.loadMoreError(0, event.message);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, ItemDetailActivity.class);
        startActivity(intent);
    }
}
