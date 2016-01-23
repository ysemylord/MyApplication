package com.example.sijia.myapplication;

import android.app.Application;
import android.os.Environment;
import android.test.ApplicationTestCase;
import android.util.Log;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    public void test(){

        String path=Environment.getDownloadCacheDirectory().getAbsolutePath();
        Log.i("path",path);

        Log.i("DataDirectory()", Environment.getDataDirectory().getAbsolutePath());
        Log.i("DownloadCacheDirectory)", Environment.getDownloadCacheDirectory().getAbsolutePath());
        Log.i("ExternalStorageDir", Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.i("getRootDirectory", Environment.getRootDirectory().getAbsolutePath());
        Log.i("getExternalFilesDir)", Environment.getExternalStorageDirectory().getAbsolutePath());

        Log.i("Context--ExternalCacheDir", getContext().getExternalCacheDir().getAbsolutePath());//
        Log.i("Context---CacheDir", getContext().getCacheDir().getAbsolutePath());
    }
}