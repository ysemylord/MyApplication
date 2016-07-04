package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/6/27.
 */
public class ReboundScrollView extends ScrollView {
    String TAG="";
    float mStartY;
    Rect originRect=new Rect();
    public ReboundScrollView(Context context) {
        super(context);
        TAG=getClass().getSimpleName();
    }

    public ReboundScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TAG=getClass().getSimpleName();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.i(TAG+"flow","onFinishInflate");
        //记录下
        originRect.left=getLeft();
        originRect.top=getTop();
        originRect.right=getRight();
        originRect.bottom=getBottom();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG+"flow","onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG+"flow","onLayout");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG+"flow","onDraw");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action= event.getAction();
        Log.i(TAG+"--action",action+"");
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mStartY=  event.getY();
                Log.i(TAG+"MotionEvent","ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                float nowY=event.getY();
                float delatY=nowY-mStartY;
                Log.i(TAG+"MotionEvent--delatY",delatY+"");
                Log.i(TAG+"MotionEvent","ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG+"MotionEvent","ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);

    }
}
