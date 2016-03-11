package com.example.sijia.myapplication.activity.ForBroadCastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.sijia.myapplication.Contant.action_constant;
import com.example.sijia.myapplication.R;

public class BroadcastReceiverDemoActivity extends AppCompatActivity {
    MyBroadCastReceiver myBroadCastReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver_demo);
        myBroadCastReceiver = new MyBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter(action_constant.ACTION_TEST);
        registerReceiver(myBroadCastReceiver, intentFilter);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadCastReceiver);
    }

    public void skip(View view) {
        Intent intent=new Intent(this,SendBroadCastActivity.class);
        startActivity(intent);
    }

    class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(action_constant.ACTION_TEST)) {//因为注册广播的时候已经设置过滤器，这里的判断是多余的，不过使逻辑更严谨可以加上
                Toast.makeText(BroadcastReceiverDemoActivity.this,"收到广播",1000).show();
            }
        }
    }


}
