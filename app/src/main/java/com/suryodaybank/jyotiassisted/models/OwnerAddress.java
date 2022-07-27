package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class OwnerAddress {
    @SerializedName("address_Line1")
    String address_Line1;
    @SerializedName("address_Line2")
    String address_Line2;
    @SerializedName("address_Line3")
    String address_Line3;
    @SerializedName("city")
    String city;
    @SerializedName("pincode")
    String pinCode;
    @SerializedName("district")
    String district;
    @SerializedName("state")
    String state;
    @SerializedName("country")
    String country;

    public String getAddress_Line1() {
        return address_Line1;
    }

    public String getAddress_Line2() {
        return address_Line2;
    }

    public String getAddress_Line3() {
        return address_Line3;
    }

    public String getCity() {
        return city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
}
