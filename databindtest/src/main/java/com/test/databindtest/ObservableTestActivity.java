package com.test.databindtest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.databindtest.bean.ObservableUser;
import com.test.databindtest.bean.PainUser;
import com.test.databindtest.databinding.ActivityObservableTestBinding;

public class ObservableTestActivity extends AppCompatActivity {

    private ObservableUser mObservableUser;
    private PainUser mpainUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_observable_test);
        ActivityObservableTestBinding activityObservableTestBinding = DataBindingUtil.setContentView(this, R.layout.activity_observable_test);
        mObservableUser = new ObservableUser();
        mObservableUser.setFirstName("巫妖");
        mObservableUser.setAge("90岁");
        activityObservableTestBinding.setUser(mObservableUser);

        mpainUser = new PainUser();
        mpainUser.firstName.set("XuyaboP");
        mpainUser.age.set("34岁P");
        activityObservableTestBinding.setPainUser(mpainUser);


    }

    public void nority_data_change(View view) {
        String domain = Math.random() + "";
        mObservableUser.setFirstName(domain + "-巫妖");
        mObservableUser.setAge(domain + "-90岁");

    }

    public void granularity_notify_data_change(View view) {
        String domain = Math.random() + "P";
        mpainUser.firstName.set(domain+"-巫妖");
        mpainUser.age.set(domain+"-90岁");
    }
}
