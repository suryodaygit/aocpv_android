package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.suryodaybank.jyotiassisted.databinding.FragmentAddMonthlyHouseholdBinding;
import com.suryodaybank.jyotiassisted.models.MonthlyIncome;

import java.util.ArrayList;
import java.util.List;

public class AddMonthlyHouseholdFragment extends Fragment {

    private FragmentAddMonthlyHouseholdBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddMonthlyHouseholdBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        List<String> relationShip = new ArrayList<>();
        relationShip.add("Self");
        relationShip.add("Father");
        relationShip.add("Mother");
        relationShip.add("Husband");
        relationShip.add("Son");
        relationShip.add("Daughter");
        relationShip.add("Brother");
        relationShip.add("Sister");
        relationShip.add("House Owner");
        ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, relationShip);
        memberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerMember.setAdapter(memberAdapter);

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MonthlyIncome monthlyIncome = new MonthlyIncome();
                monthlyIncome.setFamilyMember("");
                if (binding.rbYes.isChecked())
                    monthlyIncome.setEarningMember("Yes");
                else
                    monthlyIncome.setEarningMember("No");
                monthlyIncome.setOccupation(binding.etOccupation.getText().toString());
                monthlyIncome.setSourceOfIncome(binding.etSourceOfIncome.getText().toString());
                monthlyIncome.setSecuredLoan(binding.etSecuredLoan.getText().toString());
                monthlyIncome.setUnsecuredLoan(binding.etUnsecuredLoan.getText().toString());
                monthlyIncome.setMonthlyIncome(binding.etMonthlyIncome.getText().toString());
                monthlyIncome.setMonthlyIncome(binding.etMonthlyLoanEMI.getText().toString());

                NavController navController = Navigation.findNavController(binding.getRoot());
                navController.navigateUp();
            }
        });
    }
}