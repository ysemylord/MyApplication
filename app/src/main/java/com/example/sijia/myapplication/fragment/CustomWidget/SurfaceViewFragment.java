package com.example.sijia.myapplication.fragment.CustomWidget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sijia.myapplication.fragment.BaseFragment;
import com.example.sijia.myapplication.widget.MySurfaceView;

/**
 * Created by Administrator on 2016/3/2.
 */
public class SurfaceViewFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {/*
        View view=View.inflate(container.getContext(), R.layout.fragment_surfaceview_layout,null);
        return view;*/
        return new MySurfaceView(getActivity());
    }
}
