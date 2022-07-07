package com.suryodaybank.jyotiassisted.repositories;

import com.suryodaybank.jyotiassisted.models.PreApprove;
import com.suryodaybank.jyotiassisted.services.AocpvService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class AocpvRepository {

    private AocpvService aocpvService;

    @Inject
    public AocpvRepository(AocpvService aocpvService) {
        this.aocpvService = aocpvService;
    }

    public Call<List<PreApprove>> getPreApproveList() {
        return aocpvService.getPreApprovalList();
    }


}
