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

    private OnClickListener onClickListener;

    public PreApproveAdapter() {
        super(new DiffUtil.ItemCallback<PreApprove>() {
            @Override
            public boolean areItemsTheSame(@NonNull PreApprove oldItem, @NonNull PreApprove newItem) {
                return oldItem.getCustomerID() == newItem.getCustomerID();
            }

            @Override
            public boolean areContentsTheSame(@NonNull PreApprove oldItem, @NonNull PreApprove newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public PreApproveVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PreApproveVH(ItemPreApproveBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PreApproveVH holder, int position) {
        PreApprove preApprove = getItem(position);
        holder.bind(preApprove);
        holder.binding.btnProceed.setOnClickListener(view -> onClickListener.onProceed());
        holder.binding.btnNotInterested.setOnClickListener(view -> onClickListener.onNotInterested());
        holder.binding.btnCall.setOnClickListener(view -> onClickListener.onCall(preApprove.getLandphoneNUMBER()));
    }

    static class PreApproveVH extends RecyclerView.ViewHolder {
        public ItemPreApproveBinding binding;

        public PreApproveVH(@NonNull ItemPreApproveBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PreApprove preApprove) {
            binding.tvName.setText(preApprove.getMemberNAME());
            binding.tvMobileNumber.setText(preApprove.getLandphoneNUMBER());
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onProceed();

        void onNotInterested();

        void onCall(String number);
    }
}
