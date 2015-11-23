package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by sijia on 2015/11/21.
 */
public class GustureButton extends Button {
    private float deltaX;
    private float deltaY;

    public GustureButton(Context context) {
        super(context);
    }

    public GustureButton(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    public GustureButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float downPosX;
    float downPosY;
    float oriX;
    float oriY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);
        String DEBUG_TAG = "touch Event";


        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                deltaX = event.getX();
                Log.i("downPoterX", deltaX + "");
            case (MotionEvent.ACTION_MOVE):
                float nowX = event.getX();
                float xd = nowX - deltaX;

                setTranslationX(nowX - deltaX);

                Log.i("moveX rawX", nowX + " "+event.getRawX());
                Log.i("distanceX", xd + "");


                return true;
            case (MotionEvent.ACTION_UP):
                Log.i(DEBUG_TAG, "Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL):
                Log.i(DEBUG_TAG, "Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                Log.i(DEBUG_TAG, "Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            case (MotionEvent.ACTION_POINTER_DOWN):
                Log.i(DEBUG_TAG, "ACTION_POINTER_DOWN");
                break;
            case (MotionEvent.ACTION_POINTER_UP):
                Log.i(DEBUG_TAG, "ACTION_POINTER_UP");
                break;
            default:
                return super.onTouchEvent(event);
        }
        return false;
    }


}
