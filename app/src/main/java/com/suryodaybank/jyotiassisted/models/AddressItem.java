package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class AddressItem {

    @SerializedName("address_Line3")
    private String addressLine3;

    @SerializedName("pincode")
    private String pincode;

    @SerializedName("country")
    private String country;

    @SerializedName("city")
    private String city;

    @SerializedName("address_Line1")
    private String addressLine1;

    @SerializedName("address_Line2")
    private String addressLine2;

    @SerializedName("district")
    private String district;

    @SerializedName("state")
    private String state;

    public String getAddressLine3(){
        return addressLine3;
    }

    public String getPincode(){
        return pincode;
    }

    public String getCountry(){
        return country;
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

    public String getDistrict(){
        return district;
    }

    public String getState(){
        return state;
    }
}