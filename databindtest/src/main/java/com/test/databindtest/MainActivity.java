package com.test.databindtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
       /* ActivityMainBinding activityMainBinding= DataBindingUtil.setContentView(
                this, R.layout.activity_main);
        User user=new User("胥亚波","22岁");
        activityMainBinding.setUser(user);*/
    }
}
