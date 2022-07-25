package com.suryodaybank.jyotiassisted.repositories;

import com.suryodaybank.jyotiassisted.models.CRMCustDataResponseItem;
import com.suryodaybank.jyotiassisted.models.CustomerDetailsRequest;
import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.LoginRequest;
import com.suryodaybank.jyotiassisted.models.LoginResponse;
import com.suryodaybank.jyotiassisted.models.PreApprove;
import com.suryodaybank.jyotiassisted.services.AocpvService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class AocpvRepository {

    private final AocpvService aocpvService;

    @Inject
    public AocpvRepository(AocpvService aocpvService) {
        this.aocpvService = aocpvService;
    }

    public Call<List<PreApprove>> getPreApproveList() {
        return aocpvService.getPreApprovalList();
    }


    public Call<DataModel<CRMCustDataResponseItem>> getUserDetail(DataModel<CustomerDetailsRequest> customerDetailsRequestDataModel) {
        return aocpvService.getCustomerDetails(customerDetailsRequestDataModel);
    }
}
