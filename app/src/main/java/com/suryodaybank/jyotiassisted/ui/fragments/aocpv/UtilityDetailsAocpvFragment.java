package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.suryodaybank.jyotiassisted.databinding.FragmentUtilityDetailsAocpvBinding;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import java.util.ArrayList;
import java.util.List;

public class UtilityDetailsAocpvFragment extends Fragment {

    private FragmentUtilityDetailsAocpvBinding binding;
    private AocpvViewModel aocpvViewModel;

    public UtilityDetailsAocpvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUtilityDetailsAocpvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aocpvViewModel = new ViewModelProvider(requireActivity()).get(AocpvViewModel.class);
        setupViews();
        setUpObserver();
    }

    private void setUpObserver() {


    }

    private void setupViews() {
        List<String> utilityBill = new ArrayList<>();
        utilityBill.add(0, "Please select");
        utilityBill.add("Phone Number");
        utilityBill.add("Email");
        utilityBill.add("Landline");
        ArrayAdapter<String> utilityAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, utilityBill);
        utilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.utilitySpinner.setAdapter(utilityAdapter);

        List<String> relationShip = new ArrayList<>();
        relationShip.add("Self");
        relationShip.add("Father");
        relationShip.add("Mother");
        relationShip.add("Husband");
        relationShip.add("Son");
        relationShip.add("Daughter");
        relationShip.add("Brother");
        relationShip.add("Sister");
        ArrayAdapter<String> ownerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, relationShip);
        ownerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ownerSpinner.setAdapter(ownerAdapter);

        List<String> country = new ArrayList<>();
        country.add("India");
        country.add("US");
        country.add("Europe");
        country.add("Australia");
        country.add("Germany");
        country.add("China");
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, country);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.countrySpinner.setAdapter(countryAdapter);
    }
}