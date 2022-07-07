package com.suryodaybank.jyotiassisted.services;

import com.suryodaybank.jyotiassisted.models.PreApprove;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface AocpvService {

    @POST("preapprovallist")
    Call<List<PreApprove>> getPreApprovalList();
}
