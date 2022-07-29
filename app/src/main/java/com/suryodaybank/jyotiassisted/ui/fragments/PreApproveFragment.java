package com.suryodaybank.jyotiassisted.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.suryodaybank.jyotiassisted.databinding.FragmentPreApproveBinding;
import com.suryodaybank.jyotiassisted.models.PreApprove;
import com.suryodaybank.jyotiassisted.ui.adapter.PreApproveAdapter;
import com.suryodaybank.jyotiassisted.utils.ProgressDialog;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PreApproveFragment extends Fragment {

    private FragmentPreApproveBinding binding;

    private PreApproveAdapter preApproveAdapter = new PreApproveAdapter();
    private AocpvViewModel aocpvViewModel;
    private ProgressDialog mProgressDialog;

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
        aocpvViewModel = new ViewModelProvider(this).get(AocpvViewModel.class);
        mProgressDialog = new ProgressDialog(getActivity());
        setupView();
        setupObserver();
        aocpvViewModel.getPreApproveList();
    }

    private void setupView() {
        preApproveAdapter.setOnClickListener(new PreApproveAdapter.OnClickListener() {
            @Override
            public void onProceed() {
                NavController navController = Navigation.findNavController(binding.getRoot());
                navController.navigate(PreApproveFragmentDirections.actionPreApproveFragmentToAocpvFragment());
            }

            @Override
            public void onNotInterested(PreApprove preApprove) {
                aocpvViewModel.notInterestedStatusUpdate(getActivity(), preApprove);
            }

            @Override
            public void onCall(long number) {
//                if (number == null) return;
                try {
                    Uri uri = Uri.parse("tel:" + number);
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Something is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.rvPreApprove.setHasFixedSize(true);
        binding.rvPreApprove.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPreApprove.setAdapter(preApproveAdapter);

        binding.ivFilter.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(binding.getRoot());
            navController.navigate(PreApproveFragmentDirections.actionPreApproveFragmentToPreApproveFilterFragment());
        });

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                aocpvViewModel.searchQueryLiveData.setValue(newText);
                return true;
            }
        });
    }

    private void setupObserver() {
        aocpvViewModel.preApprovesLivedata.observe(getViewLifecycleOwner(), preApproves -> {
            preApproveAdapter.submitList(preApproves);
            preApproveAdapter.notifyDataSetChanged();
            if (preApproves.isEmpty()) {
                binding.tvNoItemFound.setVisibility(View.VISIBLE);
            } else {
                binding.tvNoItemFound.setVisibility(View.GONE);
            }
        });
        aocpvViewModel.searchQueryLiveData.observe(getViewLifecycleOwner(), query -> {
            preApproveAdapter.submitList(aocpvViewModel.preApprovesLivedata.getValue().stream().filter(new Predicate<PreApprove>() {
                @Override
                public boolean test(PreApprove preApprove) {
                    String number = preApprove.getMobilePhone() + "";
                    return number.contains(query);
                }
            }).collect(Collectors.toList()));
        });
        aocpvViewModel.showProgressDialog.observe(getViewLifecycleOwner(), shouldShow -> {
            if (shouldShow) {
                mProgressDialog.showDialog();
            } else {
                mProgressDialog.hideDialog();
            }
        });
    }
}