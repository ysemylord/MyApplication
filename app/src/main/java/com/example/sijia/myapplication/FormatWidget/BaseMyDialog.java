package com.example.sijia.myapplication.FormatWidget;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.sijia.myapplication.util.Util;


/**
 * Created by sijia on 2016/1/2.
 * 继承此基类实现自定义的Dialog
 */
public class BaseMyDialog extends AlertDialog {
    public BaseMyDialog(Context context) {
        super(context);
    }

    public BaseMyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    private boolean mDimEnable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.layout_mydialog);
    }


    /**
     * 设置背景是否变暗
     * @param dimEnable
     */
    public void setDimEnable(boolean dimEnable) {
        mDimEnable = dimEnable;
    }

    /**
     *
     * @param lp
     */
    private void showDim(WindowManager.LayoutParams lp) {
        float alpha = (mDimEnable == true ?  lp.dimAmount : 0f);
        lp.dimAmount = alpha;
    }

    /**
     * 只能设置垂直方向的Gravity
     * 水平方向的Gravity设置无效
     */
    public void showInBottom() {
        show();
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = Util.getWindowWidth(getContext());
        showDim(lp);
        getWindow().setAttributes(lp);
    }

    public void showInTop() {
        show();
        Window window = getWindow();
        window.setGravity(Gravity.TOP);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = Util.getWindowWidth(getContext());
        showDim(lp);
        getWindow().setAttributes(lp);
    }

    public void showInCenter() {
        show();
        Window window = getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        WindowManager.LayoutParams lp = window.getAttributes();
        showDim(lp);
        getWindow().setAttributes(lp);

    }

}
