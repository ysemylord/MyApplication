package com.test.databindtest.bean;

public class User {
    private final String firstName;
    private final String age;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.age = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAge() {
        return age;
    }
}