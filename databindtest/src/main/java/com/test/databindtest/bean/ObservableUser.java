package com.test.databindtest.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.test.databindtest.BR;

/**
 * 规则getXxxYyy()get后面的单词首字母必须大写
 * 绑定过后BR文件中的映射名为xxxYyy 单词首字母小写
 */
public class ObservableUser extends BaseObservable {
    private String mFirstName;
    private String mAge;

    public ObservableUser(String mFirstName, String mAge) {
        this.mFirstName = mFirstName;
        this.mAge = mAge;
    }

    public ObservableUser() {

    }

    @Bindable
    public String getFirstName() {
        return mFirstName;
    }

    @Bindable
    public String getAge() {
        return mAge;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
        notifyPropertyChanged(BR.firstName);
    }


    public void setAge(String mAge) {
        this.mAge = mAge;
        notifyPropertyChanged(BR.age);
    }
}
