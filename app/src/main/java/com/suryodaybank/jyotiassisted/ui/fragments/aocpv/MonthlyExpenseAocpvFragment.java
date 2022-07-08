package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.suryodaybank.jyotiassisted.R;

public class MonthlyExpenseAocpvFragment extends Fragment {

    public MonthlyExpenseAocpvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monthly_expense_aocpv, container, false);
    }
}