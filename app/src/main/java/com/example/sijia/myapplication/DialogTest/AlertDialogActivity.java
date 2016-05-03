package com.example.sijia.myapplication.DialogTest;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.sijia.myapplication.R;

public class AlertDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
    }


    public void showDialog(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("内容");
        dialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final int button1 = this.getResources().getIdentifier("button1", "id", "android");
        Button negativeButton = (Button) alertDialog.findViewById(button1);
        negativeButton.setVisibility(View.VISIBLE);
        negativeButton.setTextColor(Color.BLUE);
        final int button2 = getResources().getIdentifier("button2", "id", "android");
        Button positiveButton = (Button) alertDialog.findViewById(button2);
        positiveButton.setVisibility(View.VISIBLE);


        LinearLayout.LayoutParams llLayoutParams1 = (LinearLayout.LayoutParams) negativeButton.getLayoutParams();
        llLayoutParams1.width = 0;
        llLayoutParams1.weight = 1;
        positiveButton.setLayoutParams(llLayoutParams1);

        LinearLayout.LayoutParams llLayoutParams = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
        llLayoutParams.width = 0;
        llLayoutParams.weight = 1;
        positiveButton.setLayoutParams(llLayoutParams);

        ViewGroup viewGroup = (ViewGroup) positiveButton.getParent();
        int count=viewGroup.getChildCount();
        for (int i=0;i<count;i++){
            View view1=viewGroup.getChildAt(i);
            Log.i("chideType",view.getClass().getSimpleName());
        }


    }
}
