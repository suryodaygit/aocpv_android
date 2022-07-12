
package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.suryodaybank.jyotiassisted.databinding.FragmentAddressDetailsApcpvBinding;

import java.util.ArrayList;
import java.util.List;

public class AddressDetailsAocpvFragment extends Fragment {

    private FragmentAddressDetailsApcpvBinding binding;

    public AddressDetailsAocpvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddressDetailsApcpvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        List<String> addressType = new ArrayList<>();
        addressType.add("Company Provided");
        addressType.add("Living with family");
        addressType.add("Leases");
        addressType.add("Own");
        addressType.add("Rent");
        addressType.add("Unknown");
        ArrayAdapter<String> addressAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, addressType);
        addressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.addressSpinner.setAdapter(addressAdapter);

        List<String> communication = new ArrayList<>();
        communication.add("Permanent");
        communication.add("Office");
        communication.add("Residential");
        ArrayAdapter<String> communicationAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, communication);
        communicationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.communicationSpinner.setAdapter(communicationAdapter);

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

        List<String> proofList = new ArrayList<>();
        proofList.add("Aadhaar card");
        proofList.add("Voter ID");
        proofList.add("Passport");
        proofList.add("Driving License");
        proofList.add("MGNREGA Card");
        ArrayAdapter<String> proofAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, proofList);
        proofAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.proofSpinner.setAdapter(proofAdapter);
    }
}