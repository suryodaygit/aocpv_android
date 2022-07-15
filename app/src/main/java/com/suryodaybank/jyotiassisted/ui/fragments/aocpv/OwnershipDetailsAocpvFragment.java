package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.suryodaybank.jyotiassisted.databinding.FragmentOwnershipDetailsAocpvBinding;

import java.util.ArrayList;
import java.util.List;

public class OwnershipDetailsAocpvFragment extends Fragment {

    private FragmentOwnershipDetailsAocpvBinding binding;

    public OwnershipDetailsAocpvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOwnershipDetailsAocpvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        List<String> ownership = new ArrayList<>();
        ownership.add("Own");
        ownership.add("Rented");
        ownership.add("Ancestral");
        ArrayAdapter<String> ownershipAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ownership);
        ownershipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ownershipSpinner.setAdapter(ownershipAdapter);

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
        ArrayAdapter<String> relationShipAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, relationShip);
        relationShipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ownerSpinner.setAdapter(relationShipAdapter);

        List<String> utilityBill = new ArrayList<>();
        utilityBill.add(0, "Please select");
        utilityBill.add("ELECTRICTY BILL");
        utilityBill.add("GAS BILL");
        utilityBill.add("WATER BILL");
        utilityBill.add("MUNICIPAL TAX BILL");
        ArrayAdapter<String> utilityAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, utilityBill);
        utilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.utilitySpinner.setAdapter(utilityAdapter);

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