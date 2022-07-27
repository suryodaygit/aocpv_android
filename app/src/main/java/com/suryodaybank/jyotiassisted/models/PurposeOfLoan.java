package com.suryodaybank.jyotiassisted.models;

public class PurposeOfLoan {
  String purposeCode;
  String purposeOfLoan;

  public PurposeOfLoan(String purposeCode, String purposeOfLoan) {
    this.purposeCode = purposeCode;
    this.purposeOfLoan = purposeOfLoan;
  }

  public String getPurposeCode() {
    return purposeCode;
  }

  public String getPurposeOfLoan() {
    return purposeOfLoan;
  }
}
