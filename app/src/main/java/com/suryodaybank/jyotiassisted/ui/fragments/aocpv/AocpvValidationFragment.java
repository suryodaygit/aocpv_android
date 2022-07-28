package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.suryodaybank.jyotiassisted.databinding.FragmentAocpvValidationBinding;
import com.suryodaybank.jyotiassisted.models.AddressData;
import com.suryodaybank.jyotiassisted.models.OwnerAddress;
import com.suryodaybank.jyotiassisted.models.ValidationData;
import com.suryodaybank.jyotiassisted.models.ValidationRequestModel;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AocpvValidationFragment extends Fragment {

    private FragmentAocpvValidationBinding binding;
    private AocpvViewModel aocpvViewModel;
    private ValidationData validationDetailsData = new ValidationData();
    private List<AddressData> addressData = new ArrayList<>();
    private List<OwnerAddress> ownerAddressData = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAocpvValidationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aocpvViewModel = new ViewModelProvider(requireActivity()).get(AocpvViewModel.class);
        setupViews();

        ValidationRequestModel validationRequestModel = new ValidationRequestModel("123456783");
        aocpvViewModel.callValidationData(getActivity(), validationRequestModel);
        aocpvViewModel.validationDataMutableLiveData.observe(getViewLifecycleOwner(), validationData -> {
            validationDetailsData = validationData;
            addressData = validationData.getAddress();
            ownerAddressData = validationData.getOwnerAddress();
            setData();
        });
    }

    private void setupViews() {
        List<String> proofList = new ArrayList<>();
        proofList.add("Aadhaar card");
        proofList.add("Voter ID");
        proofList.add("Passport");
        proofList.add("Driving License");
        proofList.add("MGNREGA Card");
        ArrayAdapter<String> proofAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, proofList);
        proofAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.documentSpinner.setAdapter(proofAdapter);

        binding.btnSubmit.setOnClickListener(view -> {
            //TODO: Enter number dynamically
            NavHostFragment.findNavController(this)
                    .navigate(AocpvValidationFragmentDirections.actionAocpvValidationFragmentToOtpValidationAocpvFragment("919898302748"));
        });
    }

    private void setData() {
        String customerId = validationDetailsData.getCustomerId();
        binding.etCustomerId.setText(customerId);
        binding.etFirstName.setText(validationDetailsData.getName());
        binding.etDateOfBirth.setText(validationDetailsData.getDateOfBirth());
        binding.etMobileNum.setText(validationDetailsData.getMobileNo());
        binding.etTypeOfResidence.setText("");
        binding.etAddressCommunication.setText("");
        for (int i = 0; i <= addressData.size() - 1; i++) {
            binding.etAddLine1.setText(addressData.get(i).getAddress_Line1());
            binding.etAddLine2.setText(addressData.get(i).getAddress_Line2());
            binding.etAddLine3.setText(addressData.get(i).getAddress_Line3());
            binding.etCountry.setText(addressData.get(i).getCountry());
            binding.etState.setText(addressData.get(i).getState());
            binding.etDistrict.setText(addressData.get(i).getDistrict());
            binding.etCity.setText(addressData.get(i).getCity());
            binding.etPincode.setText(addressData.get(i).getPinCode());
        }

        binding.etFood.setText(validationDetailsData.getFoodAndUtility());
        binding.etRent.setText(validationDetailsData.getRent());
        binding.etMiscTransportation.setText(validationDetailsData.getTransportation());
        binding.etMiscMedical.setText(validationDetailsData.getMedical());
        binding.etMiscEducation.setText(validationDetailsData.getEducation());
        binding.etMiscOthers.setText(validationDetailsData.getOther());
        binding.result.setText("");
        binding.etMonthlyBalance.setText(validationDetailsData.getMonthlyBalance());
        binding.purposeSpinner.setText(validationDetailsData.getPurposeOfLoan());
        binding.etExistingPurpose.setText(validationDetailsData.getExistingLoanPurpose());
        binding.etUtilityBills.setText(validationDetailsData.getUtilityBill());
        binding.etRelation.setText(validationDetailsData.getRelationshipWithOwner());

        for (int i = 0; i <= ownerAddressData.size() - 1; i++) {
            binding.etUtilityAddLine1.setText(ownerAddressData.get(i).getAddress_Line1());
            binding.etUtilityAddLine2.setText(ownerAddressData.get(i).getAddress_Line2());
            binding.etUtilityAddLine3.setText(ownerAddressData.get(i).getAddress_Line3());
            binding.etUtilityCountry.setText(ownerAddressData.get(i).getCountry());
            binding.etUtilityState.setText(ownerAddressData.get(i).getState());
            binding.etUtilityDistrict.setText(ownerAddressData.get(i).getDistrict());
            binding.etUtilityPincode.setText(ownerAddressData.get(i).getPinCode());
            binding.etUtilityCity.setText(ownerAddressData.get(i).getCity());
        }

        if (validationDetailsData.getMobileLinkToAadhar().equals("Yes")) {
            binding.rbYes.setChecked(true);
            binding.etMobileNo.setVisibility(View.VISIBLE);
            binding.etMobileNo.setText(validationDetailsData.getMobile2());
        } else {
            binding.rbNo.setChecked(true);
            binding.etMobileNo.setVisibility(View.GONE);
        }

    }
}