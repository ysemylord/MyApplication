package com.test.rxlearn;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    String TAG = "RX——android";

    public ApplicationTest() {
        super(Application.class);

    }

    public void testSimpleRx() {
        Observable.just("hello,world")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, s);
                    }
                });
    }

    public void testSimpleRx2() {
        Observable.just("hello,world")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "--xu";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, s);
                    }
                });
    }

    public void testSimpleRx3() {
        String[] names=new String[]{"jack","lord","land"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG,s);
                    }
                });
    }




}