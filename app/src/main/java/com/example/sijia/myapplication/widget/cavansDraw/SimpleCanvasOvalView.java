package com.example.sijia.myapplication.widget.cavansDraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/15.
 */
public class SimpleCanvasOvalView extends View {
    public SimpleCanvasOvalView(Context context) {
        super(context);
    }

    public SimpleCanvasOvalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleCanvasOvalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        RectF rect=new RectF(0,0,500,200);
        canvas.drawOval(rect,paint);//画的是矩形的内切椭圆,如果矩形是正方形，那么椭圆就是圆
    }
}
