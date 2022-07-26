package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class AddressData {
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
}
