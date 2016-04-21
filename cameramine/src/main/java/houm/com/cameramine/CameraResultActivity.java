package houm.com.cameramine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import houm.com.cameramine.Fragment.CameraFragment;
import houm.com.cameramine.util.Util;

public class CameraResultActivity extends BaseActivity {
    private String mPicPath;
    private Bitmap mOriginBitmap;
    private Bitmap mLastBitmp;
    private ImageView mShowPhoIM;
    private float[] mGrayMatrix;//灰度
    private float[] mReversalMatrix;//反转
    private float[] mOldMatrix;//怀旧
    private float[] mDiscolorationMatrix;
    private float[] mSaturationMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_result);
        getSupportActionBar().setTitle("拍照结果");
        init();
        initMatrix();
    }


    private void init() {
        mShowPhoIM = (ImageView) findViewById(R.id.show_photo_iv);
        mPicPath = getIntent().getStringExtra(CameraFragment.PIC_PATH);
        Bitmap tempBitmap = BitmapFactory.decodeFile(mPicPath);
        android.graphics.Matrix m = new android.graphics.Matrix();
        m.setRotate(90);
        mOriginBitmap = Bitmap.createBitmap(tempBitmap, 0, 0, tempBitmap.getWidth(), tempBitmap.getHeight(), m, true);
        mShowPhoIM.setImageBitmap(mOriginBitmap);


    }

    private void initMatrix() {
        mGrayMatrix = new float[]{
                0.33f, 0.59f, 0.11f, 0, 0,
                0.33f, 0.59f, 0.11f, 0, 0,
                0.33f, 0.59f, 0.11f, 0, 0,
                0, 0, 0, 1, 0,
        };
        mReversalMatrix = new float[]{
                -1, 0, 0, 1, 1,
                0, -1, 0, 1, 1,
                0, 0, -1, 1, 1,
                0, 0, 0, 1, 0,
        };

        mOldMatrix = new float[]{
                0.383f, 0.769f, 0.189f, 0, 0,
                0.349f, 0.686f, 0.168f, 0, 0,
                0.272f, 0.534f, 0.131f, 0, 0,
                0, 0, 0, 1, 0,
        };

        mDiscolorationMatrix = new float[]{
                1.5f, 1.5f, 1.5f, 0, -1,
                1.5f, 1.5f, 1.5f, 0, -1,
                1.5f, 1.5f, 1.5f, 0, -1,
                0, 0, 0, 1, 0,
        };

        mSaturationMatrix = new float[]{
                1.438f, -0.122f, -0.016f, 0, -0.03f,
                -0.062f, 1.378f, -0.016f, 0, 0.05f,
                -0.62f, -0.122f, 1.483f, 0, -0.02f,
                0, 0, 0, 1, 0,
        };

    }

    private Bitmap useColorMatrix(float[] mImageTrix) {
        if (mLastBitmp != null && !mLastBitmp.equals(mOriginBitmap)) {
            mLastBitmp.recycle();
            Log.i("recycler", "true");
        }
        ColorMatrix matrix = new ColorMatrix();
        matrix.set(mImageTrix);
        Bitmap bitmap = mOriginBitmap;
        Bitmap blankBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(blankBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(mImageTrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        mLastBitmp = blankBitmap;
        return blankBitmap;
    }

    public void to_gray(View view) {
        Bitmap bitmap = useColorMatrix(mGrayMatrix);
        mShowPhoIM.setImageBitmap(bitmap);
    }

    public void to_reser(View view) {
        Bitmap bitmap = useColorMatrix(mReversalMatrix);
        mShowPhoIM.setImageBitmap(bitmap);
    }

    public void to_old(View view) {
        Bitmap bitmap = useColorMatrix(mOldMatrix);
        mShowPhoIM.setImageBitmap(bitmap);
    }

    public void to_discoloration(View view) {
        Bitmap bitmap = useColorMatrix(mDiscolorationMatrix);
        mShowPhoIM.setImageBitmap(bitmap);
    }

    public void to_satur(View view) {
        Bitmap bitmap = useColorMatrix(mSaturationMatrix);
        mShowPhoIM.setImageBitmap(bitmap);
    }


    public void to_origin(View view) {
        mShowPhoIM.setImageBitmap(mOriginBitmap);
    }

    public void restore(View view) {
        mShowPhoIM.setDrawingCacheEnabled(true);
        Bitmap bitmap = mShowPhoIM.getDrawingCache();
        Util.saveBitmap(mPicPath, bitmap);
        finish();
    }
}
