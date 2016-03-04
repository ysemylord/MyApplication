package com.example.sijia.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.io.Serializable;


public class BaseFragment extends Fragment implements Serializable{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("FragmentStart",getClass().getSimpleName());
    }

}
