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

import com.suryodaybank.jyotiassisted.databinding.FragmentMonthlyExpenseAocpvBinding;

public class MonthlyExpenseAocpvFragment extends Fragment {

    private FragmentMonthlyExpenseAocpvBinding binding;

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
        setupViews();
    }

    private void setupViews() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int sum = 0;

                if (!binding.etAmount1.getText().toString().isEmpty()) {
                    sum += Integer.parseInt(binding.etAmount1.getText().toString());
                }
                if (!binding.etAmount2.getText().toString().isEmpty()) {
                    sum += Integer.parseInt(binding.etAmount2.getText().toString());
                }
                if (!binding.etAmount3.getText().toString().isEmpty()) {
                    sum += Integer.parseInt(binding.etAmount3.getText().toString());
                }

                binding.result.setText(String.valueOf(sum));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        binding.etAmount1.addTextChangedListener(textWatcher);
        binding.etAmount2.addTextChangedListener(textWatcher);
        binding.etAmount3.addTextChangedListener(textWatcher);
    }
}