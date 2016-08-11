package com.example.sijia.myapplication.FormatWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ScrollViewCanWrapperScrollView extends ScrollView {

    /**
     */
    public ScrollView parentScrollView;

    public ScrollViewCanWrapperScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }




    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return false;

    }


}