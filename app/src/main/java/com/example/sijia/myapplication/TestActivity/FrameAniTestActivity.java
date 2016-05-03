package com.example.sijia.myapplication.TestActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sijia.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FrameAniTestActivity extends AppCompatActivity {

    /**
     * 幀動畫
     */
    @Bind(R.id.im_ani)
    ImageView mImAni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_ani_test);
        ButterKnife.bind(this);
        mImAni.getX();
        mImAni.getLeft();

    }


    public void start(View view) {
        ((AnimationDrawable)mImAni.getDrawable()).start();
    }

    public void stop(View view) {
        ((AnimationDrawable)mImAni.getDrawable()).stop();
        AnimationDrawable animationDrawable1= (AnimationDrawable) mImAni.getDrawable();
        AnimationDrawable animationDrawable2= (AnimationDrawable) mImAni.getDrawable();
        if(animationDrawable1.equals(animationDrawable2)){
            Log.i("FrameAniTestActivity","相等");
        }else{
            Log.i("FrameAniTestActivity","不相等");
        }
    }

    public void add(View view) {
        LinearLayout mContainer= (LinearLayout) findViewById(R.id.container);
        LinearLayout container=new LinearLayout(this);
        for(int i=0;i<20;i++) {
            ImageView imageView = (ImageView) LayoutInflater.from(this).inflate(R.layout.frame_image_view, container, false);
            container.addView(imageView);
        }
        mContainer.addView(container);
    }


}
