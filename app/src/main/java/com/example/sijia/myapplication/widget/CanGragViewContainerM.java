package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by sijia on 2015/11/21.
 * //利用最基本的手势事件实现
 */


public class CanGragViewContainerM extends LinearLayout {


    private float mLastTouchX;
    private float mLastTouchY;
    private float mPosX;
    private float mPosY;

    public CanGragViewContainerM(Context context) {
        super(context);
        init();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    public CanGragViewContainerM(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanGragViewContainerM(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {

    }


    // The ‘active pointer’ is the one currently moving our object.
    final int INVALID_POINTER_ID = -1000;
    private int mActivePointerId = INVALID_POINTER_ID;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        //mScaleDetector.onTouchEvent(ev);

        final int action = MotionEventCompat.getActionMasked(ev);
        int cds[] = new int[2];
        getLocationOnScreen(cds);

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
                final float x = MotionEventCompat.getX(ev, pointerIndex)-cds[0];
                final float y = MotionEventCompat.getY(ev, pointerIndex)-cds[1];
             /*   final float x = ev.getRawX();
                final float y = ev.getRawY();*/
                // Remember where we started (for dragging)
                mLastTouchX = x;
                mLastTouchY = y;
                mPosX = x;
                mPosY = y;
                // Save the ID of this pointer (for dragging)
                mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                Log.i("downx", x + "");
                Log.i("downy", y + "");
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                // Find the index of the active pointer and fetch its position
                final int pointerIndex =
                        MotionEventCompat.findPointerIndex(ev, mActivePointerId);
                final float x = MotionEventCompat.getX(ev, pointerIndex)-cds[0];
                final float y = MotionEventCompat.getY(ev, pointerIndex)-cds[1];

                final int pointerIndex2 = MotionEventCompat.getActionIndex(ev);
                int id = MotionEventCompat.getPointerId(ev, pointerIndex2);


             /*   final float x = ev.getRawX();
                final float y = ev.getRawY();*/
                // Calculate the distance moved
                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;
                setX(getX() + dx);
                setY(getY() + dy);
                Log.i("pso_x", x + "");
                Log.i("y", y + "");
                Log.i("disX", dx + "");
                Log.i("disY", dy + "");
                mPosX += dx;
                mPosY += dy;

                // invalidate();

                // Remember this touch position for the next move event
                mLastTouchX = x;
                mLastTouchY = y;

                break;
            }

            case MotionEvent.ACTION_UP: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {

                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
                final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);

                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;

                    mLastTouchX = MotionEventCompat.getX(ev, newPointerIndex)-cds[0];
                    mLastTouchY = MotionEventCompat.getY(ev, newPointerIndex)-cds[1];
                    mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
                }
                break;
            }
        }
        return true;
    }
}
