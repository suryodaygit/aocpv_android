package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UtilityDataRequest {

    @SerializedName("houseOwnership")
    private String houseOwnership;

    @SerializedName("utilityBillPhoto")
    private String utilityBillPhoto;

    @SerializedName("address")
    private List<UtilityAddressItem> address;

    @SerializedName("otherAssets")
    private List<String> otherAssets;

    @SerializedName("utilityBill")
    private String utilityBill;

    @SerializedName("applicationNo")
    private String applicationNo;

    @SerializedName("roofType")
    private String roofType;

    @SerializedName("vintage")
    private String vintage;

    @SerializedName("buisness_photo")
    private String buisnessPhoto;

    @SerializedName("flowStatus")
    private String flowStatus;

    @SerializedName("residenceStability")
    private String residenceStability;

    @SerializedName("relationshipWithOwner")
    private String relationshipWithOwner;

    public String getHouseOwnership(){
        return houseOwnership;
    }

    public String getUtilityBillPhoto(){
        return utilityBillPhoto;
    }

    public List<UtilityAddressItem> getAddress(){
        return address;
    }

    public List<String> getOtherAssets(){
        return otherAssets;
    }

    public String getUtilityBill(){
        return utilityBill;
    }

    public String getApplicationNo(){
        return applicationNo;
    }

    public String getRoofType(){
        return roofType;
    }

    public String getVintage(){
        return vintage;
    }

    public String getBuisnessPhoto(){
        return buisnessPhoto;
    }

    public String getFlowStatus(){
        return flowStatus;
    }

    public String getResidenceStability(){
        return residenceStability;
    }

    public String getRelationshipWithOwner(){
        return relationshipWithOwner;
    }
}
