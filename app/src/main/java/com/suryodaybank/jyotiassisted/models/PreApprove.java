package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PreApprove {

    @SerializedName("address")
    private String address;

    @SerializedName("date_OF_BIRTH")
    private String dateOFBIRTH;

    @SerializedName("customerID")
    private int customerID;

    @SerializedName("state")
    private String state;

    @SerializedName("postal")
    private String postal;

    @SerializedName("member_NAME")
    private String memberNAME;

    @SerializedName("landphone_NUMBER")
    private String landphoneNUMBER;

    @SerializedName("reference_NUMBER")
    private String referenceNUMBER;

    public String getAddress() {
        return address;
    }

    public String getDateOFBIRTH() {
        return dateOFBIRTH;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getState() {
        return state;
    }

    public String getPostal() {
        return postal;
    }

    public String getMemberNAME() {
        return memberNAME;
    }

    public String getLandphoneNUMBER() {
        return landphoneNUMBER;
    }

    public String getReferenceNUMBER() {
        return referenceNUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreApprove that = (PreApprove) o;
        return customerID == that.customerID && Objects.equals(address, that.address) && Objects.equals(dateOFBIRTH, that.dateOFBIRTH) && Objects.equals(state, that.state) && Objects.equals(postal, that.postal) && Objects.equals(memberNAME, that.memberNAME) && Objects.equals(landphoneNUMBER, that.landphoneNUMBER) && Objects.equals(referenceNUMBER, that.referenceNUMBER);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, dateOFBIRTH, customerID, state, postal, memberNAME, landphoneNUMBER, referenceNUMBER);
    }
}