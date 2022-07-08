package com.suryodaybank.jyotiassisted.services;

import com.suryodaybank.jyotiassisted.models.LoginRequestModel;
import com.suryodaybank.jyotiassisted.models.LoginResponseModel;
import com.suryodaybank.jyotiassisted.models.RequestData;
import com.suryodaybank.jyotiassisted.models.VersionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetroServiceInterface {

    @POST("checkappversion")
     Call<VersionResponse> getAppVersion(@Body RequestData requestData);

    @POST("netbanking/validate/user")
    Call<LoginResponseModel> getUserLogin(@Body LoginRequestModel loginRequestModel);


}
