package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.suryodaybank.jyotiassisted.databinding.FragmentAocpvValidationBinding;

import java.util.ArrayList;
import java.util.List;

public class AocpvValidationFragment extends Fragment {

    private FragmentAocpvValidationBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAocpvValidationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        List<String> proofList = new ArrayList<>();
        proofList.add("Aadhaar card");
        proofList.add("Voter ID");
        proofList.add("Passport");
        proofList.add("Driving License");
        proofList.add("MGNREGA Card");
        ArrayAdapter<String> proofAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, proofList);
        proofAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.documentSpinner.setAdapter(proofAdapter);
    }
}