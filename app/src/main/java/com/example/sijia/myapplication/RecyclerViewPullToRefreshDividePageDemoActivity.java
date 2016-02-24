package com.example.sijia.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sijia.myapplication.FormatAdapter.TestSimpleRecyclerViewAdapter;
import com.example.sijia.myapplication.model.Product;
import com.example.sijia.myapplication.util.VolleyQuenueInstence;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class RecyclerViewPullToRefreshDividePageDemoActivity extends Activity {
    PtrClassicFrameLayout mPtrClassicFrameLayout;
    RecyclerView recyclerView;
    private LoadMoreListViewContainer loadMoreListViewContainer;
    private int mNumInPage = 4;
    private int mCurPage = 1;
    List<Product.DatasEntity.GoodsListEntity> mGoodsList;
    private TestSimpleRecyclerViewAdapter adapter;
    //注意:如果想使列表响“应加载更多”事件，则必须在onRefreshBegin和onLoadMore函数中调用loadMoreListViewContainer.loadMoreFinish
    //函数，当讲参数hasMore设为true时才会加载更多数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        mPtrClassicFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.load_more_list_view_ptr_frame);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);

        mGoodsList=new ArrayList<>();
        //mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);

        adapter=new TestSimpleRecyclerViewAdapter(RecyclerViewPullToRefreshDividePageDemoActivity.this,mGoodsList);
        recyclerView.setAdapter(adapter);

        //todo 自定义头部
      /* View viewHeader= LayoutInflater.from(this).inflate(R.layout.layout_custom_ptf_header,null);
        mPtrClassicFrameLayout.setHeaderView(viewHeader);*/

        //refresh
        mPtrClassicFrameLayout.setLoadingMinTime(1000);
        mPtrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, recyclerView, header);
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
       // loadMoreListViewContainer.useDefaultFooter();

        //todo 自定义footer
/*        CustomLoadMoreFooterView footerView = new CustomLoadMoreFooterView(this);
        footerView.setVisibility(View.GONE);
        loadMoreListViewContainer.setLoadMoreView(footerView);
        loadMoreListViewContainer.setLoadMoreUIHandler(footerView);*/


  /*      loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                Log.i("onLoadMore", "onLoadMore");
                queryData();

            }
        });*/
        mPtrClassicFrameLayout.autoRefresh();
    }

    private void queryData() {
        final String goodsUrl = "http://chuanchi.test.kh888.cn//app/index.php?act=goods&op=goods_list&page=" + mNumInPage + "&curpage=" + (mCurPage++) + "&gc_id=258";
        StringRequest goodsRequest = new StringRequest(Request.Method.GET, goodsUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("goodsUrl", response);
                Gson gson=new Gson();
                Product product=gson.fromJson(response, Product.class);
                Product.DatasEntity datasEntity=product.getDatas();
                List<Product.DatasEntity.GoodsListEntity> tempGoodsList=datasEntity.getGoods_list();
                int orignalLength=mGoodsList.size();
                mGoodsList.addAll(tempGoodsList);
                adapter.notifyDataSetChanged();
                mPtrClassicFrameLayout.refreshComplete();
                //loadMoreListViewContainer.loadMoreFinish(mGoodsList.size()==0?true:false, orignalLength==mGoodsList.size()?false:true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("请求错误", error.getMessage());
            }
        });

        VolleyQuenueInstence.getInstance(RecyclerViewPullToRefreshDividePageDemoActivity.this).add(goodsRequest);
    }
}
