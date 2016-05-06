package com.example.sijia.myapplication.TestActivity;

import android.graphics.drawable.RotateDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.sijia.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class TweenAniamtionTestActivity extends AppCompatActivity {

    @Bind(R.id.iv)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_aniamtion_test);
        ButterKnife.bind(this);


    }

    public void rotate(View view) {
        RotateDrawable rotateDrawable= (RotateDrawable) mImageView.getDrawable();
        rotateDrawable.setLevel(1000);
    }
}
