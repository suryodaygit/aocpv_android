package com.suryodaybank.jyotiassisted.repositories;

import com.google.gson.JsonObject;
import com.suryodaybank.jyotiassisted.models.CustomerSaveData;
import com.suryodaybank.jyotiassisted.models.DataModel;
import com.suryodaybank.jyotiassisted.models.CustomerDetailsRequest;
import com.suryodaybank.jyotiassisted.models.MfiData;
import com.suryodaybank.jyotiassisted.models.PreApprove;
import com.suryodaybank.jyotiassisted.models.Response;
import com.suryodaybank.jyotiassisted.models.SaveExpenseRequest;
import com.suryodaybank.jyotiassisted.models.SaveIncomeRequest;
import com.suryodaybank.jyotiassisted.models.UtilityDataRequest;
import com.suryodaybank.jyotiassisted.models.ValidationRequestModel;
import com.suryodaybank.jyotiassisted.models.ValidationResponse;
import com.suryodaybank.jyotiassisted.services.AocpvService;
import com.suryodaybank.jyotiassisted.utils.PreApproveStatus;

import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class AocpvRepository {

    private final AocpvService aocpvService;

    @Inject
    public AocpvRepository(AocpvService aocpvService) {
        this.aocpvService = aocpvService;
    }

    public Call<List<PreApprove>> getPreApproveList(String status) {
        DataModel<JsonObject> dataModel = new DataModel<>();
        JsonObject body = new JsonObject();
        body.addProperty("BranchID", "10011");
        body.addProperty("Status", status);
        dataModel.setData(body);
        return aocpvService.getPreApprovalList(dataModel);
    }

    public Call<ResponseBody> notInterestedStatus(long customerId) {
        DataModel<JsonObject> dataModel = new DataModel<>();
        JsonObject body = new JsonObject();
        body.addProperty("BranchID", "10011");
        body.addProperty("Status", PreApproveStatus.NOT_INTERESTED.status);
        body.addProperty("customerID", "19845948959"); //TODO: Need to pass customerId here
        dataModel.setData(body);
        return aocpvService.updateStatus(dataModel);
    }

    public Call<Response> getUserDetail(DataModel<CustomerDetailsRequest> customerDetailsRequestDataModel) {
        return aocpvService.getCustomerDetails(customerDetailsRequestDataModel);
    }

    public Call<ResponseBody> saveUserDetail(CustomerSaveData customerSaveDataDataModel) {
        DataModel<CustomerSaveData> body = new DataModel<>();
        body.setData(customerSaveDataDataModel);
        return aocpvService.saveCustomerDetails(body);
    }

    public Call<ResponseBody> saveIncomeDetails(SaveIncomeRequest incomeRequest) {
        DataModel<SaveIncomeRequest> body = new DataModel<>();
        body.setData(incomeRequest);
        return aocpvService.saveIncomeDetails(body);
    }

    public Call<ResponseBody> saveExpenseDetails(SaveExpenseRequest expenseRequest) {
        DataModel<SaveExpenseRequest> body = new DataModel<>();
        body.setData(expenseRequest);
        return aocpvService.saveExpenseDetails(body);
    }

    public Call<ResponseBody> saveUtilityDetails(UtilityDataRequest utilityDataRequest) {
        DataModel<UtilityDataRequest> body = new DataModel<>();
        body.setData(utilityDataRequest);
        return aocpvService.saveUtilityDetails(body);
    }

    public Call<ResponseBody> mfiClassification(MfiData mfiData){
        DataModel<MfiData> mfiDataDataModel = new DataModel<MfiData>();
        mfiDataDataModel.setData(mfiData);
    return aocpvService.getMFISuccess(mfiDataDataModel);
    }

    public  Call<ValidationResponse> validationData(ValidationRequestModel validationRequestModel){
        DataModel<ValidationRequestModel> dataModel = new DataModel<>();
        dataModel.setData(validationRequestModel);
        return aocpvService.getValidationData(dataModel);
    }
}