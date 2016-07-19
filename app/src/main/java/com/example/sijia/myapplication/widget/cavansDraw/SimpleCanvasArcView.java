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
public class SimpleCanvasArcView extends View {
    public SimpleCanvasArcView(Context context) {
        super(context);
    }

    public SimpleCanvasArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleCanvasArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        RectF rect=new RectF(0,0,200,200);
        canvas.drawArc(rect,0f,40f,true,paint);
    }
}
