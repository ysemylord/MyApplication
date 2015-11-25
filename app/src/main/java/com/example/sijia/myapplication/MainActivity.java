package com.example.sijia.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll= (LinearLayout) findViewById(R.id.j1_container);
        LinearLayout lli= (LinearLayout) View.inflate(this, R.layout.item, null);
        ll.addView(lli);
    }
}
