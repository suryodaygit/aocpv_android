package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class UtilityAddressItem {
    @SerializedName("address_Line3")
    private String addressLine3;

    @SerializedName("pincode")
    private String pincode;

    @SerializedName("city")
    private String city;

    @SerializedName("address_Line1")
    private String addressLine1;

    @SerializedName("address_Line2")
    private String addressLine2;

    @SerializedName("state")
    private String state;

    public String getAddressLine3(){
        return addressLine3;
    }

    public String getPincode(){
        return pincode;
    }

    public String getCity(){
        return city;
    }

    public String getAddressLine1(){
        return addressLine1;
    }

    public String getAddressLine2(){
        return addressLine2;
    }

    public String getState(){
        return state;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setState(String state) {
        this.state = state;
    }
}
