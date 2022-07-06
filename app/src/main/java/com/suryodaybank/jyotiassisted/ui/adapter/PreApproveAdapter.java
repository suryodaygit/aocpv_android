package com.suryodaybank.jyotiassisted.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.suryodaybank.jyotiassisted.databinding.ItemPreApproveBinding;
import com.suryodaybank.jyotiassisted.models.PreApprove;

public class PreApproveAdapter extends ListAdapter<PreApprove, PreApproveAdapter.PreApproveVH> {

    public PreApproveAdapter() {
        super(new DiffUtil.ItemCallback<PreApprove>() {
            @Override
            public boolean areItemsTheSame(@NonNull PreApprove oldItem, @NonNull PreApprove newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull PreApprove oldItem, @NonNull PreApprove newItem) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @NonNull
    @Override
    public PreApproveVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PreApproveVH(ItemPreApproveBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PreApproveVH holder, int position) {
//        holder.binding
    }

    static class PreApproveVH extends RecyclerView.ViewHolder {
        public ItemPreApproveBinding binding;

        public PreApproveVH(@NonNull ItemPreApproveBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
