package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class MonthlyIncome {

    @SerializedName("member")
    private String familyMember;
    @SerializedName("earning")
    private String earningMember;
    @SerializedName("occupation")
    private String occupation;
    @SerializedName("primarySourceOfIncome")
    private String sourceOfIncome;
    @SerializedName("securedLoan")
    private String securedLoan;
    @SerializedName("unsecuredLoan")
    private String unsecuredLoan;
    @SerializedName("monthlyIncome")
    private long monthlyIncome;
    @SerializedName("monthlyLoanEmi")
    private long monthlyLoanEmi;
    @SerializedName("occCode")
    private String occCode;

    public String getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(String familyMember) {
        this.familyMember = familyMember;
    }

    public String getEarningMember() {
        return earningMember;
    }

    public void setEarningMember(String earningMember) {
        this.earningMember = earningMember;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSourceOfIncome() {
        return sourceOfIncome;
    }

    public void setSourceOfIncome(String sourceOfIncome) {
        this.sourceOfIncome = sourceOfIncome;
    }

    public String getSecuredLoan() {
        return securedLoan;
    }

    public void setSecuredLoan(String securedLoan) {
        this.securedLoan = securedLoan;
    }

    public String getUnsecuredLoan() {
        return unsecuredLoan;
    }

    public void setUnsecuredLoan(String unsecuredLoan) {
        this.unsecuredLoan = unsecuredLoan;
    }

    public long getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public long getMonthlyLoanEmi() {
        return monthlyLoanEmi;
    }

    public void setMonthlyLoanEmi(long monthlyLoanEmi) {
        this.monthlyLoanEmi = monthlyLoanEmi;
    }

    public void setOccCode(String occCode) {
        this.occCode = occCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyIncome that = (MonthlyIncome) o;
        return Objects.equals(familyMember, that.familyMember) && Objects.equals(earningMember, that.earningMember) && Objects.equals(occupation, that.occupation) && Objects.equals(sourceOfIncome, that.sourceOfIncome) && Objects.equals(securedLoan, that.securedLoan) && Objects.equals(unsecuredLoan, that.unsecuredLoan) && Objects.equals(monthlyIncome, that.monthlyIncome) && Objects.equals(monthlyLoanEmi, that.monthlyLoanEmi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyMember, earningMember, occupation, sourceOfIncome, securedLoan, unsecuredLoan, monthlyIncome, monthlyLoanEmi);
    }
}
