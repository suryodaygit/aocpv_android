package com.suryodaybank.jyotiassisted.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.suryodaybank.jyotiassisted.databinding.FragmentPreApproveBinding;
import com.suryodaybank.jyotiassisted.ui.adapter.PreApproveAdapter;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PreApproveFragment extends Fragment {

    private FragmentPreApproveBinding binding;

    private PreApproveAdapter preApproveAdapter = new PreApproveAdapter();
    private AocpvViewModel aocpvViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPreApproveBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aocpvViewModel = new ViewModelProvider(this).get(AocpvViewModel.class);
        setupView();
        setupObserver();
    }

    private void setupView() {
        preApproveAdapter.setOnClickListener(new PreApproveAdapter.OnClickListener() {
            @Override
            public void onProceed() {
                NavController navController = Navigation.findNavController(binding.getRoot());
                navController.navigate(PreApproveFragmentDirections.actionPreApproveFragmentToAocpvFragment());
            }

            @Override
            public void onNotInterested() {

            }
        });
        binding.rvPreApprove.setHasFixedSize(true);
        binding.rvPreApprove.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPreApprove.setAdapter(preApproveAdapter);

        binding.ivFilter.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(binding.getRoot());
            navController.navigate(PreApproveFragmentDirections.actionPreApproveFragmentToPreApproveFilterFragment());
        });
    }

    private void setupObserver() {
        aocpvViewModel.preApprovesLivedata.observe(getViewLifecycleOwner(), preApproves -> {
            preApproveAdapter.submitList(preApproves);
            if (preApproves.isEmpty()) {
                binding.tvNoItemFound.setVisibility(View.VISIBLE);
            } else {
                binding.tvNoItemFound.setVisibility(View.GONE);
            }
        });
    }
}