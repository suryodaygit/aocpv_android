
package com.suryodaybank.jyotiassisted.models;


import com.google.gson.annotations.SerializedName;


public class LoginRequestData {

    @SerializedName("Password")
    private String mPassword;
    @SerializedName("UserID")
    private String mUserID;

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getUserID() {
        return mUserID;
    }

    public void setUserID(String userID) {
        mUserID = userID;
    }

}
