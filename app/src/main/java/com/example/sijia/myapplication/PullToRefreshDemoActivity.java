package com.example.sijia.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class PullToRefreshDemoActivity extends Activity {
    PtrClassicFrameLayout mPtrClassicFrameLayout;
    ListView mListView;
    List<String> datas = null;
    ArrayAdapter mArrayAdapter = null;
    private LoadMoreListViewContainer mLoadMoreListViewContainer;
    private LoadMoreListViewContainer loadMoreListViewContainer;

    //注意:如果想使列表响“应加载更多”事件，则必须在onRefreshBegin和onLoadMore函数中调用loadMoreListViewContainer.loadMoreFinish
    /**
         LoadMoreDefaultFooterView#LoadMoreDefaultFooterView
         当empty为true表示总的数据量为0
         当讲参数hasMore设为true时才会加载更多数据
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_demo);
        mPtrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.load_more_list_view_ptr_frame);
        mListView = (ListView) findViewById(R.id.load_more_small_image_list_view);
        datas = new ArrayList<>();


        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);
        mListView.setAdapter(mArrayAdapter);

        //todo 自定义头部
      /* View viewHeader= LayoutInflater.from(this).inflate(R.layout.layout_custom_ptf_header,null);
        mPtrClassicFrameLayout.setHeaderView(viewHeader);*/

        mPtrClassicFrameLayout.setLoadingMinTime(1000);
        mPtrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Log.i("onRefreshBegin", "onRefreshBegin");
                datas.clear();
                for (int i = 0; i < 2; i++) {
                    datas.add(Math.random() + "");
                }
                mArrayAdapter.notifyDataSetChanged();
                mPtrClassicFrameLayout.refreshComplete();
                loadMoreListViewContainer.loadMoreFinish(false, true);//第二个参数hasMore决定上拉时是否加载更多。haseMore为false，则上拉时不会调用onLoadMore去加载更多数据
            }
        });


        // load more container
        loadMoreListViewContainer = (LoadMoreListViewContainer) findViewById(R.id.load_more_list_view_container);
        loadMoreListViewContainer.useDefaultFooter();

       //todo 自定义footer
/*        CustomLoadMoreFooterView footerView = new CustomLoadMoreFooterView(this);
        footerView.setVisibility(View.GONE);
        loadMoreListViewContainer.setLoadMoreView(footerView);
        loadMoreListViewContainer.setLoadMoreUIHandler(footerView);*/


        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                Log.i("onLoadMore", "onLoadMore");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 2; i++) {
                            datas.add(Math.random() + "");
                        }
                        mArrayAdapter.notifyDataSetChanged();
                        loadMoreListViewContainer.loadMoreFinish(datas.size()==0?true:false, true);
                    }
                }, 1000);
            }
        });
        mPtrClassicFrameLayout.autoRefresh();
    }
}
