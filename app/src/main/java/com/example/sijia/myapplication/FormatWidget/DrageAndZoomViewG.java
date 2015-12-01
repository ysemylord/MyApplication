package com.example.sijia.myapplication.FormatWidget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * http://blog.csdn.net/k1585853768/article/details/50062287
 * 可拖拽和缩放内容并有回弹效果的LinearLayout
 * 当子View拖动过多，弹回
 *用法:此容器有且只有各一个子View,且子View的高和款一样大
 * 要实现自view同容器移动容小放大，请使用Layout_weight
 * Created by xyb on 2015/11/25.
 */
public class DrageAndZoomViewG extends LinearLayout {
    private ScaleGestureDetector mScaleDetector;
    private float mLastTouchX;
    private float mLastTouchY;
    private Scroller mScroller;

    public DrageAndZoomViewG(Context context) {
        super(context);
    }

    public DrageAndZoomViewG(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    // The ‘active pointer’ is the one currently moving our object.
    private int INVALID_POINTER_ID = -1000;
    private int mActivePointerId = INVALID_POINTER_ID;


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        //为了使手指按在Button等可点击的控件上任可以滑动，需要拦截滑动实践
        //并且为了使坐标准确，在此处记录按下的点
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_DOWN:
                final int pointerIndex = MotionEventCompat.getActionIndex(event);
                mActivePointerId = MotionEventCompat.getPointerId(event, pointerIndex);
                final float x = MotionEventCompat.getX(event, pointerIndex);
                final float y = MotionEventCompat.getY(event, pointerIndex);

                // Remember where we started (for dragging)
                mLastTouchX = x;
                mLastTouchY = y;


                // Save the ID of this pointer (for dragging)
                mActivePointerId = MotionEventCompat.getPointerId(event, 0);
                mActivePointerId = MotionEventCompat.getPointerId(event, 0);
                return false;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        // mScaleDetector.onTouchEvent(ev);
        mScaleDetector.onTouchEvent(ev);
        Log.i("scroll", getScrollX() + " ");
        final int action = MotionEventCompat.getActionMasked(ev);

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                // Find the index of the active pointer and fetch its position
                final int pointerIndex =
                        MotionEventCompat.findPointerIndex(ev, mActivePointerId);
                final float x = MotionEventCompat.getX(ev, pointerIndex);
                final float y = MotionEventCompat.getY(ev, pointerIndex);

                // Calculate the distance moved
                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;
                if (getChildCount() != 1) {
                    Log.e("错误", "只能有一个视图");
                    return true;
                }
                //  获取子视图
                View view = getChildAt(0);
                //获取屏幕宽度
              /*  int windowHeight = Util.getWindowHeight(getContext());
                int windowWidth = Util.getWindowWidth(getContext());*/
                int OFFSETX=view.getWidth()-20;//水平方向最大偏移量
                int OFFSETY=view.getHeight()-20;//竖直方向最大偏移量
                //获取本容器高度
                if (view.getWidth() >= getWidth() && view.getHeight() >= getHeight()) {//view比容器大
                    Log.i("Bottom position", view.getBottom() + " ");
                    if (getScrollX() < 0 && Math.abs(getScrollX()) >= OFFSETX) {//左边拉得太多
                        mScroller.startScroll(getScrollX(), getScrollY(), -getScrollX(), -getScrollY());
                        invalidate();
                        return true;
                    }
                    if (getScrollX() > 0 && Math.abs(getScrollX())  >= OFFSETX) {//右边拉得太多
                        mScroller.startScroll(getScrollX(), getScrollY(),  - getScrollX(), -getScrollY());
                        Log.i("回拉","右边拉得太多");
                        invalidate();
                        return true;
                    }

                    if ((getScrollY() < 0 && Math.abs(getScrollY()) >= OFFSETY)) {//上边拉得太多
                        mScroller.startScroll(getScrollX(), getScrollY(), 0, -getScrollY());
                        invalidate();
                        return true;
                    }

                    if (getScrollY() > 0 && Math.abs(getScrollY())  >= OFFSETY) {//下边拉得太多
                        mScroller.startScroll(getScrollX(), getScrollY(), 0, -getScrollY() );
                    }
                } else {
                    mScroller.startScroll(getScrollX(), getScrollY(), -getScrollX(), -getScrollY());//滚回原位
                    invalidate();
                    return true;
                }
                Log.i("ScrollByX",-dx+" ");
                scrollBy((int) (-dx), (int) (-dy));

                invalidate();
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
                    mLastTouchX = MotionEventCompat.getX(ev, newPointerIndex);
                    mLastTouchY = MotionEventCompat.getY(ev, newPointerIndex);
                    mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
                }
                break;
            }
        }
        return true;
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float spanX = mScaleDetector.getCurrentSpanX() / mScaleDetector.getPreviousSpanX();
            float spanY = mScaleDetector.getCurrentSpanY() / mScaleDetector.getPreviousSpanY();
            LayoutParams lParams = (LayoutParams) getLayoutParams();
            float zoomValue = Math.min(spanX, spanY);
            float newWidth = lParams.width * zoomValue;
            float newHeight = lParams.height * zoomValue;

            lParams.width = (int) newWidth;
            lParams.height = (int) newHeight;
            setLayoutParams(lParams);





            return true;


        }
    }

}
