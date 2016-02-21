package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Date;

/**
 * Created by xyb on 2016/2/21.
 */
public class CanvaseDemo2 extends View {
    private final int lineWidth = 30;
    private int mSecondPointerAngle = 0;
    private int mMunitePonterAngle = 0;
    private int mHourPointerAngle=0;

    public CanvaseDemo2(Context context) {
        super(context);
    }

    public CanvaseDemo2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvaseDemo2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
        int startX = getWidth() / 2;
        int startY = getHeight() / 2 - getWidth() / 2;
        paint.setTextSize(28);


        canvas.save();
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(getWidth() / 2, getHeight() / 2 - getWidth() / 2, startX, startY + lineWidth, paint);
            canvas.drawText(i + "", startX - paint.measureText(i + "") / 2, startY + lineWidth + 30, paint);
            canvas.rotate(360 / 12, getWidth() / 2, getHeight() / 2);
        }
        canvas.restore();

        canvas.save();
        paint.setTextSize(20);
        for (int i = 0; i < 60; i++) {
            canvas.drawLine(getWidth() / 2, getHeight() / 2 - getWidth() / 2, startX, startY + lineWidth / 2, paint);
            canvas.rotate(360 / 60, getWidth() / 2, getHeight() / 2);
        }
        canvas.restore();


        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);

        calcuAngle();

        //秒
        canvas.save();
        paint.setColor(Color.BLUE);
        canvas.rotate(mSecondPointerAngle);
        canvas.drawLine(0, 0, 0, -(getWidth()/2-15), paint);
        canvas.restore();

        //分
        canvas.save();
        canvas.rotate(mMunitePonterAngle);
        paint.setColor(Color.RED);
        canvas.drawLine(0, 0, 0, 0 - (getWidth() / 2-20), paint);
        canvas.restore();

        //时
        paint.setColor(Color.BLACK);
        canvas.rotate(mHourPointerAngle);
        canvas.drawLine(0, 0, 0, -(getWidth() / 2-50), paint);



        canvas.restore();
    }

    private void calcuAngle() {
        Date date=new Date();
        int seconde=date.getSeconds();
        int munite=date.getMinutes();
        int hour=date.getHours();
        Log.i("time",hour+":"+munite+":"+seconde);
       /* int secAng=seconde/60*360;
        int munAng=munite/60*360;
        int houAng=hour/24*360;*/
        mSecondPointerAngle=seconde*6;
        mMunitePonterAngle=munite*6;
        mHourPointerAngle=hour*30;
        Log.i("angle",mSecondPointerAngle+" "+mMunitePonterAngle+" "+mHourPointerAngle);

    }


}
