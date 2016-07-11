package com.test.databindtest.practice.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.test.databindtest.R;
import com.test.databindtest.Util;
import com.test.databindtest.databinding.ActivityPracticBinding;
import com.test.databindtest.practice.bean.Student;

public class PracticActivity extends Activity {
    Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_practic);
        ActivityPracticBinding activityPracticBinding = DataBindingUtil.setContentView(this, R.layout.activity_practic);
        String studentJson=Util.getFromAssets("Student.json",this);
        mStudent=new Gson().fromJson(studentJson,Student.class);
        activityPracticBinding.setStudent(mStudent);
    }

    public void updateStudentData(View view) {
        int credits=mStudent.getCredits()+1;
        mStudent.setCredits(credits);
    }
}
