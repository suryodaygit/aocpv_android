package com.suryodaybank.jyotiassisted.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.suryodaybank.jyotiassisted.models.CRMCustDataResponseItem;
import com.suryodaybank.jyotiassisted.models.CustomerDetailsRequest;
import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.LoginRequest;
import com.suryodaybank.jyotiassisted.models.MonthlyIncome;
import com.suryodaybank.jyotiassisted.models.PreApprove;
import com.suryodaybank.jyotiassisted.repositories.AocpvRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class AocpvViewModel extends ViewModel {

    private static final String TAG = "AocpvViewModel";

    private final AocpvRepository aocpvRepository;
    public MutableLiveData<List<PreApprove>> preApprovesLivedata = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<List<MonthlyIncome>> monthlyIncomeLivedata = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<Integer> pageNoLivedata = new MutableLiveData<>(1);
    public MutableLiveData<String> searchQueryLiveData = new MutableLiveData<>("");
    public MutableLiveData<CRMCustDataResponseItem> customerQueryLiveData = new MutableLiveData<CRMCustDataResponseItem>();

    @Inject
    public AocpvViewModel(AocpvRepository aocpvRepository) {
        this.aocpvRepository = aocpvRepository;
        Log.d(TAG, "AocpvViewModel: Init");
        getPreApproveList();
        getCustomerPersonalDetails();
    }

    private void getCustomerPersonalDetails() {
        DataModel<CustomerDetailsRequest> body = new DataModel<>();
        CustomerDetailsRequest customerDetailsRequest = new CustomerDetailsRequest();
        customerDetailsRequest.setMobileNo("");
        customerDetailsRequest.setAadhaarNo("");
        customerDetailsRequest.setPanNo("");
        customerDetailsRequest.setCustomerNo("180268778");
        customerDetailsRequest.setBranchCode("");

        body.setData(customerDetailsRequest);
        aocpvRepository.getUserDetail(body).enqueue(new Callback<DataModel<CRMCustDataResponseItem>>() {
            @Override
            public void onResponse(Call<DataModel<CRMCustDataResponseItem>> call, Response<DataModel<CRMCustDataResponseItem>> response) {
                if (response.isSuccessful()) {
                    customerQueryLiveData.postValue(response.body().getData());

                } else {
                    //Error
                    Log.d(TAG, "onResponse: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<DataModel<CRMCustDataResponseItem>> call, Throwable t) {

            }
        });
    }

    private void getPreApproveList() {
        aocpvRepository.getPreApproveList().enqueue(new Callback<List<PreApprove>>() {
            @Override
            public void onResponse(Call<List<PreApprove>> call, Response<List<PreApprove>> response) {
                if (response.isSuccessful()) {
                    preApprovesLivedata.setValue(response.body());
                } else {
                    //Error
                    Log.d(TAG, "onResponse: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<PreApprove>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void addMonthlyIncome(MonthlyIncome monthlyIncome) {
        List<MonthlyIncome> monthlyIncomes = monthlyIncomeLivedata.getValue();
        monthlyIncomes.add(monthlyIncome);
        monthlyIncomeLivedata.setValue(monthlyIncomes);
    }
}
