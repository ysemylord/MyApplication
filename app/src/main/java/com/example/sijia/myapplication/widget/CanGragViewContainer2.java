package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by sijia on 2015/11/21.
 * 利用GustureListener实现，拖拽
 */


public class CanGragViewContainer2 extends LinearLayout {

    int _xDelta;
    int _yDelta;
    private GestureDetectorCompat mGestureDetectorCompat = null;
    private GestureDetector.SimpleOnGestureListener mSimpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float X = e2.getRawX();
            float Y = e2.getRawY();
            LayoutParams lParams = (LayoutParams) getLayoutParams();
            lParams.leftMargin = (int) (X - _xDelta);
            lParams.topMargin = (int) (Y - _yDelta);
            setLayoutParams(lParams);
            Log.i("e1.x", e1.getRawX() + "");
            Log.i("e2.x", e2.getRawX() + "");
            Log.i("e2.x-e1.x", e2.getRawX() - e1.getRawX() + "");
            Log.i("distanceX", distanceX + "");
            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }


        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }
    };
    private int mActivePointerId;

    public CanGragViewContainer2(Context context) {
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
        Log.i("move",event.getAction()+"");
        if (action == MotionEvent.ACTION_MOVE) {//拦截移动事件
            return true;
        }
        if (action == MotionEvent.ACTION_DOWN) {//记下点击的位置

            mActivePointerId = MotionEventCompat.getPointerId(event, 0);

            int X = (int) event.getRawX();
            int Y = (int) event.getRawY();
            LayoutParams lParams = (LayoutParams) getLayoutParams();
            _xDelta = X - lParams.leftMargin;
            _yDelta = Y - lParams.topMargin;


            Log.i("X", X + "");
            Log.i("event.getRawX()", event.getRawX() + "");
            Log.i("lParams.leftMargin", lParams.leftMargin + "");
            return true;
        }
        if(action==MotionEvent.ACTION_POINTER_DOWN){

        }
        if(action==MotionEvent.ACTION_UP){
           Log.i("up","up");
        }

        if(action==MotionEvent.ACTION_POINTER_UP){

            final int pointerIndex = MotionEventCompat.getActionIndex(event);
            final int pointerId = MotionEventCompat.getPointerId(event, pointerIndex);

            if (pointerId == mActivePointerId) {
                // This was our active pointer going up. Choose a new
                // active pointer and adjust accordingly.
                final int newPointerIndex = pointerIndex == 0 ? 1 : 0;

                int X = (int) event.getRawX();
                int Y = (int) event.getRawY();
                LayoutParams lParams = (LayoutParams) getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                mActivePointerId = MotionEventCompat.getPointerId(event, newPointerIndex);
            }
        }
        return false;
    }



    public CanGragViewContainer2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanGragViewContainer2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        mGestureDetectorCompat = new GestureDetectorCompat(getContext(), mSimpleOnGestureListener);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetectorCompat.onTouchEvent(event);
        return true;
    }


}
