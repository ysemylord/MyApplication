package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.LinearLayout;

/**
 * Created by sijia on 2015/11/21.
 * 利用ScaleGustureDetector实现缩放
 */


public class CanGragViewContainer3 extends LinearLayout {
    private ScaleGestureDetector mScaleGestureDetector;
    ScaleGestureDetector.OnScaleGestureListener listener = new ScaleGestureDetector.OnScaleGestureListener() {

        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float spanX = scaleGestureDetector.getCurrentSpanX() - scaleGestureDetector.getPreviousSpanX();
            float spanY = scaleGestureDetector.getCurrentSpanY() - scaleGestureDetector.getPreviousSpanY();
            LayoutParams lParams = (LayoutParams) getLayoutParams();
            float newWidth = lParams.width + spanX;
            float newHeight = lParams.height + spanY;
            lParams.width = (int) newWidth;
            lParams.height = (int) newHeight;
            setLayoutParams(lParams);
            Log.i("spanX", spanX + "");
            Log.i("spanY",spanY+"");
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    };
    private int original_x;
    private int original_y;

    public CanGragViewContainer3(Context context) {
        super(context);
        init();
    }


    public CanGragViewContainer3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanGragViewContainer3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        mScaleGestureDetector = new ScaleGestureDetector(getContext(), listener);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        if (action == MotionEvent.ACTION_MOVE) {//拦截移动事件
            return true;
        }
        if (action == MotionEvent.ACTION_DOWN) {//记下点击的位置
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            LayoutParams lParams = (LayoutParams) getLayoutParams();
            original_x = X - lParams.leftMargin;
            original_y = Y - lParams.topMargin;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }


}
