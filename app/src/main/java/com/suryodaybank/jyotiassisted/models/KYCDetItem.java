package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class KYCDetItem{

	@SerializedName("ISSUEDATE")
	private String iSSUEDATE;

	@SerializedName("DOCUMENTNUMBER")
	private String dOCUMENTNUMBER;

	@SerializedName("CIFNUMBER")
	private String cIFNUMBER;

	@SerializedName("DOCUMENTTYPE")
	private String dOCUMENTTYPE;

	@SerializedName("EXPIRYDATE")
	private String eXPIRYDATE;

	public String getISSUEDATE(){
		return iSSUEDATE;
	}

	public String getDOCUMENTNUMBER(){
		return dOCUMENTNUMBER;
	}

	public String getCIFNUMBER(){
		return cIFNUMBER;
	}

	public String getDOCUMENTTYPE(){
		return dOCUMENTTYPE;
	}

	public String getEXPIRYDATE(){
		return eXPIRYDATE;
	}
}