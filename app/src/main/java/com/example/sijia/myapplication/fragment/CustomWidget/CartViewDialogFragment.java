package com.example.sijia.myapplication.fragment.CustomWidget;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.fragment.BaseDialogFragment;
import com.personal.assistlibrary.customwidget.CartAnimationView;

/**
 * Created by xuyaf on 2016/3/15.
 */
public class CartViewDialogFragment extends BaseDialogFragment {

    Handler mHandler=new Handler();
    @Override
    protected int getResourse() {
        return R.layout.fragment_cartview;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().findViewById(R.id.ani_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartAnimationView cartAnimationView= (CartAnimationView) getView().findViewById(R.id.imageView);
                cartAnimationView.setView(getView().findViewById(R.id.to_view));
               // cartAnimationView.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.teset));
                cartAnimationView.setBitmap(getView().findViewById(R.id.iv_c));
                cartAnimationView.startAnimation();
                cartAnimationView.setAnimationListener(new CartAnimationView.AnimationListener(){

                    @Override
                    public void onAnimationEnd() {

                    }
                });

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
