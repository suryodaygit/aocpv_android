package com.suryodaybank.jyotiassisted.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.suryodaybank.jyotiassisted.databinding.FragmentPreApproveBinding;
import com.suryodaybank.jyotiassisted.ui.adapter.PreApproveAdapter;

public class PreApproveFragment extends Fragment {

    private FragmentPreApproveBinding binding;

    private PreApproveAdapter preApproveAdapter = new PreApproveAdapter();

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
        setupView();
    }

    private void setupView() {
        binding.rvPreApprove.setHasFixedSize(true);
        binding.rvPreApprove.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPreApprove.setAdapter(preApproveAdapter);
    }
}