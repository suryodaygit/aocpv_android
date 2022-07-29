package com.suryodaybank.jyotiassisted.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.suryodaybank.jyotiassisted.repositories.OtpAocpvRepository;
import com.suryodaybank.jyotiassisted.utils.SingleLiveEvent;
import com.suryodaybank.jyotiassisted.utils.Utils;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class OtpAocpvViewModel extends ViewModel {

    private final OtpAocpvRepository otpAocpvRepository;

    public SingleLiveEvent<String> messageLiveData = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> finalSuccessCall = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> showProgressDialog = new SingleLiveEvent<>();

    @Inject
    public OtpAocpvViewModel(OtpAocpvRepository otpAocpvRepository) {
        this.otpAocpvRepository = otpAocpvRepository;
    }

    public void sendOtp(String mobile) {
        showProgressDialog.setValue(true);
        otpAocpvRepository.sendOtp(mobile).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                showProgressDialog.setValue(false);
                if (!response.isSuccessful()) {
                    messageLiveData.setValue(Utils.getErrorMessage(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showProgressDialog.setValue(false);
                t.printStackTrace();
            }
        });
    }

    public void validateOtp(String otp, String applicationNo) {
        showProgressDialog.setValue(true);
        otpAocpvRepository.validateOtp(otp).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    finalAocpvCall(applicationNo);
                } else {
                    showProgressDialog.setValue(false);
                    String message = Utils.getErrorMessage(response.errorBody());
                    messageLiveData.setValue(message);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showProgressDialog.setValue(false);
                t.printStackTrace();
            }
        });
    }

    private void finalAocpvCall(String applicationNo) {
        otpAocpvRepository.finalAocpvCall(applicationNo).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                showProgressDialog.setValue(false);
                if (response.isSuccessful()) {
                    finalSuccessCall.call();
                } else {
                    messageLiveData.setValue("Something is wrong in final call.");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showProgressDialog.setValue(false);
                t.printStackTrace();
            }
        });
    }
}
