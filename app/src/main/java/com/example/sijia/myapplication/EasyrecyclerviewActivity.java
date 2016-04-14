package com.example.sijia.myapplication;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sijia.myapplication.FormatAdapter.EasyrecyclerviewAdapter;
import com.example.sijia.myapplication.model.Person;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EasyrecyclerviewActivity extends AppCompatActivity {

    private EasyRecyclerView mRecyclerView;
    private EasyrecyclerviewAdapter easyrecyclerviewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easyrecyclerview);
        ButterKnife.bind(this);

        mRecyclerView=(EasyRecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        easyrecyclerviewAdapter = new EasyrecyclerviewAdapter(this);
        mRecyclerView.setAdapterWithProgress(easyrecyclerviewAdapter);
        SwipeRefreshLayout.OnRefreshListener refreshListener;
        mRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        easyrecyclerviewAdapter.add(new Person("下村花"));
                        easyrecyclerviewAdapter.add(new Person("大村花"));
                        easyrecyclerviewAdapter.add(new Person("下村花"));
                        easyrecyclerviewAdapter.add(new Person("大村花"));
                        easyrecyclerviewAdapter.add(new Person("下村花"));
                        easyrecyclerviewAdapter.add(new Person("大村花"));
                        easyrecyclerviewAdapter.add(new Person("下村花"));
                        easyrecyclerviewAdapter.add(new Person("大村花"));
                        mRecyclerView.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        easyrecyclerviewAdapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {


            @Override
            public void onLoadMore() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        easyrecyclerviewAdapter.addAll((new com.example.sijia.myapplication.model.Person("下村花")));
                    }
                }, 1000);
            }
        });

    }
}
