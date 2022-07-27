package com.suryodaybank.jyotiassisted.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

    @SerializedName("CRMCustDataResponse")
    private List<CRMCustDataResponseItem> cRMCustDataResponse;

    public List<CRMCustDataResponseItem> getCRMCustDataResponse(){
        return cRMCustDataResponse;
    }


}