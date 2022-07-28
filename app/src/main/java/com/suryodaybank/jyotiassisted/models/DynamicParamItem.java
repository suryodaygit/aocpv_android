package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class DynamicParamItem {

    @SerializedName("Value")
    private String value;

    @SerializedName("Name")
    private String name;

    public DynamicParamItem(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}