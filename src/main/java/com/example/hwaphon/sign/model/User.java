package com.example.hwaphon.sign.model;

/**
 * Created by hwaphon on 3/10/2016.
 */
public class User {

    private String mName;
    private String mPassword;
    private String mPhone;

    public User() {

    }

    public User(String name, String password) {
        mName = name;
        mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }
}
