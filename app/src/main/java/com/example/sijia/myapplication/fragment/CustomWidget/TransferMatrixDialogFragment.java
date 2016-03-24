package com.example.sijia.myapplication.fragment.CustomWidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.sijia.myapplication.FormatAdapter.SimpleListViewBaseAdapter;
import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.fragment.BaseDialogFragment;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by xuyaf on 2016/3/15.
 */
public class TransferMatrixDialogFragment extends BaseDialogFragment {

    ImageView mIaImageView1;

    GridView mGridView;
    EditAdapter mDataAdapter;
    private String[] mDatas;

    @Override
    protected int getResourse() {
        return R.layout.transfer_matrix_dialog_layout;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mGridView = (GridView) getView().findViewById(R.id.gridview);
        mDatas = new String[]{
                "1", "0", "0",
                "0", "1", "0",
                "0", "0", "1"
        };

        List<String> list = Arrays.asList(mDatas);
        mDataAdapter = new EditAdapter(getActivity(), list);

        mGridView.setAdapter(mDataAdapter);

        getView().findViewById(R.id.config_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> datas = ((EditAdapter) mGridView.getAdapter()).getList();
                float[] mImageTrix = new float[9];

                int index = 0;
                for (String value : datas) {
                    mImageTrix[index++] = Float.parseFloat(value);
                    Log.i("value", Float.parseFloat(value) + "");
                }

                Matrix matrix = new Matrix();
                matrix.setValues(mImageTrix);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                Bitmap blankBitmap = Bitmap.createBitmap(bitmap.getWidth() * 2, bitmap.getHeight() * 2, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(blankBitmap);
                canvas.drawBitmap(bitmap, matrix, null);
                mIaImageView1 = (ImageView) getView().findViewById(R.id.im);
                mIaImageView1.setImageBitmap(blankBitmap);
            }
        });

       /* ImageView imageView= (ImageView) getView().findViewById(R.id.ori);
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.teset);
        imageView.setImageBitmap(bitmap);*/

    }

    class EditAdapter extends SimpleListViewBaseAdapter<String> {

        public EditAdapter(Context context, List<String> data) {
            super(context, data);
        }

        @Override
        public View getItemView(final int position, View convertView, ViewHolder holder) {

            //return R.layout.item_gride_edit;
            EditText editText = holder.getView(R.id.et);
            editText.setText(getItem(position));
            editText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    data.set(position, s.toString());
                }
            });
            return convertView;
        }

        @Override
        public int getResourece() {
            return R.layout.item_gride_edit;
        }

        public List<String> getList(){
            return data;
        }
    }
}
