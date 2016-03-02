package com.example.sijia.myapplication;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/3/2.
 */
public class MyTest extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

}