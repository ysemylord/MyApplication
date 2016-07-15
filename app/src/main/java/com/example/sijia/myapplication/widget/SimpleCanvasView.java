package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/15.
 */
public class SimpleCanvasView extends View {
    public SimpleCanvasView(Context context) {
        super(context);
    }

    public SimpleCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        Rect rect=new Rect(0,0,200,200);
        canvas.drawRect(rect,paint);
    }
}
