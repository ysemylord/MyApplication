package com.example.sijia.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sijia.myapplication.util.VolleyQuenueInstence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    List<String> datas = null;
    ArrayAdapter mArrayAdapter = null;
    private LoadMoreListViewContainer loadMoreListViewContainer;
    private int mNumInPage = 4;
    private int mCurPage = 1;

    //注意:如果想使列表响“应加载更多”事件，则必须在onRefreshBegin和onLoadMore函数中调用loadMoreListViewContainer.loadMoreFinish
    //函数，当讲参数hasMore设为true时才会加载更多数据

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
                datas.clear();

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

                try {
                    int originalLength=datas.size();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONObject("datas").getJSONArray("goods_list");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        datas.add(jsonArray.getJSONObject(i).getString("goods_name"));
                    }
                    mArrayAdapter.notifyDataSetChanged();
                    mPtrClassicFrameLayout.refreshComplete();
                    loadMoreListViewContainer.loadMoreFinish(datas.size() == 0 ? true : false, originalLength==datas.size()?false:true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
