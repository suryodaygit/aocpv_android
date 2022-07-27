package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class IncomeDetails {
    @SerializedName("member")
    String member;
    @SerializedName("earning")
    String earning;
    @SerializedName("occupation")
    String occupation;
    @SerializedName("primarySourceOfIncome")
    String primarySourceOfIncome;
    @SerializedName("securedLoan")
    String securedLoan;
    @SerializedName("unsecuredLoan")
    String unsecuredLoan;
    @SerializedName("monthlyIncome")
    String monthlyIncome;
    @SerializedName("monthlyLoanEmi")
    String monthlyLoanEmi;
}
