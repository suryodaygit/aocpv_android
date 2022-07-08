package com.suryodaybank.jyotiassisted.ui.fragments;

import static com.suryodaybank.jyotiassisted.utils.Constants.BRANCH_CODE;
import static com.suryodaybank.jyotiassisted.utils.Constants.UID1;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.suryodaybank.jyotiassisted.databinding.FragmentHomeBinding;
import com.suryodaybank.jyotiassisted.utils.Constants;
import com.suryodaybank.jyotiassisted.utils.SharedPreferenceUtils;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    String userId, branchName;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String userId = SharedPreferenceUtils.getInstance(getContext()).getString(UID1);
        String branch_code = SharedPreferenceUtils.getInstance(getContext()).getString(BRANCH_CODE);
        Log.d("branch Code", branch_code);
      //  binding.branchCode.setText(branch_code);

    }
}