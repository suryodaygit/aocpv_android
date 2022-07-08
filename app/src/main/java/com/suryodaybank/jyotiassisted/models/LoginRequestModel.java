
package com.suryodaybank.jyotiassisted.models;


import com.google.gson.annotations.SerializedName;


public class LoginRequestModel {

    @SerializedName("Data")
    private LoginRequestData mLoginRequestData;

    public LoginRequestData getData() {
        return mLoginRequestData;
    }

    public void setData(LoginRequestData loginRequestData) {
        mLoginRequestData = loginRequestData;
    }

}
