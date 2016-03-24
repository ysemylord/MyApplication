package houm.com.cameramine.Fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import houm.com.cameramine.R;

/**
 * Created by Administrator on 2015/12/3.
 */
public class CameraFragment extends Fragment implements SurfaceHolder.Callback {
    SurfaceView mSurfaceView;
    SurfaceHolder mSurfaceHolder;
    Camera mCamera;
    String TAG="CameraFragment";
    private Camera.Parameters parameters;


    @Override
    public void onStart() {
        super.onStart();
        if (checkCamaraHandware(getActivity()) && mCamera == null) {
            mCamera = getCamera();
        }

    }



    /**
     * 检查Camera是否存在
     *
     * @param context
     * @return
     */
    private boolean checkCamaraHandware(Context context) {
        boolean exite = false;
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
            exite = true;
        return exite;
    }

    private Camera getCamera() {
        Camera camera = null;
        try {

            camera = Camera.open();
        } catch (Exception e) {
            Log.e("getCamera", "camera is not aavilable");

        }
        return camera;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        mSurfaceView= (SurfaceView) view.findViewById(R.id.surface_view);
        mSurfaceHolder=mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        return view;
    }





    //SurfaceHolder.CallBack
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setStartPreview(mCamera, mSurfaceHolder);
        initCameraParams();
    }

    //SurfaceHolder.CallBack
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
             if(mSurfaceHolder.getSurface()==null){
                 Log.e(TAG,"mSurfaceHoldre is null");
                 return;
             }
             mCamera.stopPreview();
             setStartPreview(mCamera, mSurfaceHolder);

    }


    //SurfaceHolder.CallBack
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
             release();
    }


    /**
     * camera，预览
     */

    private void setStartPreview(Camera camera, SurfaceHolder surfaceHolder) {
        try {
            camera.setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("setStartPreview", e.getMessage());
        }
        camera.startPreview();
    }

    private void release(){
        if(mCamera!=null){
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
        }
    }


    //相机参数的初始化设置
    private void initCameraParams()
    {
        parameters=mCamera.getParameters();
        parameters.setPictureFormat(PixelFormat.JPEG);
        //parameters.setPictureSize(surfaceView.getWidth(), surfaceView.getHeight());  // 部分定制手机，无法正常识别该方法。
       // parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH); 开启闪光灯
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//1连续对焦

        mCamera.setDisplayOrientation(90);
        mCamera.setParameters(parameters);
       // mCamera.cancelAutoFocus();// 2如果要实现连续的自动对焦，这一句必须加上


    }




}
