package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.sijia.myapplication.R;

/**
 * Created by xuyaf on 2016/3/13.
 */
public class RoundImage extends ImageView {
    public RoundImage(Context context) {
        super(context);
    }

    public RoundImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
