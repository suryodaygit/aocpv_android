package com.suryodaybank.jyotiassisted.models;

import android.telecom.Call;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

public class LoginErrorResponse extends JSONObject {
    String errorMessage="";
    String statusCode="";
    @SerializedName("details")
    List<String> details;

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public List<String> getDetails() {
        return details;
    }
}
