package com.suryodaybank.jyotiassisted.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.suryodaybank.jyotiassisted.models.RequestData;
import com.suryodaybank.jyotiassisted.models.VersionData;
import com.suryodaybank.jyotiassisted.repositories.VersionRepository;
import com.suryodaybank.jyotiassisted.services.RetroServiceInterface;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {


    RetroServiceInterface retroServiceInterface;

    public MutableLiveData<String> livedata = new MutableLiveData();

    @Inject
    public LoginViewModel(RetroServiceInterface retroServiceInterface) {
        this.retroServiceInterface = retroServiceInterface;

    }

    public MutableLiveData getLivedata() {
        return livedata;
    }

    public void makeVersionCall() {
        RequestData requestData = new RequestData();
        VersionData versionData = new VersionData();
        versionData.setVersion("1.0.0");
        versionData.setOs("Android");
        requestData.setData(versionData);
        VersionRepository versionRepository = new VersionRepository(retroServiceInterface);
        versionRepository.makeAPICall(requestData);


    }
}
