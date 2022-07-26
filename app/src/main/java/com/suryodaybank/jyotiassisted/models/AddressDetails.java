package com.suryodaybank.jyotiassisted.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AddressDetails{

	@SerializedName("AddressDet")
	private List<AddressDetItem> addressDet;

	public List<AddressDetItem> getAddressDet(){
		return addressDet;
	}
}