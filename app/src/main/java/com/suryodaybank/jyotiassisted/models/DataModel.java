package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class DataModel<T> {

    @SerializedName("Data")
    private T data;

    @SerializedName("Error")
    private String error;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
