package com.example.sijia.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView im;
    }


    public void bigger(View view) {
        TextView tv= (TextView) findViewById(R.id.text);
        LinearLayout.LayoutParams ll= (LinearLayout.LayoutParams) tv.getLayoutParams();
        ll.width=ll.width+20;
        ll.height=ll.height+20;
        tv.setLayoutParams(ll);
        tv.requestLayout();
    }

    public void smaller(View view) {
        TextView tv= (TextView) findViewById(R.id.text);
        LinearLayout.LayoutParams ll= (LinearLayout.LayoutParams) tv.getLayoutParams();
        ll.width=ll.width-20;
        ll.height=ll.height-20;
        tv.setLayoutParams(ll);
        Window phoneWindow= getWindow();
    }
}
