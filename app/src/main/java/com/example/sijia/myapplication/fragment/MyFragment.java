package com.example.sijia.myapplication.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.sijia.myapplication.R;

public class MyFragment extends Fragment {







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout frameLayout= (FrameLayout) inflater.inflate(R.layout.fragment_my, container, false);
       TextView textView= (TextView) frameLayout.findViewById(R.id.textView);
        textView.setText(Math.random()+"");
        return frameLayout;
    }


}
