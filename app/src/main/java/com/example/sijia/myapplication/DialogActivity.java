package com.example.sijia.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.sijia.myapplication.FormatWidget.BaseMyDialog;


public class DialogActivity extends Activity {
    BaseMyDialog myDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    public void showDialog(View view) {
        myDialog = new BaseMyDialog(this);
        myDialog.show();
    }
}
