package com.suryodaybank.jyotiassisted.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.suryodaybank.jyotiassisted.models.Data;
import com.suryodaybank.jyotiassisted.models.LoginRequestData;
import com.suryodaybank.jyotiassisted.models.LoginRequestModel;
import com.suryodaybank.jyotiassisted.models.LoginResponseModel;
import com.suryodaybank.jyotiassisted.models.RequestData;
import com.suryodaybank.jyotiassisted.models.VersionData;
import com.suryodaybank.jyotiassisted.models.VersionResponse;
import com.suryodaybank.jyotiassisted.repositories.VersionRepository;
import com.suryodaybank.jyotiassisted.services.RetroServiceInterface;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class LoginViewModel extends ViewModel {


    RetroServiceInterface retroServiceInterface;
    private final VersionRepository versionRepository;
    String username, password;
    Boolean isLogin;

    public MutableLiveData<String> livedata = new MutableLiveData();
    public MutableLiveData<Data> loginlivedata = new MutableLiveData();
    public MutableLiveData<String> errorlivedata = new MutableLiveData();

    @Inject
    public LoginViewModel(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;


    }
    public MutableLiveData getLoginLivedata(){
        return loginlivedata;
    }

    public MutableLiveData getLivedata() {
        return livedata;
    }

    public void makeVersionCall() {
        RequestData requestData = new RequestData();
        VersionData versionData = new VersionData();
        versionData.setVersion("1.0.0");
        versionData.setOs("Android");
        requestData.setData(versionData);
        versionRepository.makeAPICall(requestData).enqueue(new Callback<VersionResponse>() {
            @Override
            public void onResponse(Call<VersionResponse> call, Response<VersionResponse> response) {
                if(response.isSuccessful()){
                    if(!response.body().getData().getAllow()) {
                        livedata.postValue(response.body().getData().getMessage());
                    }

                }else {
                    livedata.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<VersionResponse> call, Throwable t) {

            }
        });


        }



    public void getLoginApicall(String username, String password) {
        this.username = username;
        this.password = password;
        LoginRequestModel loginRequestModel = new LoginRequestModel();
        LoginRequestData loginRequestData = new LoginRequestData();
        loginRequestData.setUserID(username);
        loginRequestData.setPassword(password);
        loginRequestModel.setData(loginRequestData);

        versionRepository.getLoginAPI(loginRequestModel).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

                if (response.isSuccessful()) {

                    if(response.body().getError()==null) {
                        loginlivedata.postValue(response.body().getData());
                    }else {
                        errorlivedata.postValue(response.body().getError());
                    }


                } else {
                    livedata.postValue(null);


                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {

            }
        });



    }
}
