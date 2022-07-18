package com.suryodaybank.jyotiassisted.ui.dialogs;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.suryodaybank.jyotiassisted.R;
import com.suryodaybank.jyotiassisted.databinding.FragmentPreApproveFilterBinding;
import com.suryodaybank.jyotiassisted.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PreApproveFilterFragment extends BottomSheetDialogFragment {

    private FragmentPreApproveFilterBinding binding;
    final Calendar startDateCalender = Calendar.getInstance();
    final Calendar endDateCalender = Calendar.getInstance();

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
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigateUp();
        });

        binding.tvStartDate.setOnClickListener(view -> {

            new DatePickerDialog(getContext(), (view1, year, month, day) -> {
                startDateCalender.set(Calendar.YEAR, year);
                startDateCalender.set(Calendar.MONTH, month);
                startDateCalender.set(Calendar.DAY_OF_MONTH, day);
                updateDateLabel(binding.tvStartDate, startDateCalender);
            }, startDateCalender.get(Calendar.YEAR), startDateCalender.get(Calendar.MONTH), startDateCalender.get(Calendar.DAY_OF_MONTH)).show();
        });

        binding.tvEndDate.setOnClickListener(view -> {

            new DatePickerDialog(getContext(), (view1, year, month, day) -> {
                endDateCalender.set(Calendar.YEAR, year);
                endDateCalender.set(Calendar.MONTH, month);
                endDateCalender.set(Calendar.DAY_OF_MONTH, day);
                updateDateLabel(binding.tvEndDate, endDateCalender);
            }, endDateCalender.get(Calendar.YEAR), endDateCalender.get(Calendar.MONTH), endDateCalender.get(Calendar.DAY_OF_MONTH)).show();
        });

        binding.cgByDate.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.contains(R.id.chipCustomDate)) {
                binding.tvStartDate.setVisibility(View.VISIBLE);
                binding.tvEndDate.setVisibility(View.VISIBLE);
            } else {
                binding.tvStartDate.setVisibility(View.GONE);
                binding.tvEndDate.setVisibility(View.GONE);
            }
        });
    }


    private void updateDateLabel(TextView view, Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.COMMON_DATE_FORMAT, Locale.US);
        view.setText(dateFormat.format(calendar.getTime()));
    }
}