package com.suryodaybank.jyotiassisted.viewmodels;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.suryodaybank.jyotiassisted.R;
import com.suryodaybank.jyotiassisted.models.PreApprove;
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
public class PreApproveViewModel extends ViewModel {

    private static final String TAG = "PreApproveViewModel";

    private final AocpvRepository aocpvRepository;

    public MutableLiveData<List<PreApprove>> preApprovesLivedata = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<String> searchQueryLiveData = new MutableLiveData<>("");

    public SingleLiveEvent<Boolean> showProgressDialog = new SingleLiveEvent<>();

    @Inject
    public PreApproveViewModel(AocpvRepository aocpvRepository) {
        this.aocpvRepository = aocpvRepository;
        getPreApproveList();
    }

    public void getPreApproveList() {
        showProgressDialog.setValue(true);
        aocpvRepository.getPreApproveList(PreApproveStatus.INITIATED.status).enqueue(new Callback<List<PreApprove>>() {
            @Override
            public void onResponse(Call<List<PreApprove>> call, Response<List<PreApprove>> response) {
                if (response.isSuccessful()) {
                    showProgressDialog.setValue(false);
                    preApprovesLivedata.setValue(response.body());
                } else {
                    //Error
                    Log.d(TAG, "onResponse: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<PreApprove>> call, Throwable t) {
                showProgressDialog.setValue(false);
                t.printStackTrace();
            }
        });
    }

    public void notInterestedStatusUpdate(Context context, PreApprove preApprove) {
        showProgressDialog.setValue(true);
        aocpvRepository.notInterestedStatus(preApprove.getCustomerID()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                showProgressDialog.setValue(false);
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
                showProgressDialog.setValue(false);
                Toast.makeText(context, R.string.something_is_wrong, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
