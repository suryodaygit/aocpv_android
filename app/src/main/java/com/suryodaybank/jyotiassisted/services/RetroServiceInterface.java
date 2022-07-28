package com.suryodaybank.jyotiassisted.services;

import com.suryodaybank.jyotiassisted.models.LoginResponse;
import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.LoginRequest;
import com.suryodaybank.jyotiassisted.models.VersionRequest;
import com.suryodaybank.jyotiassisted.models.VersionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetroServiceInterface {

    @POST("v1/checkappversion")
    Call<DataModel<VersionResponse>> getAppVersion(@Body DataModel<VersionRequest> requestData);

    @Headers({
            "X-Correlation-ID: 1234",
            "X-From-ID: CB",
            "X-To-ID: MB",
            "X-Transaction-ID: EabeDcEE-db3c-BddD-CbD7-4bAA992c75d4",
            "X-User-ID: 30639",
            "X-Request-ID: AOCPV",
            "Content-Type: application/json",
            "Cookie: : HttpOnly; : HttpOnly; : HttpOnly"
    })
    @POST("aocpv/v1/netbanking/validate/user")
    Call<DataModel<LoginResponse>> getUserLogin(@Body DataModel<LoginRequest> loginRequestModel);
}
