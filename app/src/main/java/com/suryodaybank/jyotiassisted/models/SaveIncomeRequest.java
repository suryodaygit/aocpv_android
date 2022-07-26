package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaveIncomeRequest {

    @SerializedName("totalMonthlyEmi")
    private String totalMonthlyEmi;

    @SerializedName("totalMonthlyIncome")
    private String totalMonthlyIncome;

    @SerializedName("applicationNo")
    private String applicationNo;

    @SerializedName("flowStatus")
    private final String flowStatus = "ID";

    @SerializedName("incomeDetails")
    private List<MonthlyIncome> incomeDetails;

    public String getTotalMonthlyEmi() {
        return totalMonthlyEmi;
    }

    public String getTotalMonthlyIncome() {
        return totalMonthlyIncome;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public List<MonthlyIncome> getIncomeDetails() {
        return incomeDetails;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public void setIncomeDetails(List<MonthlyIncome> incomeDetails) {
        this.incomeDetails = incomeDetails;
    }

    public void setTotalMonthlyEmi(String totalMonthlyEmi) {
        this.totalMonthlyEmi = totalMonthlyEmi;
    }

    public void setTotalMonthlyIncome(String totalMonthlyIncome) {
        this.totalMonthlyIncome = totalMonthlyIncome;
    }
}