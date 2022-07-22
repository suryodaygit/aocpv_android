package com.suryodaybank.jyotiassisted.services;

import com.suryodaybank.jyotiassisted.models.LoginResponse;
import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.LoginRequest;
import com.suryodaybank.jyotiassisted.models.VersionRequest;
import com.suryodaybank.jyotiassisted.models.VersionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetroServiceInterface {

    @POST("v1/checkappversion")
    Call<DataModel<VersionResponse>> getAppVersion(@Body DataModel<VersionRequest> requestData);

    @POST("v1/netbanking/validate/user")
    Call<DataModel<LoginResponse>> getUserLogin(@Body DataModel<LoginRequest> loginRequestModel);
}
