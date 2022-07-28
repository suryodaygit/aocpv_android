package com.suryodaybank.jyotiassisted.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OtpRequest {

    @SerializedName("EmailId")
    private String emailId;

    @SerializedName("MobileNumber")
    private String mobileNumber;

    @SerializedName("DynamicParam")
    private List<DynamicParamItem> dynamicParam;

    @SerializedName("CustomerId")
    private String customerId;

    @SerializedName("IntFlag")
    private String intFlag;

    @SerializedName("TemplateId")
    private String templateId;

    public String getEmailId() {
        return emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public List<DynamicParamItem> getDynamicParam() {
        return dynamicParam;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getIntFlag() {
        return intFlag;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setDynamicParam(List<DynamicParamItem> dynamicParam) {
        this.dynamicParam = dynamicParam;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setIntFlag(String intFlag) {
        this.intFlag = intFlag;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}