package com.example.sijia.myapplication.CustomViewTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.sijia.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScrollTestActivity extends AppCompatActivity {

    @Bind(R.id.container_ll)
    LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_test);
        ButterKnife.bind(this);
    }


    public void getPadding(View view) {
        Log.i("padding l t r b",mContainer.getPaddingLeft()+"--"+mContainer.getPaddingTop()+
                "--"+mContainer.getPaddingRight()+"--"+mContainer.getPaddingBottom());

    }
}
