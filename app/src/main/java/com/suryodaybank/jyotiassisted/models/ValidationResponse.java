package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class ValidationResponse {
    @SerializedName("applicationNo")
    String applicationNo;
    @SerializedName("customerId")
    String customerId;
    @SerializedName("name")
    String name;
    @SerializedName("mobileNo")
    String mobileNo;
    @SerializedName("dateOfBirth")
    String dateOfBirth;
    @SerializedName("address")
    AddressData address;
    @SerializedName("houseOwnership")
    String houseOwnership;
    @SerializedName("roofType")
    String roofType;
    @SerializedName("residenceStability")
    String residenceStability;
    @SerializedName("utilityBill")
    String utilityBill;
    @SerializedName("relationshipWithOwner")
    String relationshipWithOwner;
    @SerializedName("ownerAddress")
    AddressData ownerAddress;
    @SerializedName("otherAssets")
    OtherAssets otherAssets;
    @SerializedName("vintage")
    String vintage;
    @SerializedName("incomeDetails")
    IncomeDetails incomeDetails;
    @SerializedName("totalMonthlyIncome")
    String totalMonthlyIncome;
    @SerializedName("totalMonthlyEmi")
    String totalMonthlyEmi;
    @SerializedName("foodAndUtility")
    String foodAndUtility;
    @SerializedName("rent")
    String rent;
    @SerializedName("transportation")
    String transportation;
    @SerializedName("medical")
    String medical;
    @SerializedName("education")
    String education;
    @SerializedName("other")
    String other;
    @SerializedName("total")
    String total;
    @SerializedName("monthlyBalance")
    String monthlyBalance;
    @SerializedName("purposeOfLoan")
    String purposeOfLoan;
    @SerializedName("existingLoanPurpose")
    String existingLoanPurpose;
    @SerializedName("mobileLinkToAadhar")
    String mobileLinkToAadhar;
    @SerializedName("mobile2")
    String mobile2;
    @SerializedName("maxEmieligibility")
    String maxEmieligibility;
    @SerializedName("customerClassification")
    String customerClassification;
    @SerializedName("flowStatus")
    String flowStatus;
    @SerializedName("buisness_photo")
    String buisness_photo;
    @SerializedName("utilityBillPhoto")
    String utilityBillPhoto;
    @SerializedName("status")
    String status;

}
