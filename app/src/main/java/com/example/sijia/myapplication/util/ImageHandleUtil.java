package com.example.sijia.myapplication.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by xyb on 2016/2/25.
 */
public class ImageHandleUtil  {

    /**
     *
     * @param originalBitmap
     * @param hue 色调
     * @param saturation 饱和度
     * @param lun   亮度
     * @return
     */
     public static Bitmap handleImageEffec(Bitmap originalBitmap,float hue,float saturation,float lun){
         Bitmap neededBitmap=Bitmap.createBitmap(originalBitmap.getWidth(), originalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
         Canvas canvase=new Canvas(neededBitmap);
         Paint paint=new Paint();

         //色调
         ColorMatrix hueColorMatrix=new ColorMatrix();
         hueColorMatrix.setRotate(0,hue);
         hueColorMatrix.setRotate(1,hue);
         hueColorMatrix.setRotate(2, hue);

         //设置饱和度
         ColorMatrix saturationColorMatrix=new ColorMatrix();
         saturationColorMatrix.setSaturation(saturation);

         //通过设置颜色系数的方式来调节亮度
         ColorMatrix lunColorMatrix=new ColorMatrix();
         lunColorMatrix.setScale(lun,lun,lun,1);

         //混合颜色矩阵效果
         ColorMatrix finalColorMatrix=new ColorMatrix();
         finalColorMatrix.postConcat(hueColorMatrix);
         finalColorMatrix.postConcat(saturationColorMatrix);
         finalColorMatrix.postConcat(lunColorMatrix);


         paint.setColorFilter(new ColorMatrixColorFilter(finalColorMatrix));
         canvase.drawBitmap(originalBitmap, 0, 0, paint);//将origianlBitmap画在needBitmap上

         return neededBitmap;
     }

}
