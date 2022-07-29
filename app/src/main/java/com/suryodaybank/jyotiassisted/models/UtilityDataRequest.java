package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UtilityDataRequest {

    @SerializedName("houseOwnership")
    private String houseOwnership;

    public void setHouseOwnership(String houseOwnership) {
        this.houseOwnership = houseOwnership;
    }

    public void setUtilityBillPhoto(String utilityBillPhoto) {
        this.utilityBillPhoto = utilityBillPhoto;
    }

    public void setAddress(List<UtilityAddressItem> address) {
        this.address = address;
    }

    public void setOtherAssets(List<String> otherAssets) {
        this.otherAssets = otherAssets;
    }

    public void setUtilityBill(String utilityBill) {
        this.utilityBill = utilityBill;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }

    public void setBuisnessPhoto(String buisnessPhoto) {
        this.buisnessPhoto = buisnessPhoto;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public void setResidenceStability(String residenceStability) {
        this.residenceStability = residenceStability;
    }

    public void setRelationshipWithOwner(String relationshipWithOwner) {
        this.relationshipWithOwner = relationshipWithOwner;
    }

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

    @SerializedName("bLat")
    private String bLat;

    @SerializedName("bLong")
    private String bLong;

    @SerializedName("bAddress")
    private String bAddress;

    public String getbLat() {
        return bLat;
    }

    public void setbLat(String bLat) {
        this.bLat = bLat;
    }

    public String getbLong() {
        return bLong;
    }

    public void setbLong(String bLong) {
        this.bLong = bLong;
    }

    public String getbAddress() {
        return bAddress;
    }

    public void setbAddress(String bAddress) {
        this.bAddress = bAddress;
    }

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