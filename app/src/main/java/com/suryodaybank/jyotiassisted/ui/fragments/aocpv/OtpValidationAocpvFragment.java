package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.suryodaybank.jyotiassisted.R;
import com.suryodaybank.jyotiassisted.databinding.FragmentOtpValidationAocpvBinding;
import com.suryodaybank.jyotiassisted.utils.ProgressDialog;
import com.suryodaybank.jyotiassisted.viewmodels.OtpAocpvViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OtpValidationAocpvFragment extends Fragment {

    private FragmentOtpValidationAocpvBinding binding;
    private OtpAocpvViewModel otpAocpvViewModel;
    private OtpValidationAocpvFragmentArgs navArgs;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navArgs = OtpValidationAocpvFragmentArgs.fromBundle(getArguments());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOtpValidationAocpvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        otpAocpvViewModel = new ViewModelProvider(requireActivity()).get(OtpAocpvViewModel.class);
        mProgressDialog = new ProgressDialog(getActivity());
        setupView();
        setupObserver();
        otpAocpvViewModel.sendOtp(navArgs.getMobile());
    }

    private void setupView() {
        binding.btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = binding.pinView.getText().toString();
                if (otp.length() == 6)
                    otpAocpvViewModel.validateOtp(otp, navArgs.getApplicationNo());
                else
                    otpAocpvViewModel.messageLiveData.setValue("Enter valid OTP");
            }
        });
    }

    private void setupObserver() {
        otpAocpvViewModel.messageLiveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
            }
        });
        otpAocpvViewModel.finalSuccessCall.observe(getViewLifecycleOwner(), new Observer<Void>() {
            @Override
            public void onChanged(Void unused) {
                showFinalSuccessMessage();
            }
        });
        otpAocpvViewModel.showProgressDialog.observe(getViewLifecycleOwner(), shouldShow -> {
            if (shouldShow) {
                mProgressDialog.showDialog();
            } else {
                mProgressDialog.hideDialog();
            }
        });
    }

    private void showFinalSuccessMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Success")
                .setMessage("Application id 12345681 generated successfully.")
                .setCancelable(false)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    NavHostFragment.findNavController(OtpValidationAocpvFragment.this)
                            .navigate(OtpValidationAocpvFragmentDirections.actionOtpValidationAocpvFragmentToHomeFragment());
                });
        builder.show();
    }
}