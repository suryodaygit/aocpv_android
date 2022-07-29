package com.suryodaybank.jyotiassisted.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.LoginRequest;
import com.suryodaybank.jyotiassisted.models.LoginResponse;
import com.suryodaybank.jyotiassisted.models.VersionResponse;
import com.suryodaybank.jyotiassisted.repositories.VersionRepository;
import com.suryodaybank.jyotiassisted.utils.SingleLiveEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public SingleLiveEvent<Boolean> progressLiveData = new SingleLiveEvent<>();

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
                }
            }

            @Override
            public void onFailure(Call<DataModel<VersionResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void getLoginApicall(String username, String password) {
        progressLiveData.setValue(true);
        DataModel<LoginRequest> body = new DataModel<>();
        LoginRequest loginRequestData = new LoginRequest();
        loginRequestData.setUserID(username);
        loginRequestData.setPassword(password);
        body.setData(loginRequestData);

        versionRepository.getLoginAPI(body).enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<DataModel<LoginResponse>> call, Response<DataModel<LoginResponse>> response) {
                progressLiveData.setValue(false);
                if (response.isSuccessful()) {
                    loginlivedata.postValue(response.body().getData());
                } else {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
                        String line;
                        try {
                            while ((line = reader.readLine()) != null) {
                                sb.append(line);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String finallyError = sb.toString();


                    try {
                        JSONObject jsonObject = new JSONObject(finallyError);
                        if (jsonObject.has("errorMessage")) {
                            JSONArray array = jsonObject.getJSONArray("details");
                            String notAuthorizedUser = array.get(0).toString();
                            errorlivedata.postValue(notAuthorizedUser);
                        } else {
                            String lockUser = jsonObject.getJSONObject("Error").getJSONObject("data").getString("UserStatus");
                            errorlivedata.postValue(lockUser);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("login response", response.toString());
                }
            }

            @Override
            public void onFailure(Call<DataModel<LoginResponse>> call, Throwable t) {
                progressLiveData.setValue(false);
            }
        });
    }
}
