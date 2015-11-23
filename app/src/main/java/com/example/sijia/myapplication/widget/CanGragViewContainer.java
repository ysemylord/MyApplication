package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.LinearLayout;

/**
 * Created by sijia on 2015/11/21.
 * //利用最基本的手势事件实现
 */


public class CanGragViewContainer extends LinearLayout {
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



    private int _xDelta;
    private int _yDelta;
    private int mActivePointerId;

    public CanGragViewContainer(Context context) {
        super(context);
        init();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        int action=event.getAction()&MotionEvent.ACTION_MASK;
        if(action==MotionEvent.ACTION_MOVE) {//拦截移动事件
            return true;
        }
        if(action==MotionEvent.ACTION_DOWN){//记下点击的位置
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            LayoutParams lParams = (LayoutParams) getLayoutParams();
            _xDelta = X - lParams.leftMargin;
            _yDelta = Y - lParams.topMargin;
            Log.i("X",X+"");
            Log.i("event.getRawX()",event.getRawX()+"");
            Log.i("lParams.leftMargin",lParams.leftMargin+"");
            mActivePointerId = MotionEventCompat.getPointerId(event, 0);
        }
        return false;
    }
    public CanGragViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanGragViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init(){
        mScaleGestureDetector = new ScaleGestureDetector(getContext(), listener);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        mScaleGestureDetector.onTouchEvent(event);
         int X = (int) event.getRawX();
         int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                Log.i("move","up");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.i("move","ACTION_POINTER_UP");
                final int pointerIndex = MotionEventCompat.getActionIndex(event);
                final int pointerId = MotionEventCompat.getPointerId(event, pointerIndex);

                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;

                    float mLastTouchX = MotionEventCompat.getX(event, newPointerIndex);
                    float mLastTouchY = MotionEventCompat.getY(event, newPointerIndex);
                    int[] location = new int[2];
                    getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];

                    int X1 = (int) (mLastTouchX+x);
                    int Y1 = (int) (mLastTouchY+y);
                    LayoutParams lParams = (LayoutParams) getLayoutParams();
                    _xDelta = X1 - lParams.leftMargin;
                    _yDelta = Y1 - lParams.topMargin;
                    mActivePointerId = MotionEventCompat.getPointerId(event, newPointerIndex);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                LayoutParams layoutParams = (LayoutParams) getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                setLayoutParams(layoutParams);
                break;
        }
        invalidate();
        return true;
    }
}
