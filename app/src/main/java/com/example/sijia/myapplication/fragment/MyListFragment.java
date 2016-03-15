package com.example.sijia.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sijia.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuyaf on 2016/3/15.
 */
public class MyListFragment extends BaseFragment {
    List<String> mListItems;
    ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_item_list, container, false);
        mListView = (ListView) frameLayout.findViewById(R.id.list);
        mListItems = new ArrayList<>();
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mListItems));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemName = (String) parent.getAdapter().getItem(position);
                try {
                    Class clazz=Class.forName(itemName);
                    DialogFragment dialogFragments= (DialogFragment) clazz.newInstance();
                    dialogFragments.show(getFragmentManager(),itemName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                Log.i("itemName", itemName);
            }
        });
        return frameLayout;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected void addItem(String itemName) {
        mListItems.add(itemName);
        mListView.deferNotifyDataSetChanged();
    }
}

