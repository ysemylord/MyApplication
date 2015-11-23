package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.LinearLayout;

/**
 * Created by sijia on 2015/11/21.
 */


public class CanGragViewContainer4 extends LinearLayout {

    private ScaleGestureDetector mScaleGestureDetector;
    boolean mScale = false;
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
            Log.i("spanY", spanY + "");
            mScale = true;
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
    private int _xDelta;
    private int _yDelta;

    public CanGragViewContainer4(Context context) {
        super(context);
        init();
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
            _xDelta = X - lParams.leftMargin;
            _yDelta = Y - lParams.topMargin;
            Log.i("X", X + "");
            Log.i("event.getRawX()", event.getRawX() + "");
            Log.i("lParams.leftMargin", lParams.leftMargin + "");
        }
        return false;
    }

    public CanGragViewContainer4(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanGragViewContainer4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        mScaleGestureDetector = new ScaleGestureDetector(getContext(), listener);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {



        int X = (int) event.getRawX();
        int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                LayoutParams layoutParams = (LayoutParams) getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                setLayoutParams(layoutParams);
                return true;
        }
        invalidate();
        return true;
    }
}
