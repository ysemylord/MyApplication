package com.example.sijia.myapplication.fragment.CustomWidget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.fragment.BaseDialogFragment;

/**
 * Created by xuyaf on 2016/3/15.
 */
public class TransferMatrixDialogFragment extends BaseDialogFragment {

ImageView mIaImageView1;
    @Override
    protected int getResourse() {
        return R.layout.transfer_matrix_dialog_layout;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        float[] mImageTrix = new float[]{
                2, 0, 0,
                0, 2, 0,
                0, 0, 1
        };
        Matrix matrix = new Matrix();
        matrix.setValues(mImageTrix);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.teset);
        Bitmap blankBitmap = Bitmap.createBitmap(bitmap.getWidth()*2, bitmap.getHeight()*2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(blankBitmap);
        canvas.drawBitmap(bitmap, matrix, null);
        mIaImageView1= (ImageView) getView().findViewById(R.id.im);
        mIaImageView1.setImageBitmap(blankBitmap);


    }
}
