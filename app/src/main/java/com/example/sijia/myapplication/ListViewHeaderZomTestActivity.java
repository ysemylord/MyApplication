package com.example.sijia.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ListViewHeaderZomTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_header_zom_test);
        RelativeLayout re= (RelativeLayout) findViewById(R.id.root_view);
        FrameLayout frameLayout=new FrameLayout(this);
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        frameLayout.addView(imageView);
        re.addView(frameLayout);

    }
}
