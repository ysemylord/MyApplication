package com.example.sijia.myapplication.fragment.CustomWidget;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.fragment.BaseFragment;


public class RoundImageFragment extends BaseFragment {

    ImageView mImageView;

    public RoundImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
  /*      Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.test);
        Bitmap mOUt=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(mOUt);
        Paint paint=new Paint();
        canvas.drawRoundRect(0,0,bitmap.getWidth(),bitmap.getHeight(),80,80,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,0,0,paint);*/
        LinearLayout con = (LinearLayout) inflater.inflate(R.layout.fragment_round_image, container, false);
      mImageView= (ImageView) con.findViewById(R.id.test_img);

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.teset);


        Bitmap mOUt=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(mOUt);
        Paint paint=new Paint();



        //先画dst
        canvas.drawCircle(mOUt.getWidth()/2,mOUt.getHeight()/2,mOUt.getWidth()/2,paint);

        //图想混排模式设置成显示两图片中Src相交的部分
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        //再画src
        canvas.drawBitmap(bitmap, 0, 0,paint);

        mImageView.setImageBitmap(mOUt);
        return con;
    }


}
