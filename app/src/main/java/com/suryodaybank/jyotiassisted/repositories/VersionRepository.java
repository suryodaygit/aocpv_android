package com.suryodaybank.jyotiassisted.repositories;

import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.LoginRequest;
import com.suryodaybank.jyotiassisted.models.LoginResponse;
import com.suryodaybank.jyotiassisted.models.VersionRequest;
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

    public Call<DataModel<VersionResponse>> makeAPICall() {
        DataModel<VersionRequest> body = new DataModel<>();
        VersionRequest versionRequest = new VersionRequest();
        versionRequest.setVersion("1.0.0");
        versionRequest.setOs("Android");
        body.setData(versionRequest);
        return retroServiceInterface.getAppVersion(body);
    }

    public Call<DataModel<LoginResponse>> getLoginAPI(DataModel<LoginRequest> loginRequestModel) {
        return retroServiceInterface.getUserLogin(loginRequestModel);
    }
}
