package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PreApprove {

    @SerializedName("branchId")
    private int branchId;

    @SerializedName("amount")
    private double amount;

    @SerializedName("address")
    private String address;

    @SerializedName("mobilePhone")
    private long mobilePhone;

    @SerializedName("referenceNo")
    private String referenceNo;

    @SerializedName("customerID")
    private long customerID;

    @SerializedName("memberName")
    private String memberName;

    @SerializedName("dateOfBirth")
    private String dateOfBirth;

    @SerializedName("state")
    private String state;

    @SerializedName("postal")
    private int postal;

    @SerializedName("status")
    private String status;

    @SerializedName("createDate")
    private Object createDate;

    public int getBranchId(){
        return branchId;
    }

    public double getAmount(){
        return amount;
    }

    public String getAddress(){
        return address;
    }

    public long getMobilePhone(){
        return mobilePhone;
    }

    public String getReferenceNo(){
        return referenceNo;
    }

    public long getCustomerID(){
        return customerID;
    }

    public String getMemberName(){
        return memberName;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public String getState(){
        return state;
    }

    public int getPostal(){
        return postal;
    }

    public String getStatus(){
        return status;
    }

    public Object getCreateDate(){
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreApprove that = (PreApprove) o;
        return branchId == that.branchId && Double.compare(that.amount, amount) == 0 && mobilePhone == that.mobilePhone && customerID == that.customerID && postal == that.postal && Objects.equals(address, that.address) && Objects.equals(referenceNo, that.referenceNo) && Objects.equals(memberName, that.memberName) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(state, that.state) && Objects.equals(status, that.status) && Objects.equals(createDate, that.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId, amount, address, mobilePhone, referenceNo, customerID, memberName, dateOfBirth, state, postal, status, createDate);
    }
}