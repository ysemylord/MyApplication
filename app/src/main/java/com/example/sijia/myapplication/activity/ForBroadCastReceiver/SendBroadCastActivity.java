package com.example.sijia.myapplication.activity.ForBroadCastReceiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.sijia.myapplication.Contant.action_constant;
import com.example.sijia.myapplication.R;

public class SendBroadCastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broad_cast);
    }



    public void sendBroadCast(View view) {
        Intent intent=new Intent(action_constant.ACTION_TEST);
        sendBroadcast(intent);
    }
}
