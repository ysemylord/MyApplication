package com.example.sijia.myapplication.util;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by xu on 2016/1/18.
 */
public class VolleyQuenueInstence {
    private static RequestQueue mRequestQueue;

    private VolleyQuenueInstence() {

    }

    public static RequestQueue getInstance(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue =Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

}
