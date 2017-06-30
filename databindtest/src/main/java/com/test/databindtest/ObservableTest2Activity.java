package com.test.databindtest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.databindtest.bean.ObservableUser2;
import com.test.databindtest.bean.PainUser;
import com.test.databindtest.databinding.ActivityObservableTest2Binding;

public class ObservableTest2Activity extends AppCompatActivity {

    private ObservableUser2 mObservableUser;
    private PainUser mpainUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_observable_test);
        ActivityObservableTest2Binding activityObservableTestBinding = DataBindingUtil.setContentView(this, R.layout.activity_observable_test2);
        mObservableUser = new ObservableUser2();
        mObservableUser.setFirstName("巫妖");
        mObservableUser.setAge("90岁");
        activityObservableTestBinding.setUser(mObservableUser);



    }

    public void nority_data_change(View view) {
        String domain = Math.random() + "";
        mObservableUser.setFirstName(domain + "-巫妖");
        mObservableUser.setAge(domain + "-90岁");
        mObservableUser.notifyChange();

    }


}
