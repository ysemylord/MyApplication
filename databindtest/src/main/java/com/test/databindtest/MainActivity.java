package com.test.databindtest;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.test.databindtest.bean.ObservableUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<ObservableUser> mObservableUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView= (ListView) findViewById(R.id.listView);
        mObservableUsers=new ArrayList<>();
        mObservableUsers.add(new ObservableUser("胥亚波","234"));
        mObservableUsers.add(new ObservableUser("胥亚波2","234"));
        MyAdapter myAdapter=new MyAdapter(mObservableUsers);
        mListView.setAdapter(myAdapter);

    }

}

class MyAdapter extends BaseAdapter{
    private List<ObservableUser> mObservableUsers;

    public MyAdapter(List<ObservableUser> observableUsers) {
        mObservableUsers=observableUsers;
    }

    @Override
    public int getCount() {
        return mObservableUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return mObservableUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.layout_item, parent, false);
        binding.setVariable(com.test.databindtest.BR.user, mObservableUsers.get(position));
        binding.executePendingBindings();
        return binding.getRoot();
    }
}
