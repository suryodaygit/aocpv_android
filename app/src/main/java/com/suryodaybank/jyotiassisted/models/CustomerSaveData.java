package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerSaveData {

    @SerializedName("image")
    private String image;

    public void setImage(String image) {
        this.image = image;
    }

    public void setAddress(List<AddressItem> address) {
        this.address = address;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    @SerializedName("address")
    private List<AddressItem> address;

    @SerializedName("applicationNo")
    private String applicationNo;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    @SerializedName("branchid")
    private String branchId;

    @SerializedName("customerId")
    private String customerId;

    @SerializedName("name")
    private String name;

    @SerializedName("dateOfBirth")
    private String dateOfBirth;

    @SerializedName("mobileNo")
    private String mobileNo;

    @SerializedName("flowStatus")
    private String flowStatus;

    public String getImage(){
        return image;
    }

    public List<AddressItem> getAddress(){
        return address;
    }

    public String getApplicationNo(){
        return applicationNo;
    }

    public String getCustomerId(){
        return customerId;
    }

    public String getName(){
        return name;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public String getMobileNo(){
        return mobileNo;
    }

    public String getFlowStatus(){
        return flowStatus;
    }
}