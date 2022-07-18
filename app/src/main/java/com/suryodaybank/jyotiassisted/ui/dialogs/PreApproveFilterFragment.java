package com.suryodaybank.jyotiassisted.ui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.suryodaybank.jyotiassisted.databinding.FragmentPreApproveFilterBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PreApproveFilterFragment extends BottomSheetDialogFragment {

    private FragmentPreApproveFilterBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPreApproveFilterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        binding.btnCancel.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(binding.getRoot());
            navController.navigateUp();
        });
    }
}