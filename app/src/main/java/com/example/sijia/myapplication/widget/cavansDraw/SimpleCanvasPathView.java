package com.example.sijia.myapplication.widget.cavansDraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/15.
 */
public class SimpleCanvasPathView extends View {
    public SimpleCanvasPathView(Context context) {
        super(context);
    }

    public SimpleCanvasPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleCanvasPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        Path path=new Path();
        path.moveTo(20,40);
        path.lineTo(34f,56f);
    }
}
