package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValidationData {
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
    List<AddressData> address;
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
    List<OwnerAddress> ownerAddress;
    @SerializedName("otherAssets")
    List<String> otherAssets;
    @SerializedName("vintage")
    String vintage;
    @SerializedName("incomeDetails")
    List<IncomeDetails> incomeDetails;
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

    public String getApplicationNo() {
        return applicationNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public List<AddressData> getAddress() {
        return address;
    }

    public String getHouseOwnership() {
        return houseOwnership;
    }

    public String getRoofType() {
        return roofType;
    }

    public String getResidenceStability() {
        return residenceStability;
    }

    public String getUtilityBill() {
        return utilityBill;
    }

    public String getRelationshipWithOwner() {
        return relationshipWithOwner;
    }

    public List<OwnerAddress> getOwnerAddress() {
        return ownerAddress;
    }

    public List<String> getOtherAssets() {
        return otherAssets;
    }

    public String getVintage() {
        return vintage;
    }

    public List<IncomeDetails> getIncomeDetails() {
        return incomeDetails;
    }

    public String getTotalMonthlyIncome() {
        return totalMonthlyIncome;
    }

    public String getTotalMonthlyEmi() {
        return totalMonthlyEmi;
    }

    public String getFoodAndUtility() {
        return foodAndUtility;
    }

    public String getRent() {
        return rent;
    }

    public String getTransportation() {
        return transportation;
    }

    public String getMedical() {
        return medical;
    }

    public String getEducation() {
        return education;
    }

    public String getOther() {
        return other;
    }

    public String getTotal() {
        return total;
    }

    public String getMonthlyBalance() {
        return monthlyBalance;
    }

    public String getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public String getExistingLoanPurpose() {
        return existingLoanPurpose;
    }

    public String getMobileLinkToAadhar() {
        return mobileLinkToAadhar;
    }

    public String getMobile2() {
        return mobile2;
    }

    public String getMaxEmieligibility() {
        return maxEmieligibility;
    }

    public String getCustomerClassification() {
        return customerClassification;
    }

    public String getFlowStatus() {
        return flowStatus;
    }

    public String getBuisness_photo() {
        return buisness_photo;
    }

    public String getUtilityBillPhoto() {
        return utilityBillPhoto;
    }
}
