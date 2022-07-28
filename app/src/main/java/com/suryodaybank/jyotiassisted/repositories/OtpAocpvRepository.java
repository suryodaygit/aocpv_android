package com.suryodaybank.jyotiassisted.repositories;

import com.google.gson.JsonObject;
import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.DynamicParamItem;
import com.suryodaybank.jyotiassisted.models.OtpRequest;
import com.suryodaybank.jyotiassisted.services.AocpvService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class OtpAocpvRepository {

    private final AocpvService aocpvService;

    @Inject
    public OtpAocpvRepository(AocpvService aocpvService) {
        this.aocpvService = aocpvService;
    }

    public Call<ResponseBody> sendOtp(String mobile) {
        DataModel<OtpRequest> body = new DataModel<>();
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setMobileNumber(mobile);
        otpRequest.setTemplateId("OTP454");
        DynamicParamItem item = new DynamicParamItem("otp", "");
        List<DynamicParamItem> list = new ArrayList<>();
        list.add(item);
        otpRequest.setDynamicParam(list);
        body.setData(otpRequest);
        return aocpvService.sendOtp(body);
    }

    public Call<ResponseBody> validateOtp(String otp) {
        return aocpvService.validateOtp(otp);
    }

    public Call<ResponseBody> finalAocpvCall(String applicationNo) {
        DataModel<JsonObject> body = new DataModel<>();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("applicationNo", applicationNo);
        body.setData(jsonObject);
        return aocpvService.finalAocpvCall(body);
    }
}
