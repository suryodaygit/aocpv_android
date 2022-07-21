package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.suryodaybank.jyotiassisted.databinding.FragmentPersonalDetailAocpvBinding;
import com.suryodaybank.jyotiassisted.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PersonalDetailAocpvFragment extends Fragment {

    private FragmentPersonalDetailAocpvBinding binding;
    final Calendar myCalendar = Calendar.getInstance();

    public PersonalDetailAocpvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPersonalDetailAocpvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
    }

    private void setupViews() {

//        String[] titles = new String[]{"Mr.", "Mrs.", "Ms."};
//        ArrayAdapter<String> titleAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, titles);
//        binding.titleSpinner.setAdapter(titleAdapter);

//        String[] items = new String[]{"Male", "Female"};
//        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
//        binding.spinnerGender.setAdapter(genderAdapter);

        binding.etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), (view1, year, month, day) -> {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, day);
                    updateDateLabel();
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateDateLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.COMMON_DATE_FORMAT, Locale.US);
        binding.etDOB.setText(dateFormat.format(myCalendar.getTime()));
    }


}