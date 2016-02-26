package com.example.sijia.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sijia.myapplication.FormatAdapter.TestSimpleListViewBaseAdapter;
import com.example.sijia.myapplication.model.Product;
import com.example.sijia.myapplication.util.VolleyQuenueInstence;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class PullToRefreshDividePageDemoActivity extends Activity {
    PtrClassicFrameLayout mPtrClassicFrameLayout;
    ListView mListView;
    private LoadMoreListViewContainer loadMoreListViewContainer;
    private int mNumInPage = 4;
    private int mCurPage = 1;
    List<Product.DatasEntity.GoodsListEntity> mGoodsList;
    private TestSimpleListViewBaseAdapter adapter;
    //注意:如果想使列表响“应加载更多”事件，则必须在onRefreshBegin和onLoadMore函数中调用loadMoreListViewContainer.loadMoreFinish
    //函数，当讲参数hasMore设为true时才会加载更多数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_demo);
        mPtrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.load_more_list_view_ptr_frame);
        mListView = (ListView) findViewById(R.id.load_more_small_image_list_view);
        mGoodsList=new ArrayList<>();
        //mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);
        adapter=new TestSimpleListViewBaseAdapter(PullToRefreshDividePageDemoActivity.this,mGoodsList);
        mListView.setAdapter(adapter);

        //todo 自定义头部
      /* View viewHeader= LayoutInflater.from(this).inflate(R.layout.layout_custom_ptf_header,null);
        mPtrClassicFrameLayout.setHeaderView(viewHeader);*/

        //refresh
        mPtrClassicFrameLayout.setLoadingMinTime(1000);
        mPtrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mGoodsList.clear();
                mCurPage=1;
                queryData();
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
                queryData();

            }
        });
        mPtrClassicFrameLayout.autoRefresh();
    }

    private void queryData() {
        final String goodsUrl = "http://chuanchi.test.kh888.cn//app/index.php?act=goods&op=goods_list&page=" + mNumInPage + "&curpage=" + (mCurPage++) + "&gc_id=258";
        StringRequest goodsRequest = new StringRequest(Request.Method.GET, goodsUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("goodsUrl", response);
                Logger.init("json_format");
                Logger.json(response);
                Gson gson=new Gson();
                Product product=gson.fromJson(response, Product.class);
                Product.DatasEntity datasEntity=product.getDatas();
                List<Product.DatasEntity.GoodsListEntity> tempGoodsList=datasEntity.getGoods_list();
                int orignalLength=mGoodsList.size();
                mGoodsList.addAll(tempGoodsList);
                adapter.notifyDataSetChanged();
                mPtrClassicFrameLayout.refreshComplete();
                loadMoreListViewContainer.loadMoreFinish(mGoodsList.size()==0?true:false, orignalLength==mGoodsList.size()?false:true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("请求错误", error.getMessage());
            }
        });

        VolleyQuenueInstence.getInstance(PullToRefreshDividePageDemoActivity.this).add(goodsRequest);
    }
}
