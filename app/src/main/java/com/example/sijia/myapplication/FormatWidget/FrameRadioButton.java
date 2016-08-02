package com.example.sijia.myapplication.FormatWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

/**
 * Created by  on 2016/7/15.
 * 含有框框的RadioButton
 */
public class FrameRadioButton extends RadioButton {
    float line_width=10f;
    public FrameRadioButton(Context context) {
        super(context);
    }

    public FrameRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FrameRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.isChecked();
        Log.i("checked",isChecked()+"");

        Paint paint=new Paint();
        if(isChecked()) {
            paint.setColor(Color.parseColor("#f2594b"));
        }else{
            paint.setColor(Color.parseColor("#999999"));
        }

        float left=0f;
        int right=canvas.getWidth()-1;
        int bottom=canvas.getHeight()-1;
        float top=0f;
        Log.i("left ,right,top,bottom",left+"-"+right+"-"+bottom+"-"+top);

        //左上
        canvas.drawLine(left,top,left,top+line_width,paint);
        canvas.drawLine(left,top,top+line_width,top,paint);

        //左下
        canvas.drawLine(left,bottom,left,bottom-line_width,paint);
        canvas.drawLine(left,bottom,left+line_width,bottom-0f,paint);

        //右上
        canvas.drawLine(right,top,right-line_width,top,paint);
        canvas.drawLine(right,top,right,top+line_width,paint);

        //右下
        canvas.drawLine(right,bottom,right-line_width,bottom,paint);
        canvas.drawLine(right,bottom,right,bottom-line_width,paint);
    }
}
