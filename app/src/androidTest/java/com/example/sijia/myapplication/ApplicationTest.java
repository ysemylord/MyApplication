package com.example.sijia.myapplication;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sijia.myapplication.util.VolleyQuenueInstence;
import com.orhanobut.logger.Logger;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    public void test(){

    /*    String path=Environment.getDownloadCacheDirectory().getAbsolutePath();
        Log.i("path", path);

        Log.i("DataDirectory()", Environment.getDataDirectory().getAbsolutePath());
        Log.i("DownloadCacheDirectory)", Environment.getDownloadCacheDirectory().getAbsolutePath());
        Log.i("ExternalStorageDir", Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.i("getRootDirectory", Environment.getRootDirectory().getAbsolutePath());
        Log.i("getExternalFilesDir)", Environment.getExternalStorageDirectory().getAbsolutePath());

        Log.i("Context--ExternalCacheDir", getContext().getExternalCacheDir().getAbsolutePath());//
        Log.i("Context---CacheDir", getContext().getCacheDir().getAbsolutePath());*/
    }
    public void testLogger(){
        Logger.init("xybtag");
        Log.i("tag", "message");
        Logger.i("123", "456");
        Logger.i("message");
        Logger.t("mytag").i("message");
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://www.ccclsc.com/app/index.php?act=goods&op=goods_list",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                           Logger.json(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        VolleyQuenueInstence.getInstance(getContext()).add(stringRequest);
        //Logger.json();

    }
}