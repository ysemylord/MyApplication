package com.example.sijia.myapplication.fragment.CustomWidget;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.fragment.BaseDialogFragment;
import com.personal.assistlibrary.customwidget.CartAnimationView;

/**
 * Created by xuyaf on 2016/3/15.
 */
public class CartViewDialogFragment extends BaseDialogFragment {


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
                cartAnimationView.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.teset));
                cartAnimationView.startAnimation();
                cartAnimationView.setAnimationListener(new CartAnimationView.AnimationListener(){

                    @Override
                    public void onAnimationEnd() {

                    }
                });

            }
        });

    }
}
