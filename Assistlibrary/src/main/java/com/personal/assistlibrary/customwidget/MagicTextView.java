package com.personal.assistlibrary.customwidget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/3/28.
 */
public class MagicTextView extends TextView {
    private float mCurValue=0f;
    private float mFinValue;
    private float mRate=0f;
    private int mAR=1;//1为加，-1为减
    final private int DELAY_TIME=50;
    private int mTotalTime=500;//整个过程完成需要的时间

    //矫正显示的值
    DecimalFormat fnum = new DecimalFormat("0");

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
             if(mCurValue<mFinValue){
                 setText(fnum.format(mCurValue));
                 mCurValue+=mRate*mAR;
                 sendMessageDelayed(new Message(),DELAY_TIME);
             }else{
                 setText(fnum.format(mFinValue));
             }
        }
    };

    public MagicTextView(Context context) {
        super(context);
    }

    public MagicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MagicTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 要显示的值
     * @param value
     */
    public void setValue(float value){
        mFinValue=value;
        setRate();
    }



    /**
     * 为了使整个增长过程在mTotalTime时间内完成，所做的计算增长的值
     */
    private void setRate() {
        mRate=(mFinValue-mCurValue)/(mTotalTime/DELAY_TIME);
    }


    /**
     * 设置保留的小数位,默认为保留整数位
     */
    public void setDecimalNum(int num){
        if(num==0)return;

        StringBuffer sb=new StringBuffer();
        sb.append("0.");
        for(int i=0; i<num;i++){
            sb.append("0");
        }
         fnum=new DecimalFormat(sb.toString());


    }

    public void begin(){
        mHandler.sendEmptyMessage(0);
    }




}
