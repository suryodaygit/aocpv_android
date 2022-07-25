package com.suryodaybank.jyotiassisted.services;

import com.google.gson.JsonObject;
import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.PreApprove;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AocpvService {

    @POST("aocpv/v1/getByIdAndStatus")
    Call<List<PreApprove>> getPreApprovalList(@Body DataModel<JsonObject> body);

    @POST("aocpv/v1/updateStatus")
    Call<ResponseBody> updateStatus(@Body DataModel<JsonObject> body);
}
