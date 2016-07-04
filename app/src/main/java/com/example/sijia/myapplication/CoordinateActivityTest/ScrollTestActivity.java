package com.example.sijia.myapplication.CoordinateActivityTest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sijia.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScrollTestActivity extends Activity {

    @Bind(R.id.content_tv)
    TextView mContentTv;
    @Bind(R.id.scrollView)
    ViewGroup mScrollView;
    @Bind(R.id.scroll_x)
    TextView mScrollX;
    @Bind(R.id.scroll_y)
    TextView mScrollY;
    @Bind(R.id.scroll_x_et)
    EditText mScrollXEt;
    @Bind(R.id.scroll_y_et)
    EditText mScrollYEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_test2);
        ButterKnife.bind(this);
        for (int i = 0; i < 500; i++) {
            mContentTv.append(i + " ");
        }

    }

    public void getScrollXY(View view) {
        mScrollX.setText(mScrollView.getScrollX() + "");
        mScrollY.setText(mScrollView.getScrollY() + "");
    }

    public void setScrollXY(View view) {
        int scrollX = Integer.parseInt(mScrollXEt.getText().toString().trim());
        int scrollY = Integer.parseInt(mScrollYEt.getText().toString().trim());
        mScrollView.scrollTo(
                scrollX, scrollY
        );
    }
}
