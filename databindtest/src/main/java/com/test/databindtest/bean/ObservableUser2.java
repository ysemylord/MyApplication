package com.test.databindtest.bean;

import android.databinding.BaseObservable;

/**
 * 规则getXxxYyy()get后面的单词首字母必须大写
 * 绑定过后BR文件中的映射名为xxxYyy 单词首字母小写
 */
public class ObservableUser2 extends BaseObservable {
    private String firstName;
    private String age;

    public ObservableUser2(String firstName, String age) {
        this.firstName = firstName;
        this.age = age;
    }

    public ObservableUser2() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setAge(String mAge) {
        this.age = mAge;
    }
}
