package com.test.databindtest.practice.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.test.databindtest.BR;

/**
 * Created by Administrator on 2016/7/11.
 */
public class Student extends BaseObservable {

    /**
     * returnCode : 200
     * returnDesc : 成功
     * returnData : b45f65603c5d12b6b37561bba35e7547
     * name : xyabo
     * age : 23
     * hobby : reading
     * dream : getting
     * credits : 45
     */

    private int returnCode;
    private String returnDesc;
    private String returnData;
    private String name;
    private int age;
    private String hobby;
    private String dream;
    private int credits;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
        notifyPropertyChanged(BR.hobby);
    }

    @Bindable
    public String getDream() {
        return dream;
    }

    public void setDream(String dream) {
        this.dream = dream;
        notifyPropertyChanged(BR.dream);
    }

    @Bindable
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
        notifyPropertyChanged(BR.credits);
    }
}
