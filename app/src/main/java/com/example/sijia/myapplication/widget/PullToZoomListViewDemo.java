package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.util.Util;

/**
 * Created by xu on 2016/1/12.
 */
public class PullToZoomListViewDemo extends ListView {
    private FrameLayout mHeaderContainer;
    private int mHeaderContainerHeight;
    private ImageView mImageView;
    private Context mContext;
    private float mLastY;
    private int mActivePointerId;
    private String TAG;

    public PullToZoomListViewDemo(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public PullToZoomListViewDemo(Context context, AttributeSet attrs) {

        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        TAG = getClass().getSimpleName();
        mHeaderContainer = new FrameLayout(mContext);
        mImageView = new ImageView(mContext);
        mImageView.setImageResource((R.mipmap.splash01));
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mHeaderContainer.addView(mImageView);
     //   mHeaderContainerHeight=mHeaderContainer.getLayoutParams().height;
        setHeaderSize();
        addHeaderView(mHeaderContainer);

    }

    private void setHeaderSize() {
        int paramInt1= Util.getWindowWidth(mContext);
        int paramInt2=Util.getWindowWidth(mContext)*9/16;

        Object localObject = this.mHeaderContainer.getLayoutParams();
        if (localObject == null)
            localObject = new AbsListView.LayoutParams(paramInt1, paramInt2);
        ((ViewGroup.LayoutParams) localObject).width = paramInt1;
        ((ViewGroup.LayoutParams) localObject).height = paramInt2;
        this.mHeaderContainer
                .setLayoutParams((ViewGroup.LayoutParams) localObject);
        this.mHeaderContainerHeight = paramInt2;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
             /*    int pointerIndex = MotionEventCompat.getActionIndex(ev);
                mActivePointerId = MotionEventCompat.getPointerId(ev, pointerIndex);*/
                mLastY = ev.getY();
                Log.i(TAG, mLastY + "");
                break;
            case MotionEvent.ACTION_MOVE:
               /* final int pointerIndex1 = MotionEventCompat.findPointerIndex(ev, mActivePointerId);
                float moveY = MotionEventCompat.getY(ev, pointerIndex1);*/
                float moveY=ev.getY();
                ViewGroup.LayoutParams layoutParams=mHeaderContainer.getLayoutParams();
                Log.i(TAG+"  before", layoutParams.height+"");
                Log.i(TAG+" mHeaderContainerHeight", mHeaderContainerHeight+"");
                Log.i(TAG+" moveY-mLastY", moveY-mLastY+"");
                layoutParams.height=((int) ( mHeaderContainerHeight+(moveY-mLastY)/2)>0)? (int) (mHeaderContainerHeight+(moveY-mLastY)/2):0;
                if(layoutParams.height<=0){
                   removeHeaderView(mHeaderContainer);
                }else{
                   /* if(getHeaderViewsCount()<=0)
                    addHeaderView(mHeaderContainer);*/
                }
                Log.i(TAG+"after", layoutParams.height+"");
                mHeaderContainer.setLayoutParams(layoutParams);
                break;
            case MotionEvent.ACTION_UP:
                ViewGroup.LayoutParams layoutParams2=mHeaderContainer.getLayoutParams();
                layoutParams2.height= (int)  mHeaderContainerHeight;
                mHeaderContainer.setLayoutParams(layoutParams2);
                break;
        }
        return true;
    }
}
