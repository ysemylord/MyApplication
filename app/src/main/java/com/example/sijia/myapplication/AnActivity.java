package com.example.sijia.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AnActivity extends AppCompatActivity {

    @Bind(R.id.textView2)
    Button textView2;
    @Bind(R.id.imageView2)
    ImageView imageView2;
    private RotateAnimation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an);
        ButterKnife.bind(this);
        animation = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(250);
        animation.setFillEnabled(true);
        animation.setFillAfter(true);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView2.startAnimation(animation);
                imageView2.setImageResource(R.drawable.ic_pulltorefresh_arrow);
               // imageView2.clearAnimation();
            }
        });
    }


}
