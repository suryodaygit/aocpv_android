package com.suryodaybank.jyotiassisted.services;

import com.suryodaybank.jyotiassisted.models.RequestData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetroServiceInterface {

    @POST("checkappversion")
    public Call<ResponseBody> getAppVersion(@Body RequestData requestData);


}
