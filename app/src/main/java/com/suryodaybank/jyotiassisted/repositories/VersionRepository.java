package com.suryodaybank.jyotiassisted.repositories;

import android.util.Log;
import android.widget.Toast;

import com.suryodaybank.jyotiassisted.models.RequestData;
import com.suryodaybank.jyotiassisted.services.RetroServiceInterface;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VersionRepository {


    @Inject

    RetroServiceInterface retroServiceInterface;


    @Inject
    public VersionRepository(RetroServiceInterface retroServiceInterface) {

        this.retroServiceInterface = retroServiceInterface;

    }

    public void makeAPICall(RequestData requestData){
       Call<ResponseBody> call = retroServiceInterface.getAppVersion(requestData);
       call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               if(response.isSuccessful()){
                   Log.d("Check response", response.body().toString());
               }else {
                   Log.d("Check response", "Error in API");
               }

           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {

           }
       });

    }




}
