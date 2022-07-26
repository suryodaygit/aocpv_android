package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class ValidationRequestModel {
    @SerializedName("applicationNo")
    String applicationNo;

    public ValidationRequestModel(String applicationNo) {
        this.applicationNo = applicationNo;
    }
}
