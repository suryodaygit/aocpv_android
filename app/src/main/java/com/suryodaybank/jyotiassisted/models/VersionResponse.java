
package com.suryodaybank.jyotiassisted.models;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class VersionResponse {

    @SerializedName("Data")
    private VersionResponseData mVersionResponseData;

    public VersionResponseData getData() {
        return mVersionResponseData;
    }

    public void setData(VersionResponseData versionResponseData) {
        mVersionResponseData = versionResponseData;
    }

}
