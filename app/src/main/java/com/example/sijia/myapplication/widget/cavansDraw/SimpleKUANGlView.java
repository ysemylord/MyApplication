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
public class SimpleKUANGlView extends View {
    public SimpleKUANGlView(Context context) {
        super(context);
    }

    public SimpleKUANGlView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleKUANGlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);

        Path path = new Path();
        path.moveTo(80, 200);// 此点为多边形的起点
        path.lineTo(120, 250);
        path.lineTo(80, 250);
        canvas.drawLine(0f,0f,0f,50f,paint);
        canvas.drawLine(0f,0f,50f,0f,paint);

    }
}
