package com.example.sijia.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HtmlSource extends AppCompatActivity {

    private WebView webView;

    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_html_source);

        webView = (WebView)findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");

        webView.setWebViewClient(new MyWebViewClient());

        webView.loadUrl("http://www.cnblogs.com/hibraincol/");

    }





    final class MyWebViewClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);

            return true;

        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            Log.i("WebView", "onPageStarted");

            super.onPageStarted(view, url, favicon);

        }

        public void onPageFinished(WebView view, String url) {

            Log.i("WebView","onPageFinished ");

            view.loadUrl("javascript:window.local_obj.showSource('<head>'+" +

                    "document.getElementsByTagName('html')[0].innerHTML+'</head>');");

            super.onPageFinished(view, url);

        }

    }



    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            Log.i("HTML", html);

        }

    }
}
