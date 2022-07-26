package com.suryodaybank.jyotiassisted.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("CRMCustDataResponse")
	private List<CRMCustDataResponseItem> cRMCustDataResponse;

	@SerializedName("TransactionCode")
	private String transactionCode;

	public List<CRMCustDataResponseItem> getCRMCustDataResponse(){
		return cRMCustDataResponse;
	}

	public String getTransactionCode(){
		return transactionCode;
	}
}