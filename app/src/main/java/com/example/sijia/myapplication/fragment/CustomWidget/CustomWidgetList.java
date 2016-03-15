package com.example.sijia.myapplication.fragment.CustomWidget;

import android.os.Bundle;

import com.example.sijia.myapplication.fragment.MyListFragment;

/**
 * Created by xuyaf on 2016/3/15.
 */
public class CustomWidgetList extends MyListFragment{
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addItem((new RoundImageFragment()).getClass().getName());
        addItem((new SurfaceViewFragment()).getClass().getName());
        addItem((new TransferMatrixDialogFragment()).getClass().getName());
    }
}
