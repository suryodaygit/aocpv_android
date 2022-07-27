package com.suryodaybank.jyotiassisted.services;

import com.google.gson.JsonObject;
import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.CustomerDetailsRequest;
import com.suryodaybank.jyotiassisted.models.CustomerSaveData;
import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.MfiData;
import com.suryodaybank.jyotiassisted.models.PreApprove;
import com.suryodaybank.jyotiassisted.models.Response;
import com.suryodaybank.jyotiassisted.models.SaveExpenseRequest;
import com.suryodaybank.jyotiassisted.models.SaveIncomeRequest;
import com.suryodaybank.jyotiassisted.models.UtilityDataRequest;
import com.suryodaybank.jyotiassisted.models.ValidationRequestModel;
import com.suryodaybank.jyotiassisted.models.ValidationResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AocpvService {

    @POST("aocpv/v1/getByIdAndStatus")
    Call<List<PreApprove>> getPreApprovalList(@Body DataModel<JsonObject> body);

    @POST("aocpv/v1/updateStatus")
    Call<ResponseBody> updateStatus(@Body DataModel<JsonObject> body);

    @POST("customer/getData")
    Call<Response> getCustomerDetails(@Body DataModel<CustomerDetailsRequest> customerDetailsRequestDataModel);

    @POST("aocp/customer/saveData")
    Call<ResponseBody> saveCustomerDetails(@Body DataModel<CustomerSaveData> customerSaveDataDataModel);

    @POST("aocp/customer/saveData")
    Call<ResponseBody> saveIncomeDetails(@Body DataModel<SaveIncomeRequest> body);

    @POST("aocp/customer/saveData")
    Call<ResponseBody> saveExpenseDetails(@Body DataModel<SaveExpenseRequest> body);

    @POST("aocp/customer/saveData")
    Call<ResponseBody> saveUtilityDetails(@Body DataModel<UtilityDataRequest> body);

    @POST("aocp/customer/saveData")
    Call<ResponseBody> getMFISuccess(@Body DataModel<MfiData> mfiData);

    @POST("aocp/customer/fetchById")
    Call<ValidationResponse> getValidationData(@Body DataModel<ValidationRequestModel> body);
}