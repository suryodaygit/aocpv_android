
package com.suryodaybank.jyotiassisted.models;


import com.google.gson.annotations.SerializedName;


public class VersionResponse {

    @SerializedName("allow")
    private Boolean mAllow;
    @SerializedName("message")
    private String mMessage;

    public Boolean getAllow() {
        return mAllow;
    }

    public void setAllow(Boolean allow) {
        mAllow = allow;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
