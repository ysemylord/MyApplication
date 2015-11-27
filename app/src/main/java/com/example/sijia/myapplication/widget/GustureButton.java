package com.example.sijia.myapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by sijia on 2015/11/21.
 */
public class GustureButton extends Button {
    private float deltaX;
    private float deltaY;

    public GustureButton(Context context) {
        super(context);
    }

    public GustureButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        final String xmlns="http://schemas.android.com/apk/res/android";
        String height = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_height");
       int mHeight = attrs.getAttributeIntValue(xmlns, "layout_height", 0);
        int layout_marginBottom = attrs.getAttributeIntValue(xmlns, "layout_marginBottom", 0);
        int mMaxEms=attrs.getAttributeIntValue(xmlns,"maxEms",0);
        int maxHeight=attrs.getAttributeIntValue(xmlns,"maxHeight",0);
        TypedArray typedArray= context.obtainStyledAttributes(new int[]{android.R.attr.layout_width});
        int widht= typedArray.getInt(android.R.attr.layout_width,0);

        init();

    }

    private void init() {

    }


    public GustureButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




}
