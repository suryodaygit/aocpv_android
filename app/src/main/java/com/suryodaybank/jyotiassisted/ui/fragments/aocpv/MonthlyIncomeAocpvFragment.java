package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

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

import com.suryodaybank.jyotiassisted.databinding.FragmentMonthlyIncomeAocpvBinding;
import com.suryodaybank.jyotiassisted.ui.adapter.MonthlyIncomeAdapter;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MonthlyIncomeAocpvFragment extends Fragment {

    private static final String TAG = "MonthlyIncomeAocpvFragm";

    private FragmentMonthlyIncomeAocpvBinding binding;
    private AocpvViewModel aocpvViewModel;
    private final MonthlyIncomeAdapter monthlyIncomeAdapter = new MonthlyIncomeAdapter();

    public MonthlyIncomeAocpvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMonthlyIncomeAocpvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aocpvViewModel = new ViewModelProvider(requireActivity()).get(AocpvViewModel.class);
        setupViews();
        setupObserver();
    }

    private void setupObserver() {
        aocpvViewModel.monthlyIncomeLivedata.observe(getViewLifecycleOwner(), monthlyIncomes -> {
            monthlyIncomeAdapter.submitList(monthlyIncomes);
            monthlyIncomeAdapter.notifyDataSetChanged();
            double sumIncome = 0, sumEmi = 0;
            if (monthlyIncomes.isEmpty()) {
                binding.tvNoItemFound.setVisibility(View.VISIBLE);
            } else {
                binding.tvNoItemFound.setVisibility(View.GONE);
                for (int i = 0; i < monthlyIncomes.size(); i++) {
                    sumIncome += monthlyIncomes.get(i).getMonthlyIncome();
                    sumEmi += monthlyIncomes.get(i).getMonthlyLoanEmi();
                }
            }
            binding.tvTotalMonthlyIncome.setText(sumIncome + "");
            binding.tvTotalMonthlyEmi.setText(sumEmi + "");
        });
    }

    private void setupViews() {
        binding.rvMonthlyIncome.setHasFixedSize(true);
        binding.rvMonthlyIncome.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvMonthlyIncome.setAdapter(monthlyIncomeAdapter);

        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(binding.getRoot());
                navController.navigate(AocpvFragmentDirections.actionAocpvFragmentToAddMonthlyHouseholdFragment());
            }
        });
    }
}