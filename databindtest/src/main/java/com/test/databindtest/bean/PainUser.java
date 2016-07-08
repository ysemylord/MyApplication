package com.test.databindtest.bean;

import android.databinding.ObservableField;

/**
 * 细粒度的绑定方式
 * Created by xyb on 2016/7/8.
 */
public class PainUser {
    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<String> age = new ObservableField<>();
}
