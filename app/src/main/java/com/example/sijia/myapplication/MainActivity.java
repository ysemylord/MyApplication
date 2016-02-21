package com.example.sijia.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void jumpToWifiS(View view) {
        Intent intent=new Intent(this,WifiActivity.class);
        startActivity(intent);
    }
}
