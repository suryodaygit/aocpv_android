
package com.suryodaybank.jyotiassisted.models;


import com.google.gson.annotations.SerializedName;


public class RequestData {

    @SerializedName("Data")
    private VersionData versionData;

    public VersionData getData() {
        return versionData;
    }

    public void setData(VersionData versionData) {
        this.versionData = versionData;
    }

}
