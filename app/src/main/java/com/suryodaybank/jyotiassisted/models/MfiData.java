package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class MfiData {
    @SerializedName("flowStatus")
    private String flowStatus;
    @SerializedName("applicationNo")
    private String applicationNo;
    @SerializedName("purposeOfLoan")
    private String purposeOfLoan;
    @SerializedName("loanCode")
    private String loanCode;
    @SerializedName("existingLoanPurpose")
    private String existingLoanPurpose;
    @SerializedName("mobileLinkToAadhar")
    private String mobileLinkToAadhar;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("maxEmiEligibility")
    private String maxEmiEligibility;

    public MfiData(String flowStatus, String applicationNo, String purposeOfLoan, String loanCode, String existingLoanPurpose, String mobileLinkToAadhar, String mobile, String maxEmiEligibility) {
        this.flowStatus = flowStatus;
        this.applicationNo = applicationNo;
        this.purposeOfLoan = purposeOfLoan;
        this.loanCode = loanCode;
        this.existingLoanPurpose = existingLoanPurpose;
        this.mobileLinkToAadhar = mobileLinkToAadhar;
        this.mobile = mobile;
        this.maxEmiEligibility = maxEmiEligibility;
    }
}


