package houm.com.cameramine.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import houm.com.cameramine.R;

/**
 * Created by xuyaf on 2016/4/20.
 */
public class Util {


    public static final float DISPLAY_WIDTH = 200;
    public static final float DISPLAY_HEIGHT = 200;
    /**
     * 从path中获取图片信息
     * @param path
     * @return
     */
    public static Bitmap decodeBitmap(String path){
        BitmapFactory.Options op = new BitmapFactory.Options();
        //inJustDecodeBounds
        //If set to true, the decoder will return null (no bitmap), but the out…
        op.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(path, op); //获取尺寸信息
        //获取比例大小
        int wRatio = (int)Math.ceil(op.outWidth/DISPLAY_WIDTH);
        int hRatio = (int)Math.ceil(op.outHeight/DISPLAY_HEIGHT);
        //如果超出指定大小，则缩小相应的比例
        if(wRatio > 1 && hRatio > 1){
            if(wRatio > hRatio){
                op.inSampleSize = wRatio;
            }else{
                op.inSampleSize = hRatio;
            }
        }
        op.inJustDecodeBounds = false;
        bmp = BitmapFactory.decodeFile(path, op);
        return bmp;
    }

    /** 保存方法 */
    public static void saveBitmap(String picPath,Bitmap bm) {
        File f = new File(picPath);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i("saveBitmap", "已经保存");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 改变系统状态栏颜色
     * @param activity
     * todo 按时
     */
    public static void initSystemBar(Activity activity) {



        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){//api<19直接返回
            return;
        }

        ViewGroup view =null;
        try {
            view = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        }catch (Exception e){
            return;//没有找到布局，直接返回
        }

        view.setFitsSystemWindows(true);
        view.setClipToPadding(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            setTranslucentStatus(activity, true);

        }

        SystemBarTintManager tintManager = new SystemBarTintManager(activity);

        tintManager.setStatusBarTintEnabled(true);

        // 使用颜色资源

        tintManager.setStatusBarTintResource(R.color.colorPrimary);

    }

    @TargetApi(19)

    private static void setTranslucentStatus(Activity activity, boolean on) {

        Window win = activity.getWindow();

        WindowManager.LayoutParams winParams = win.getAttributes();

        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

        if (on) {

            winParams.flags |= bits;

        } else {

            winParams.flags &= ~bits;

        }

        win.setAttributes(winParams);

    }
}
