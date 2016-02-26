package com.example.sijia.myapplication.fragment.WidgetUse;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sijia.myapplication.R;

import java.io.Serializable;


public class SeekBarFragment extends Fragment implements Serializable{




    public SeekBarFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seek_bar, container, false);
    }







}
