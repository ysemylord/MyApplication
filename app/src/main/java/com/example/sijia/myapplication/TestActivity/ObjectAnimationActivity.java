package com.example.sijia.myapplication.TestActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.util.Util;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ObjectAnimationActivity extends AppCompatActivity {

    @Bind(R.id.button5)
    Button button5;
    @Bind(R.id.textView9)
    TextView textView9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);
        ButterKnife.bind(this);
    }

    public void doSome(View view) {
         float unitMove=Util.getWindowWidth(this)/3f;
        ObjectAnimator animator = ObjectAnimator.ofFloat(textView9, "translationX", textView9.getTranslationX(), textView9.getTranslationX()+unitMove);
        animator.setDuration(1000);
        animator.start();
    }
}
