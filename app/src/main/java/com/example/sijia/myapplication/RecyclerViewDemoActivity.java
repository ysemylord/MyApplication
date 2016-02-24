package com.example.sijia.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sijia.myapplication.FormatAdapter.SimpleRecyclerViewAdapter2;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerViewDemoActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    XRecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        ButterKnife.bind(this);
        //创建默认的线性LayoutManager
       // LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        //mRecyclerView.setLayoutManager(mLayoutManager);
       // mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
       //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);

        final List<String> mList = new ArrayList<>();
        for(int i=0;i<100;i++){
            mList.add(""+i);
        }
/*        SimpleRecyclerViewAdapter myRecyclerViewAdapter = new SimpleRecyclerViewAdapter<String>(this,mList,R.layout.recycler_item){
            @Override
            public void onBindViewHolder(SimpleViewHolder holder, int position) {
                TextView textView=holder.getView(R.id.text_view);
                textView.setText(mList.get(position));
            }
        };
          mRecyclerView.setAdapter(myRecyclerViewAdapter);*/
        SimpleRecyclerViewAdapter2 myRecyclerViewAdapter2 = new SimpleRecyclerViewAdapter2<String>(this,mList,R.layout.recycler_item){
            @Override
            public void onBindViewHolder(SimpleViewHolder holder, int position) {
                super.onBindViewHolder(holder,position);
                TextView textView=holder.getView(R.id.text_view);
                textView.setText(mList.get(position));
            }
        };

        myRecyclerViewAdapter2.setOnRecyclerViewItemClickListener(new SimpleRecyclerViewAdapter2.OnRecyclerViewItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(RecyclerViewDemoActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(myRecyclerViewAdapter2);



        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.refreshComplete();
                    }
                }, 2 * 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.loadMoreComplete();
                    }
                }, 2 * 1000);
            }
        });
    }
}
