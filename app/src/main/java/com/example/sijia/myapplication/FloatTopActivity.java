package com.example.sijia.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * ListView的头部可固定悬浮
 */
public class FloatTopActivity extends Activity {
    ListView mListView;
    TextView mFloatHeaderTv1;
    private TextView mFloatHeaderTv2;
    private TextView mFloatHeaderTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticked_top);
        mListView = (ListView) findViewById(R.id.lv);
        mFloatHeaderTv= (TextView) findViewById(R.id.float_header_tv);
        mFloatHeaderTv1= (TextView) LayoutInflater.from(this).inflate(R.layout.header_view1,mListView,false);

        mFloatHeaderTv2= (TextView) LayoutInflater.from(this).inflate(R.layout.header_view2, mListView, false);

        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{
                "1", "1", "固定", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2",
                "1", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2"
        }));

        mListView.addHeaderView(mFloatHeaderTv1);
        mListView.addHeaderView(mFloatHeaderTv2);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.i("firstVisibleItem",firstVisibleItem+"");
                if(firstVisibleItem>0){
                    mFloatHeaderTv.setVisibility(View.VISIBLE);
                    Log.i("可见","可见");
                }else{
                    mFloatHeaderTv.setVisibility(View.GONE);
                    Log.i("不可见", "不可见");
                }
            }
        });

    }
}
