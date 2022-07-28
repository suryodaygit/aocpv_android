package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.suryodaybank.jyotiassisted.databinding.FragmentOtpValidationAocpvBinding;
import com.suryodaybank.jyotiassisted.viewmodels.OtpAocpvViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OtpValidationAocpvFragment extends Fragment {

    private FragmentOtpValidationAocpvBinding binding;
    private OtpAocpvViewModel otpAocpvViewModel;
    private OtpValidationAocpvFragmentArgs navArgs;

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
                    otpAocpvViewModel.validateOtp(otp);
                else
                    otpAocpvViewModel.messageLiveData.setValue("Enter valid OTP");

                /*NavHostFragment.findNavController(OtpValidationAocpvFragment.this)
                        .navigate(OtpValidationAocpvFragmentDirections.actionOtpValidationAocpvFragmentToPreApproveFragment());*/
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
                NavHostFragment.findNavController(OtpValidationAocpvFragment.this)
                        .navigate(OtpValidationAocpvFragmentDirections.actionOtpValidationAocpvFragmentToPreApproveFragment());
            }
        });
    }
}