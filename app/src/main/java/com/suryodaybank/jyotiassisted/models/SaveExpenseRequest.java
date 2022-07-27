package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class SaveExpenseRequest {

    @SerializedName("medical")
    private String medical;

    @SerializedName("other")
    private String other;

    @SerializedName("total")
    private String total;

    @SerializedName("education")
    private String education;

    @SerializedName("applicationNo")
    private String applicationNo;

    @SerializedName("monthlyBalance")
    private String monthlyBalance;

    @SerializedName("foodAndUtility")
    private String foodAndUtility;

    @SerializedName("customerClassification")
    private String customerClassification;

    @SerializedName("flowStatus")
    private final String flowStatus = "MED";

    @SerializedName("rent")
    private String rent;

    @SerializedName("transportation")
    private String transportation;

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public String getMedical() {
        return medical;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOther() {
        return other;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducation() {
        return education;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setMonthlyBalance(String monthlyBalance) {
        this.monthlyBalance = monthlyBalance;
    }

    public String getMonthlyBalance() {
        return monthlyBalance;
    }

    public void setFoodAndUtility(String foodAndUtility) {
        this.foodAndUtility = foodAndUtility;
    }

    public String getFoodAndUtility() {
        return foodAndUtility;
    }

    public void setCustomerClassification(String customerClassification) {
        this.customerClassification = customerClassification;
    }

    public String getCustomerClassification() {
        return customerClassification;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getRent() {
        return rent;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getTransportation() {
        return transportation;
    }
}