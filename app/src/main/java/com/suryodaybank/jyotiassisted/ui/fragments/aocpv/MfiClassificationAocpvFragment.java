package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.suryodaybank.jyotiassisted.databinding.FragmentMfiClassificationAocpvBinding;
import com.suryodaybank.jyotiassisted.models.MfiData;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MfiClassificationAocpvFragment extends Fragment {

    private AocpvViewModel aocpvViewModel;
    private FragmentMfiClassificationAocpvBinding binding;
    private RadioButton selectedRadioButton;
    private String selectedRadioButtonText ="Yes";
    private String extingPurpose="";
    public MfiClassificationAocpvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMfiClassificationAocpvBinding.inflate(inflater,container,false);
        return binding.getRoot();

  }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aocpvViewModel = new ViewModelProvider(requireActivity()).get(AocpvViewModel.class);
        init();

        aocpvViewModel.getMfiClassificationData.observe(getViewLifecycleOwner(), unused -> {
            extingPurpose = binding.etExistingPurpose.getText().toString();
            if (extingPurpose.equals("")){
             // Toast.makeText(getActivity(),"Please enter purpose",Toast.LENGTH_SHORT).show();
            }else {
                extingPurpose ="hskhdkh";
                callMfiAPI();
            }
        });
    }

    private void init() {
        binding.rgLinkMobile.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedButtonId != -1) {
                    selectedRadioButton = radioGroup.findViewById(selectedButtonId);
                    selectedRadioButtonText = selectedRadioButton.getText().toString();
                    if (selectedRadioButtonText.equals("Yes")) {
                        binding.tvMobileNo.setVisibility(View.VISIBLE);
                        binding.etMobileNo.setVisibility(View.VISIBLE);
                    } else {
                        binding.tvMobileNo.setVisibility(View.GONE);
                        binding.etMobileNo.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

        private void callMfiAPI(){
            if (selectedRadioButtonText.equals("Yes")) {
                MfiData mfiData = new MfiData("MFI", "12345681", "for renovaton of house",
                        extingPurpose, selectedRadioButtonText, "9887970808", binding.tvMaxEligibilityAmount.toString());
                aocpvViewModel.callMFIClassification(getActivity(), mfiData);
            } else {
                // just send blank mobile no
                MfiData mfiData = new MfiData("MFI", "12345681", "for renovation of house",
                        extingPurpose, selectedRadioButtonText, "9808989898", binding.tvMaxEligibilityAmount.toString());
                aocpvViewModel.callMFIClassification(getActivity(), mfiData);
            }

  }
}