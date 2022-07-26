package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("Data")
	private Data data;

	public Data getData(){
		return data;
	}
}