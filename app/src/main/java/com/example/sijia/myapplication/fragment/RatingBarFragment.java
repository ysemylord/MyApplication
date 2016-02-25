package com.example.sijia.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sijia.myapplication.R;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RatingBarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RatingBarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RatingBarFragment extends Fragment implements Serializable{




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating_bar, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}
