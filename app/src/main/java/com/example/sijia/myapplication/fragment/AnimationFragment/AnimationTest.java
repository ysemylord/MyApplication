package com.example.sijia.myapplication.fragment.AnimationFragment;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.fragment.BaseDialogFragment;


public class AnimationTest extends BaseDialogFragment {

    ImageView mImageView;

    public AnimationTest() {
        // Required empty public constructor
    }


    @Override
    protected int getResourse() {
        return R.layout.fragment_animation_test;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImageView= (ImageView) getView().findViewById(R.id.test_img);
        getView().findViewById(R.id.btn_translate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  TranslateAnimation translateAnimation= (TranslateAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.translate_demo);
                TranslateAnimation translateAnimation=new TranslateAnimation(0,200,0,200);
                translateAnimation.setDuration(2000);
                mImageView.startAnimation(translateAnimation);
            }
        });


        getView().findViewById(R.id.btn_scale).setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                ScaleAnimation scaleAnimation=null;
                scaleAnimation= (ScaleAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.scale_demo);

                mImageView.startAnimation(scaleAnimation);
            }
        });

        getView().findViewById(R.id.btn_rotate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateAnimation rotateAnimation=null;
                rotateAnimation= (RotateAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.roate_demo);

                mImageView.startAnimation(rotateAnimation);
            }
        });

        getView().findViewById(R.id.btn_alpha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation alphaAnimation=null;
                alphaAnimation= (AlphaAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_demo);

                mImageView.startAnimation(alphaAnimation);
            }
        });
    }
}
