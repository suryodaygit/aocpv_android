package com.suryodaybank.jyotiassisted.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.suryodaybank.jyotiassisted.databinding.ItemMonthlyIncomeBinding;
import com.suryodaybank.jyotiassisted.models.MonthlyIncome;

public class MonthlyIncomeAdapter extends ListAdapter<MonthlyIncome, MonthlyIncomeAdapter.MonthlyIncomeVH> {

    public MonthlyIncomeAdapter() {
        super(new DiffUtil.ItemCallback<MonthlyIncome>() {
            @Override
            public boolean areItemsTheSame(@NonNull MonthlyIncome oldItem, @NonNull MonthlyIncome newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull MonthlyIncome oldItem, @NonNull MonthlyIncome newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public MonthlyIncomeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MonthlyIncomeVH(ItemMonthlyIncomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyIncomeVH holder, int position) {

    }

    static class MonthlyIncomeVH extends RecyclerView.ViewHolder {

        private final ItemMonthlyIncomeBinding binding;

        public MonthlyIncomeVH(@NonNull ItemMonthlyIncomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
