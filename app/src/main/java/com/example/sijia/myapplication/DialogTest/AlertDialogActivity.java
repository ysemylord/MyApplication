package com.example.sijia.myapplication.DialogTest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.sijia.myapplication.R;

public class AlertDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
    }


    public void showDialog(View view) {
        MyProgressDialog myProgressDialog = new MyProgressDialog(this);
        myProgressDialog.setMessage("fdfd");
        myProgressDialog.show();
    }

    class MyProgressDialog extends ProgressDialog {
        public MyProgressDialog(Context context) {
            super(context);
        }

        public MyProgressDialog(Context context, int themeResId) {
            super(context, themeResId);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.layout_dialog_progress);
        }

        @Override
        public void setMessage(CharSequence message) {
        }

        @Override
        public void show() {
            super.show();
        }
    }

}
