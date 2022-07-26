package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class GSTDetails{

	@SerializedName("GSTIN")
	private String gSTIN;

	public String getGSTIN(){
		return gSTIN;
	}
}