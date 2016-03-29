package com.example.sijia.myapplication.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xyb on 2015/11/10.
 */
public class Util {
    public static String dateFormat(Date date) {
        SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateformat1.format(date);
        return dateStr;
    }

    /**
     * 将表示十六进制的字符串格式化为偶数个，并去掉空格
     * 如ef f->ef0f
     *
     * @param str
     * @return
     */
    public static StringBuffer hexStrFormat(String str) {
        StringBuffer sb = new StringBuffer(str.replace(" ", ""));//去空格
        int length = sb.length();
        if (length % 2 != 0) {
            sb.insert(sb.length() - 1, "0");

        }
        return sb;
    }

    /**
     * 将十六进制字符串每隔连个加上空格,用于展示在TextView中
     * efeffe0f->ef ef fe 0f
     *
     * @param sb
     * @return
     */
    public static String showHexStr(StringBuffer sb) {
        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
            count++;
            if (count == 3) {
                sb.insert(i, " ");
                count = 0;
            }
        }
        return sb.toString();
    }


    public static int getWindowHeight(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager ww = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        ww.getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        return height;
    }


    public static int getWindowWidth(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager ww = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        ww.getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        return width;

    }

    public static int outScreenInfo(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager ww = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        ww.getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;     // 屏幕密度（   0.75 / 1.0  / 1.5  / 2  /  3  / 3.5 / 4 ）
        int densityDpi = metric.densityDpi; // 屏幕密度DPI（ 120 / 160  / 240 / 320 / 480 / 560 / 640）
                                            //              ldpi  mdpi   hdpi  xhdpi xxhdpi
        Log.i("screenInfo"+"width",width+"");
        Log.i("screenInfo"+"height",height+"");
        Log.i("screenInfo"+"density",density+"");
        Log.i("screenInfo" + "densityDpi", densityDpi + "");
        return densityDpi;
    }

    /**
     * 获取wifi状态下的IP
     */
    public static String getWifiIP(Context context) {
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    private static String intToIp(int i) {

        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }

    public static String getCurrentTime() {
        String timeStr = null;
        Date date = new Date();
        int hour = date.getHours();
        int minut = date.getMinutes();
        timeStr = hour + ":" + minut;
        return timeStr;
    }

    /**
     * 获取app最大内存 单位byte
     *
     * @return
     */
    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 精确两位小数
     * @param price
     */
    public static void preciseTow(float price){
        DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p=decimalFormat.format(price);//format 返回的是字符串
    }

    public static void webViewNormalSetting(WebView webview_present,String cachePath){
        webview_present.getSettings().setJavaScriptEnabled(true);
        webview_present.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webview_present.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        // 开启 DOM storage API 功能
        webview_present.getSettings().setDomStorageEnabled(true);
        //开启 database storage API 功能
        webview_present.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = Environment.getDownloadCacheDirectory() + cachePath;
        webview_present.getSettings().setDatabasePath(cacheDirPath);
        //设置  Application Caches 缓存目录
        webview_present.getSettings().setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能
        webview_present.getSettings().setAppCacheEnabled(true);
        webview_present.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

}
