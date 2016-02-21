package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xyb on 2016/2/21.
 */
public class CanvaseDemo extends View {
    private final int lineWidth = 30;
    private int mSecondPointerAngle = 0;
    private int mMunitePonterAngle = 0;

    public CanvaseDemo(Context context) {
        super(context);
    }

    public CanvaseDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvaseDemo(Context context, AttributeSet attrs, int defStyleAttr) {
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
        paint.setTextSize(16);
        canvas.save();
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(getWidth() / 2, getHeight() / 2 - getWidth() / 2, startX, startY + lineWidth, paint);
            canvas.drawText(i + "", startX - paint.measureText(i + "") / 2, startY + lineWidth + 30, paint);
            canvas.rotate(360 / 12, getWidth() / 2, getHeight() / 2);
        }
        canvas.restore();

        canvas.save();

        canvas.translate(getWidth() / 2, getHeight() / 2);

        canvas.save();
        paint.setColor(Color.BLUE);
        mSecondPointerAngle += 6;
        canvas.rotate(mSecondPointerAngle);
        canvas.drawLine(0, 0, 0,-100, paint);
        canvas.restore();

        canvas.save();
        if (mSecondPointerAngle==360) {
            mMunitePonterAngle += 6;
            mSecondPointerAngle=0;
        }
        canvas.rotate(mMunitePonterAngle);
        paint.setColor(Color.RED);
        canvas.drawLine(0, 0, 50, 50, paint);
        canvas.restore();

/*        paint.setColor(Color.BLACK);
        canvas.rotate(360 / 12);
        canvas.drawLine(0, 0, 50, 50, paint);

        paint.setColor(Color.YELLOW);
        canvas.rotate(360 / 12);
        canvas.drawLine(0, 0, 30, 30, paint);*/

        canvas.restore();
    }


}
