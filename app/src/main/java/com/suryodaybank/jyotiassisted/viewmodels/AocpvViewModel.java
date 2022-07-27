package com.suryodaybank.jyotiassisted.viewmodels;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.suryodaybank.jyotiassisted.R;
import com.suryodaybank.jyotiassisted.models.CRMCustDataResponseItem;
import com.suryodaybank.jyotiassisted.models.CustomerDetailsRequest;
import com.suryodaybank.jyotiassisted.models.CustomerSaveData;
import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.MfiData;
import com.suryodaybank.jyotiassisted.models.MonthlyIncome;
import com.suryodaybank.jyotiassisted.models.PreApprove;
import com.suryodaybank.jyotiassisted.models.SaveExpenseRequest;
import com.suryodaybank.jyotiassisted.models.SaveIncomeRequest;
import com.suryodaybank.jyotiassisted.models.UtilityAddressItem;
import com.suryodaybank.jyotiassisted.models.UtilityDataRequest;
import com.suryodaybank.jyotiassisted.models.ValidationData;
import com.suryodaybank.jyotiassisted.models.ValidationRequestModel;
import com.suryodaybank.jyotiassisted.models.ValidationResponse;
import com.suryodaybank.jyotiassisted.repositories.AocpvRepository;
import com.suryodaybank.jyotiassisted.utils.PreApproveStatus;
import com.suryodaybank.jyotiassisted.utils.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class AocpvViewModel extends ViewModel {

    private static final String TAG = "AocpvViewModel";

    private final AocpvRepository aocpvRepository;
    public SingleLiveEvent<Void> nextPage = new SingleLiveEvent<>();
    public MutableLiveData<List<PreApprove>> preApprovesLivedata = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<List<MonthlyIncome>> monthlyIncomeLivedata = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<Integer> pageNoLivedata = new MutableLiveData<>(1);
    public MutableLiveData<String> searchQueryLiveData = new MutableLiveData<>("");
    public MutableLiveData<List<CRMCustDataResponseItem>> customerQueryLiveData = new MutableLiveData<List<CRMCustDataResponseItem>>();
    public MutableLiveData<ValidationData> validationDataMutableLiveData = new MutableLiveData<>();

    public SingleLiveEvent<Void> getMonthlyIncomeData = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> getMonthlyExpenseData = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> getCustomerDetails = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> getUtilityDetails = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> getMfiClassificationData = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> getValidationData = new SingleLiveEvent<>();

    //Calculation data for monthly balance
    public long totalMonthlyIncome = 0;
    public long totalMonthlyEmi = 0;

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
        aocpvRepository.getUserDetail(body).enqueue(new Callback<com.suryodaybank.jyotiassisted.models.Response>() {
            @Override
            public void onResponse(Call<com.suryodaybank.jyotiassisted.models.Response> call, Response<com.suryodaybank.jyotiassisted.models.Response> response) {
                if (response.isSuccessful()) {
                    customerQueryLiveData.setValue(response.body().getData().getCRMCustDataResponse());
                } else {
                    Log.d(TAG, "onResponse: " + response.errorBody().toString());
                    customerQueryLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<com.suryodaybank.jyotiassisted.models.Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getUtilityDetails() {
        DataModel<UtilityAddressItem> body = new DataModel<>();

    }

    private void getPreApproveList() {
        aocpvRepository.getPreApproveList(PreApproveStatus.INITIATED.status).enqueue(new Callback<List<PreApprove>>() {
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

    public void notInterestedStatusUpdate(Context context, PreApprove preApprove) {
        aocpvRepository.notInterestedStatus(preApprove.getCustomerID()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    List<PreApprove> dataList = preApprovesLivedata.getValue();
                    dataList.remove(preApprove);
                    preApprovesLivedata.setValue(dataList);
                } else {
                    Toast.makeText(context, R.string.something_is_wrong, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, R.string.something_is_wrong, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void saveData(int index) {
        switch (index) {
            case 0:
                //Save customer details
                getCustomerDetails.call();
                break;
            case 1:
                //Save utility details
                getUtilityDetails.call();
                break;
            case 2:
                //Save monthly income details
                getMonthlyIncomeData.call();
                break;
            case 3:
                getMonthlyExpenseData.call();
                break;
            case 4:
                getMfiClassificationData.call();
                break;
        }
    }

    public void callMonthlyExpenseApi(SaveExpenseRequest saveExpenseRequest) {
        saveExpenseRequest.setApplicationNo("12345681"); //TODO: Need to generate random
        aocpvRepository.saveExpenseDetails(saveExpenseRequest).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    nextPage.call();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void callOwnershipDetailAPI(UtilityDataRequest utilityDataRequest) {
        utilityDataRequest.setApplicationNo("12345681");//TODO: Need to generate random
        aocpvRepository.saveUtilityDetails(utilityDataRequest).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    nextPage.call();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void callPersonalDetailAPI(CustomerSaveData customerSaveData) {
        customerSaveData.setApplicationNo("12345681"); //TODO: Need to generate random
        aocpvRepository.saveUserDetail(customerSaveData).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    nextPage.call();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void callMonthlyIncomeApi(SaveIncomeRequest saveIncomeRequest) {
        saveIncomeRequest.setApplicationNo("12345681"); //TODO: Need to generate random
        aocpvRepository.saveIncomeDetails(saveIncomeRequest).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    nextPage.call();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void callMFIClassification(Context mContext, MfiData mfiData) {
        aocpvRepository.mfiClassification(mfiData).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    String response1 = String.valueOf(response.body());
                    Toast.makeText(mContext, "data updated", Toast.LENGTH_SHORT).show();
                    Log.d("mfi", response1);
                    nextPage.call();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void callValidationData(Context mContext, ValidationRequestModel validationRequestModel) {
        aocpvRepository.validationData(validationRequestModel).enqueue(new Callback<ValidationResponse>() {
            @Override
            public void onResponse(Call<ValidationResponse> call, Response<ValidationResponse> response) {
                if (response.isSuccessful()) {
                    ValidationResponse validationResponse = new ValidationResponse();
                    validationResponse = response.body();
                    validationDataMutableLiveData.setValue(validationResponse.getData());
                    if (response.isSuccessful()) {
                        nextPage.call();
                    }
                }
            }

            @Override
            public void onFailure(Call<ValidationResponse> call, Throwable t) {
                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
