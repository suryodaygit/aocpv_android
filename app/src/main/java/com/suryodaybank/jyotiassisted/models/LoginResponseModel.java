
package com.suryodaybank.jyotiassisted.models;


import com.google.gson.annotations.SerializedName;


public class LoginResponseModel {

    @SerializedName("Data")
    private Data mData;
    @SerializedName("Error")
    private String Error;

    public String getError() {
        return Error;
    }
    public void setError(String error) {
        Error = error;
    }

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

}
