package com.example.sijia.myapplication.fragment.WidgetUse;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/3.
 */
public class WebViewFragment extends BaseFragment {

    @Bind(R.id.mywebview)
    WebView mMywebview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_webview_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMywebview.loadUrl("http://m.oschina.net/blog/173518");
        mMywebview.setWebViewClient(new MyWebViewClient());
        mMywebview.setVerticalScrollBarEnabled(false);
        mMywebview.setHorizontalScrollBarEnabled(false);

        mMywebview.getSettings().setSupportZoom(true);
        mMywebview.getSettings().setBuiltInZoomControls(true);//设置是否可以缩放
        mMywebview.getSettings().setDisplayZoomControls(true);//设置是否显示缩放图标

        mMywebview.getSettings().setJavaScriptEnabled(true);

        mMywebview.addJavascriptInterface(this, "myWebView");


    }
    public void showSource(String html) {
        Log.d("HTML", html);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i("MyWebViewClient", "onPageStarted");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.i("MyWebViewClient", "onPageFinished");
            //页面加载完成后，执行以下javascript
            //mMywebview.loadUrl("javascript:window.myWebView.showSource(document.getElementById('kjk-layout-header').style.display='none');");
              mMywebview.loadUrl("javascript:window.myWebView.showSource(document.getElementsByTagName('html')[0].innerHTML)");
           // mMywebview.loadUrl("document.getElementsByTagName('html')[0].innerHTML");
            //document.getElementsByTagName('html')[0].innerHTML
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            mMywebview.loadUrl(url);
            Log.i("url", url);
            return false;
        }
    }


}
