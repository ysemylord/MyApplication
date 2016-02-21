package com.example.sijia.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.sijia.myapplication.FormatWidget.ObservableScrollView;

/**
 * title Sroll根据滑动的距离设置Title的透明度
 */
public class ScrollLisenerActivity extends Activity {

    private RelativeLayout mTitleContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_lisener);
        final ObservableScrollView observableScrollView = (ObservableScrollView) findViewById(R.id.observableScrollView);
        mTitleContainer= (RelativeLayout) findViewById(R.id.title_container);
        observableScrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                int scrollY = observableScrollView.getScrollY();
                boolean nowScrollRolled = (scrollY == 0 ? false : true);
                int calcedAlphaColor = (0xaf * scrollY) / 100;
                int color = 0;
                //透明度为0x00---0xaf
                if (calcedAlphaColor > 0xaf) {
                    color = 0xaf;
                } else if (calcedAlphaColor < 0) {
                    color = 0;
                } else {
                    color = calcedAlphaColor;
                }
                int titleContainerColor = Color.argb(color, 0xff, 0xff, 0xff);
                mTitleContainer.setBackgroundColor(titleContainerColor);

            }
        });
    }
}
