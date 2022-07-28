package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import static com.suryodaybank.jyotiassisted.utils.Constants.ELIGIBILITY;

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
import com.suryodaybank.jyotiassisted.models.SaveIncomeRequest;
import com.suryodaybank.jyotiassisted.ui.LoginActivity;
import com.suryodaybank.jyotiassisted.ui.adapter.MonthlyIncomeAdapter;
import com.suryodaybank.jyotiassisted.utils.SharedPreferenceUtils;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import java.util.concurrent.atomic.AtomicLong;

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

    @Override
    public void onResume() {
        super.onResume();
        if (aocpvViewModel.monthlyIncomeLivedata.getValue().isEmpty()) {
            binding.fabAdd.performClick();
        }
    }

    private void setupObserver() {
        aocpvViewModel.monthlyIncomeLivedata.observe(getViewLifecycleOwner(), monthlyIncomes -> {
            monthlyIncomeAdapter.submitList(monthlyIncomes);
            monthlyIncomeAdapter.notifyDataSetChanged();

            long sumIncome = 0, sumEmi = 0;
            if (monthlyIncomes.isEmpty()) {
                binding.tvNoItemFound.setVisibility(View.VISIBLE);
            } else {
                binding.tvNoItemFound.setVisibility(View.GONE);
                for (int i = 0; i < monthlyIncomes.size(); i++) {
                    sumIncome += Long.parseLong(monthlyIncomes.get(i).getMonthlyIncome());
                    sumEmi += Long.parseLong(monthlyIncomes.get(i).getMonthlyLoanEmi());
                }
            }

            aocpvViewModel.totalMonthlyIncome = sumIncome;
            aocpvViewModel.totalMonthlyEmi = sumEmi;

            binding.tvTotalMonthlyIncome.setText(sumIncome + "");
            binding.tvTotalMonthlyEmi.setText(sumEmi + "");

            int amount = (int) ((sumIncome * 0.5) -sumEmi);
            String eligibility = String.valueOf(amount);
            SharedPreferenceUtils.getInstance(getActivity()).putString(ELIGIBILITY,eligibility);
        });

        aocpvViewModel.getMonthlyIncomeData.observe(getViewLifecycleOwner(), unused -> {
            prepareDataAndCallApi();
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

    private void prepareDataAndCallApi() {
        SaveIncomeRequest saveIncomeRequest = new SaveIncomeRequest();
        saveIncomeRequest.setTotalMonthlyIncome(binding.tvTotalMonthlyIncome.getText().toString());
        saveIncomeRequest.setTotalMonthlyEmi(binding.tvTotalMonthlyEmi.getText().toString());
        saveIncomeRequest.setIncomeDetails(monthlyIncomeAdapter.getCurrentList());
        aocpvViewModel.callMonthlyIncomeApi(saveIncomeRequest);
    }
}