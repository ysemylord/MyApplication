package com.example.sijia.myapplication.fragment.AnimationFragment;

import android.os.Bundle;

import com.example.sijia.myapplication.fragment.MyListFragment;

/**
 * Created by Administrator on 2016/4/5.
 */
public class AnimationFragmenntList extends MyListFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addItem((new AnimationTest()).getClass().getName());
    }
}
