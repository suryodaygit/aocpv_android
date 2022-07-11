package com.suryodaybank.jyotiassisted.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.suryodaybank.jyotiassisted.models.LoginResponse;
import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.LoginRequest;
import com.suryodaybank.jyotiassisted.models.VersionResponse;
import com.suryodaybank.jyotiassisted.repositories.VersionRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final VersionRepository versionRepository;

    public MutableLiveData<String> livedata = new MutableLiveData();
    public MutableLiveData<LoginResponse> loginlivedata = new MutableLiveData();
    public MutableLiveData<String> errorlivedata = new MutableLiveData();

    @Inject
    public LoginViewModel(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    public void makeVersionCall() {
        versionRepository.makeAPICall().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<DataModel<VersionResponse>> call, Response<DataModel<VersionResponse>> response) {
                if (response.isSuccessful()) {
                    if (!response.body().getData().getAllow()) {
                        livedata.postValue(response.body().getData().getMessage());
                    }
                } else {
                    livedata.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataModel<VersionResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void getLoginApicall(String username, String password) {
        DataModel<LoginRequest> body = new DataModel<>();
        LoginRequest loginRequestData = new LoginRequest();
        loginRequestData.setUserID(username);
        loginRequestData.setPassword(password);
        body.setData(loginRequestData);

        versionRepository.getLoginAPI(body).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<DataModel<LoginResponse>> call, Response<DataModel<LoginResponse>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getError() == null) {
                        loginlivedata.postValue(response.body().getData());
                    } else {
                        errorlivedata.postValue(response.body().getError());
                    }
                } else {
                    livedata.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataModel<LoginResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
