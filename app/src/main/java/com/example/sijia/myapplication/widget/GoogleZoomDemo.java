package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.LinearLayout;

/**
 * Created by xyb on 2015/11/25.
 */
public class GoogleZoomDemo extends LinearLayout {

    private ScaleGestureDetector mScaleDetector;
    int originalHeight;
    int originalWidth;

    public GoogleZoomDemo(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());

    }

    public GoogleZoomDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleDetector.onTouchEvent(event);
        return true;
    }


    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
/*
            float spanX = mScaleDetector.getCurrentSpanX() - mScaleDetector.getPreviousSpanX();
            float spanY = mScaleDetector.getCurrentSpanY() - mScaleDetector.getPreviousSpanY();
            LayoutParams lParams = (LayoutParams) getLayoutParams();
            float zoomValue = Math.min(spanX, spanY);
            float newWidth = lParams.width + zoomValue;
            float newHeight = lParams.height + zoomValue;

            lParams.width = (int) newWidth;
            lParams.height = (int) newHeight;
            setLayoutParams(lParams);
*/
            float spanX = mScaleDetector.getCurrentSpanX() /mScaleDetector.getPreviousSpanX();
            float spanY = mScaleDetector.getCurrentSpanY() / mScaleDetector.getPreviousSpanY();
            LayoutParams lParams = (LayoutParams) getLayoutParams();
            float zoomValue = Math.min(spanX, spanY);
            float newWidth = lParams.width *zoomValue;
            float newHeight = lParams.height*zoomValue;

            lParams.width = (int) newWidth;
            lParams.height = (int) newHeight;
            setLayoutParams(lParams);



/*            for(int i=0;i<getChildCount();i++) {
                View view =  getChildAt(i);
                float zoomValue = Math.min(spanX, spanY);
                LayoutParams lParams = (LayoutParams) view.getLayoutParams();
                float newWidth = lParams.width + zoomValue;
                float newHeight = lParams.height + zoomValue;
                if (newWidth < 100 || newHeight > 600)
                    return true;
                lParams.width = (int) newWidth;
                lParams.height = (int) newHeight;
                view.setLayoutParams(lParams);
            }*/

            Log.i("spanX", spanX + "");
            Log.i("spanY", spanY + "");
            return true;


        }
    }

}
