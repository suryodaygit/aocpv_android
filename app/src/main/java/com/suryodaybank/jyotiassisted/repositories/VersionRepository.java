package com.suryodaybank.jyotiassisted.repositories;

import com.suryodaybank.jyotiassisted.models.LoginRequestModel;
import com.suryodaybank.jyotiassisted.models.LoginResponseModel;
import com.suryodaybank.jyotiassisted.models.RequestData;
import com.suryodaybank.jyotiassisted.models.VersionResponse;
import com.suryodaybank.jyotiassisted.services.RetroServiceInterface;

import javax.inject.Inject;

import retrofit2.Call;

public class VersionRepository {

    private final RetroServiceInterface retroServiceInterface;

    @Inject
    public VersionRepository(RetroServiceInterface retroServiceInterface) {
        this.retroServiceInterface = retroServiceInterface;
    }

    public Call<VersionResponse> makeAPICall(RequestData requestData) {
        return retroServiceInterface.getAppVersion(requestData);

    }

    public Call<LoginResponseModel> getLoginAPI(LoginRequestModel loginRequestModel){
        return retroServiceInterface.getUserLogin(loginRequestModel);
    }
}
