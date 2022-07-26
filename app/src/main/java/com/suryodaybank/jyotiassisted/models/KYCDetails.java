package com.suryodaybank.jyotiassisted.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class KYCDetails{

	@SerializedName("KYCDet")
	private List<KYCDetItem> kYCDet;

	public List<KYCDetItem> getKYCDet(){
		return kYCDet;
	}
}