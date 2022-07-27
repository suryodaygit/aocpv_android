package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.suryodaybank.jyotiassisted.databinding.FragmentMonthlyExpenseAocpvBinding;
import com.suryodaybank.jyotiassisted.models.SaveExpenseRequest;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MonthlyExpenseAocpvFragment extends Fragment {

    private FragmentMonthlyExpenseAocpvBinding binding;
    private AocpvViewModel aocpvViewModel;

    public MonthlyExpenseAocpvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMonthlyExpenseAocpvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aocpvViewModel = new ViewModelProvider(requireActivity()).get(AocpvViewModel.class);
        setupViews();
        setupObserver();
    }

    private void setupViews() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                long sum = 0;

                if (!binding.etFood.getText().toString().isEmpty()) {
                    sum += Long.parseLong(binding.etFood.getText().toString());
                }
                if (!binding.etRent.getText().toString().isEmpty()) {
                    sum += Long.parseLong(binding.etRent.getText().toString());
                }
                if (!binding.etMiscTransportation.getText().toString().isEmpty()) {
                    sum += Long.parseLong(binding.etMiscTransportation.getText().toString());
                }
                if (!binding.etMiscEducation.getText().toString().isEmpty()) {
                    sum += Long.parseLong(binding.etMiscEducation.getText().toString());
                }
                if (!binding.etMiscMedical.getText().toString().isEmpty()) {
                    sum += Long.parseLong(binding.etMiscMedical.getText().toString());
                }
                if (!binding.etMiscOthers.getText().toString().isEmpty()) {
                    sum += Long.parseLong(binding.etMiscOthers.getText().toString());
                }

                binding.result.setText(String.valueOf(sum));

                long monthlyBalance = aocpvViewModel.totalMonthlyIncome - (aocpvViewModel.totalMonthlyEmi + sum);
                binding.tvResultMonthlyBalance.setText(String.valueOf(monthlyBalance));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        binding.etFood.addTextChangedListener(textWatcher);
        binding.etRent.addTextChangedListener(textWatcher);
        binding.etMiscTransportation.addTextChangedListener(textWatcher);
        binding.etMiscEducation.addTextChangedListener(textWatcher);
        binding.etMiscMedical.addTextChangedListener(textWatcher);
        binding.etMiscOthers.addTextChangedListener(textWatcher);

        //For First time only
        long monthlyBalance = aocpvViewModel.totalMonthlyIncome - (aocpvViewModel.totalMonthlyEmi);
        binding.tvResultMonthlyBalance.setText(String.valueOf(monthlyBalance));
    }

    private void setupObserver() {
        aocpvViewModel.getMonthlyExpenseData.observe(getViewLifecycleOwner(), unused -> {
            prepareDataAndCallApi();
        });
    }

    private void prepareDataAndCallApi() {
        SaveExpenseRequest saveExpenseRequest = new SaveExpenseRequest();
        saveExpenseRequest.setFoodAndUtility(binding.etFood.getText().toString());
        saveExpenseRequest.setRent(binding.etRent.getText().toString());
        saveExpenseRequest.setTransportation(binding.etMiscTransportation.getText().toString());
        saveExpenseRequest.setEducation(binding.etMiscEducation.getText().toString());
        saveExpenseRequest.setMedical(binding.etMiscMedical.getText().toString());
        saveExpenseRequest.setOther(binding.etMiscOthers.getText().toString());
        saveExpenseRequest.setTotal(binding.result.getText().toString());
        saveExpenseRequest.setMonthlyBalance(binding.tvResultMonthlyBalance.getText().toString());
        saveExpenseRequest.setCustomerClassification("amber"); //TODO: May be need to calculate as per data

        aocpvViewModel.callMonthlyExpenseApi(saveExpenseRequest);
    }
}