package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class CustomerDetailsRequest {
	@SerializedName("MobileNo")
	private String mobileNo;
	@SerializedName("AadhaarNo")
	private String aadhaarNo;
	@SerializedName("PanNo")
	private String panNo;
	@SerializedName("CustomerNo")
	private String customerNo;
	@SerializedName("BranchCode")
	private String branchCode;

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getMobileNo(){
		return mobileNo;
	}

	public String getAadhaarNo(){
		return aadhaarNo;
	}

	public String getPanNo(){
		return panNo;
	}

	public String getCustomerNo(){
		return customerNo;
	}

	public String getBranchCode(){
		return branchCode;
	}
}
