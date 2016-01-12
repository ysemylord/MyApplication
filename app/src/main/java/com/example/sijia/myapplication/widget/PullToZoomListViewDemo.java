package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by xu on 2016/1/12.
 */
public class PullToZoomListViewDemo extends ListView {
    private FrameLayout mHeaderContainer;
    private int mHeaderContainerHeight;
    private ImageView mImageView;
    private Context mContext;
    public PullToZoomListViewDemo(Context context) {
        super(context);
        init();
    }

    public PullToZoomListViewDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mHeaderContainer=new FrameLayout(mContext);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
