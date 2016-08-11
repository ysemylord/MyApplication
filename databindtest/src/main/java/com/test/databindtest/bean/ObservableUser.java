package com.test.databindtest.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.test.databindtest.BR;

/**
 * 规则getXxxYyy()get后面的单词首字母必须大写
 * 绑定过后BR文件中的映射名为xxxYyy 单词首字母小写
 */
public class ObservableUser extends BaseObservable {
    private String firstName;
    private String age;

    public ObservableUser(String firstName, String age) {
        this.firstName = firstName;
        this.age = age;
    }

    public ObservableUser() {

    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    @Bindable
    public String getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }


    public void setAge(String mAge) {
        this.age = mAge;
        notifyPropertyChanged(BR.age);
    }
}
