package com.example.sijia.myapplication.fragment.CustomWidget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sijia.myapplication.fragment.BaseFragment;
import com.example.sijia.myapplication.widget.MySurfaceView;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/2.
 */
public class SurfaceViewFragment extends DialogFragment implements Serializable{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {/*
        View view=View.inflate(container.getContext(), R.layout.fragment_surfaceview_layout,null);
        return view;*/
        return new MySurfaceView(getActivity());
    }
}
