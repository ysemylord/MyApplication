package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by xyb on 2016/3/2.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mSurfaceHolder;
    private DrawThread thread;

    public MySurfaceView(Context context) {
        super(context);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        thread = new DrawThread();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        thread = new DrawThread();
    }

//SurfaceHolder.Callback

    /**
     * SurfaceView创建时激发
     *
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.isRun = true;
        thread.start();
    }

    /**
     * SurfaceView大小改变时激发
     *
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * SurfaceView摧毁时激发
     *
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.isRun = false;
    }
    //end SurfaceHolder.Callback

    class DrawThread extends Thread {


        boolean isRun = true;

        public DrawThread() {

        }


        @Override
        public void run() {
            int count = 0;
            while (isRun) {
                Canvas c = null;
                try {
                    synchronized (mSurfaceHolder) {


                        c = mSurfaceHolder.lockCanvas();//锁定画布，并返回画布,有时返回的画布为空，所以要catch异常，不然会崩溃
                        Paint paint = new Paint();
                        paint.setColor(Color.RED);
                        Rect r = new Rect(100, 50, 300, 250);
                        c.drawRect(r, paint);
                        paint.setColor(Color.BLUE);
                        c.drawText("" + count, 100, 100, paint);
                        Thread.sleep(1000);
                        count++;

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("error", Log.getStackTraceString(e.getCause()));
                } finally {
                    if (c != null) {
                       mSurfaceHolder.unlockCanvasAndPost(c);//结束锁定画图，并提交改变。
                    }
                }

            }
        }

/*        @Override
        public void run() {
            int count = 0;
            while (isRun) {
                Canvas c = null;
                try {
                    synchronized (mSurfaceHolder) {
                        c = mSurfaceHolder.lockCanvas();//锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
                        c.drawColor(Color.BLACK);//设置画布背景颜色
                        Paint p = new Paint(); //创建画笔
                        p.setColor(Color.WHITE);
                        Rect r = new Rect(100, 50, 300, 250);
                        c.drawRect(r, p);
                        p.setColor(Color.BLUE);
                        c.drawText("这是第" + (count++) + "秒", 100, 310, p);
                        Thread.sleep(1000);//睡眠时间为1秒
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                    if (c != null)
                    {
                        mSurfaceHolder.unlockCanvasAndPost(c);//结束锁定画图，并提交改变。
                    }
                }
            }
        }*/
    }
}
