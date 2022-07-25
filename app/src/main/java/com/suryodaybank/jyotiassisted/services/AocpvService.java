package com.suryodaybank.jyotiassisted.services;

import com.suryodaybank.jyotiassisted.models.CRMCustDataResponseItem;
import com.suryodaybank.jyotiassisted.models.CustomerDetailsRequest;
import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.LoginResponse;
import com.suryodaybank.jyotiassisted.models.PreApprove;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AocpvService {

    @POST("preapprovallist")
    Call<List<PreApprove>> getPreApprovalList();

    @POST("customer/getData")
    Call<DataModel<CRMCustDataResponseItem>> getCustomerDetails(@Body DataModel<CustomerDetailsRequest> customerDetailsRequestDataModel);

}
