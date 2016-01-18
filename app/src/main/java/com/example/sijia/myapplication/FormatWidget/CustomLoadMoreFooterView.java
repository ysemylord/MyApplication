package com.example.sijia.myapplication.FormatWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sijia.myapplication.R;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreUIHandler;

/**
 * Created by xu on 2016/1/18.
 */

    public  class CustomLoadMoreFooterView  extends RelativeLayout implements LoadMoreUIHandler {

        private TextView mTextView;

        public CustomLoadMoreFooterView(Context context) {
            this(context, null);
        }

        public CustomLoadMoreFooterView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public CustomLoadMoreFooterView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            setupViews();
        }

        private void setupViews() {
            LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_ptf_footer, this);
            mTextView = (TextView) findViewById(R.id.footer_tv);
        }

        @Override
        public void onLoading(LoadMoreContainer container) {
            setVisibility(VISIBLE);
            mTextView.setText("加载中.......");
        }

        @Override
        public void onLoadFinish(LoadMoreContainer container, boolean empty, boolean hasMore) {
            if (!hasMore) {//没有更多数据
                setVisibility(VISIBLE);
                if (empty) {
                    mTextView.setText("数据为空");
                } else {
                    mTextView.setText("全部数据加载完毕");
                }
            } else {
                setVisibility(INVISIBLE);
            }
        }

        @Override
        public void onWaitToLoadMore(LoadMoreContainer container) {
            setVisibility(VISIBLE);
            mTextView.setText("点击加载更多");
        }

        @Override
        public void onLoadError(LoadMoreContainer container, int errorCode, String errorMessage) {
            mTextView.setText("加载失败");
        }
    }

