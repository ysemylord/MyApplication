package com.example.sijia.myapplication.FormatWidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.sijia.myapplication.R;

/**
 * Created by xuyaf on 2016/3/13.
 */
public class guaguale extends View {
    Path mPath;
    Canvas mCanvas;
    Paint mPathPaint;
    private Bitmap mFgBitmap;
    private Bitmap mBgBitmap;

    public guaguale(Context context) {
        super(context);
        init();
    }


    public guaguale(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public guaguale(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //注意:这里mFgBitmap为dst,绘制的路径为src,以dst_in模式位置路径，则显示的为位置出的路径，但是路径是透明
    //的，所以显示出了下面的mBgBitmap 

    private void init() {
        mPath = new Path();
        int width = 800, height = 800;


        mBgBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.teset);

        mFgBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mFgBitmap);
        mCanvas.drawColor(Color.GRAY);

        mPathPaint = new Paint();
        mPathPaint.setStyle(Paint.Style.STROKE);//设置画笔的风格为描边，不然画path的时候空白的部分也会填充
        mPathPaint.setStrokeWidth(20);
        mPathPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));//显示重合部分的dst（即路径部分）
        mPathPaint.setAlpha(0);
    }

    /**
     * 重载onTouchEvent,捕获触屏事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int ation = MotionEventCompat.getActionMasked(event);
        switch (ation) {
            case MotionEvent.ACTION_DOWN://按下时，重置path，并将画笔移动到按下的点
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE://连接上个点与现在指定的点
                mPath.lineTo(event.getX(), event.getY());
                break;
        }
        mCanvas.drawPath(mPath, mPathPaint);//用mFgPaint画手势路径
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        canvas.drawBitmap(mBgBitmap,0,0,null);
        canvas.drawBitmap(mFgBitmap,0,0,null);

    }
}
