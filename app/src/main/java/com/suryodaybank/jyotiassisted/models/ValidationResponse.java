package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class ValidationResponse {
    @SerializedName("Data")
    ValidationData data;
    @SerializedName("status")
    String status;

    public ValidationData getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }
}
