package com.test.rxlearn;

import android.app.Application;
import android.support.annotation.NonNull;
import android.test.ApplicationTestCase;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    public void testRx2(){
        Observable<String> observable= Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.i("testRx", "I'm a thief,stealing");
                subscriber.onNext("a thief is stealing");
            }
        });

        Subscriber<String> subscriber= new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("testRx", "find a thief,arrest");
            }
        };

        observable.subscribe(subscriber);
    }


    public void testRx3(){
        Observable<String> observable= Observable.just("I'm a thief,stealing");

        Subscriber<String> subscriber= new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("testRx", "find a thief,arrest");
            }
        };

        observable.subscribe(subscriber);
    }

    public void testRx5(){
       Observable.just("a thief is stealing")
               .subscribe(new Action1<String>() {
                   @Override
                   public void call(String s) {
                       Log.i("testRx","find a thief arrest");
                   }
               });
    }


    public void testRx4(){
        Observable<String> observable= Observable.just("I'm a thief,stealing");

        Action1<String> action1=new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("testRx", "find a thief,arrest");
            }
        };

        observable.subscribe(action1);
    }


    public void testRx1(){
        Observable<String> observable = createStringObservable();

        Subscriber<String> subscriber = createStringSubscriber();

        observable.subscribe(subscriber);
    }



    @NonNull
    private Observable<String> createStringObservable() {
        Observable<String> observable= Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.i("testRx", "I'm a thief,stealing");
                subscriber.onNext("a thief is stealing");
            }
        });
        return observable;
    }

    @NonNull
    private Observable<String> createStringObservable2() {
        Observable<String> observable=Observable.just("a thief is stealing");
        return  observable;
    }

    @NonNull
    private Subscriber<String> createStringSubscriber() {
        Subscriber<String> subscriber= new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("testRx", "find a thief,arrest");
            }
        };
        return subscriber;
    }


}