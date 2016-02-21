package com.example.sijia.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sijia.myapplication.widget.CanvaseDemo;
import com.example.sijia.myapplication.widget.CanvaseDemo2;

import static com.example.sijia.myapplication.R.layout.activity_paint_canvas;

public class PaintCanvasActivity extends AppCompatActivity {
    CanvaseDemo2 canvaseDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_paint_canvas);
        canvaseDemo= (CanvaseDemo2) findViewById(R.id.canvase);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    canvaseDemo.postInvalidate();
                }
            }
        }).start();
    }
}
