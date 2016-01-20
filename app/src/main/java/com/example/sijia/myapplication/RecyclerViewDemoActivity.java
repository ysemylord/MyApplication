package com.example.sijia.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.example.sijia.myapplication.adapter.SimpleRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerViewDemoActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        ButterKnife.bind(this);
        //创建默认的线性LayoutManager
       // LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        //recyclerRecyclerView.setLayoutManager(mLayoutManager);
       // recyclerRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
       //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerRecyclerView.setHasFixedSize(true);

        final List<String> mList = new ArrayList<>();
        for(int i=0;i<100;i++){
            mList.add(""+i);
        }
        SimpleRecyclerViewAdapter myRecyclerViewAdapter = new SimpleRecyclerViewAdapter<String>(this,mList,R.layout.recycler_item){
            @Override
            public void onBindViewHolder(SimpleViewHolder holder, int position) {
                TextView textView=holder.getView(R.id.text_view);
                textView.setText(mList.get(position));
            }
        };
          recyclerRecyclerView.setAdapter(myRecyclerViewAdapter);
        }
}
