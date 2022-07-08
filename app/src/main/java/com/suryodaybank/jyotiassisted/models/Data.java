
package com.suryodaybank.jyotiassisted.models;


import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("BRCD")
    private String mBRCD;
    @SerializedName("UID1")
    private String mUID1;
    public String getBRCD() {
        return mBRCD;
    }

    public void setBRCD(String bRCD) {
        mBRCD = bRCD;
    }

    public String getUID1() {
        return mUID1;
    }

    public void setUID1(String uID1) {
        mUID1 = uID1;
    }

}
