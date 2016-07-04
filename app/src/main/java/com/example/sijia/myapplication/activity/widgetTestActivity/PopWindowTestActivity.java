package com.example.sijia.myapplication.activity.widgetTestActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.sijia.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopWindowTestActivity extends AppCompatActivity {


    @Bind(R.id.left)
    Button left;
    @Bind(R.id.top)
    Button top;
    @Bind(R.id.right)
    Button right;
    @Bind(R.id.bottom)
    Button bottom;
    @Bind(R.id.no)
    Button no;
    @Bind(R.id.anchor)
    TextView anchor;
    private PopupWindow mPopupWindow;
    private View mPopLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_window_test);
        ButterKnife.bind(this);
        mPopLayout = getLayoutInflater().inflate(R.layout.pop_layout, new LinearLayout(this), false);
        mPopupWindow = new PopupWindow(mPopLayout,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        mPopLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        mPopupWindow.setFocusable(true);

        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        anchor.post(new Runnable() {
            @Override
            public void run() {
                showAtTop(anchor,mPopLayout,mPopupWindow);
            }
        });

    }


    @OnClick({R.id.left, R.id.top, R.id.right, R.id.bottom, R.id.no, R.id.anchor})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left:
                mPopupWindow.showAtLocation(mPopLayout, Gravity.LEFT, 0, 0);
                break;
            case R.id.top:
                mPopupWindow.showAtLocation(mPopLayout, Gravity.TOP, 0, 0);
                break;
            case R.id.right:
                mPopupWindow.showAtLocation(mPopLayout, Gravity.RIGHT, 0, 0);
                break;
            case R.id.bottom:
                mPopupWindow.showAtLocation(mPopLayout, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.no:
                mPopupWindow.showAtLocation(mPopLayout, Gravity.NO_GRAVITY, 0, 0);
                break;
            case R.id.anchor:
                showAtTop(anchor,mPopLayout,mPopupWindow);
                break;
        }
    }

    public void showAtTop(View anchor,View popLayoutView,PopupWindow popupWindow){
        int[] anchor_location=new int[2];
        anchor.getLocationOnScreen(anchor_location);
        int anchor_locationX=anchor_location[0];
        int anchor_locationY=anchor_location[1];
        int delatX=anchor_locationX+(anchor.getWidth()/2-popLayoutView.getMeasuredWidth()/2);
        int delatY=anchor_locationY-popLayoutView.getMeasuredHeight();
        popupWindow.showAtLocation(anchor,Gravity.NO_GRAVITY,delatX,delatY);
    }
}
