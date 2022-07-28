package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.suryodaybank.jyotiassisted.R;
import com.suryodaybank.jyotiassisted.databinding.FragmentMfiClassificationAocpvBinding;
import com.suryodaybank.jyotiassisted.models.MfiData;
import com.suryodaybank.jyotiassisted.models.PurposeOfLoan;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MfiClassificationAocpvFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private AocpvViewModel aocpvViewModel;
    private FragmentMfiClassificationAocpvBinding binding;
    private RadioButton selectedRadioButton;
    private String selectedRadioButtonText = "Yes";
    private String extingPurpose = "jhdjhsjdhsj";
    private List<PurposeOfLoan> purposeOfLoans = new ArrayList<PurposeOfLoan>();
    private String selectedPurposeLoan = "";
    private String loanCode = "";


    public MfiClassificationAocpvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMfiClassificationAocpvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aocpvViewModel = new ViewModelProvider(requireActivity()).get(AocpvViewModel.class);
        setSpinnerList();
        setSpinerAdapter();
        init();

        aocpvViewModel.getMfiClassificationData.observe(getViewLifecycleOwner(), unused -> {
            // extingPurpose = binding.etExistingPurpose.getText().toString();
            if (extingPurpose.equals("")) {
                // Toast.makeText(getActivity(),"Please enter purpose",Toast.LENGTH_SHORT).show();
            } else {
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

    private void callMfiAPI() {
        if (selectedRadioButtonText.equals("Yes")) {
            MfiData mfiData = new MfiData("MFI", "12345681", selectedPurposeLoan, loanCode,
                    extingPurpose, selectedRadioButtonText, "9887970808", binding.tvMaxEligibilityAmount.getText().toString());
            aocpvViewModel.callMFIClassification(getActivity(), mfiData);
        } else {
            // just send blank mobile no
            MfiData mfiData = new MfiData("MFI", "12345681", selectedPurposeLoan, loanCode,
                    extingPurpose, selectedRadioButtonText, "9808989898", binding.tvMaxEligibilityAmount.getText().toString());
            aocpvViewModel.callMFIClassification(getActivity(), mfiData);
        }
    }

    private void setSpinnerList() {
        purposeOfLoans.add(new PurposeOfLoan("Purpos code", "Desc Of The Purpose"));
        purposeOfLoans.add(new PurposeOfLoan("2", "Boat"));
        purposeOfLoans.add(new PurposeOfLoan("9", "CV-Chassis"));
        purposeOfLoans.add(new PurposeOfLoan("10", "Working Capital"));
        purposeOfLoans.add(new PurposeOfLoan("20", "Acquisition of Fixed Assets"));
        purposeOfLoans.add(new PurposeOfLoan("24", "Takeover of loan Seller"));
        purposeOfLoans.add(new PurposeOfLoan("25", "Takeover of loan Customer"));
        purposeOfLoans.add(new PurposeOfLoan("30", "Investment"));
        purposeOfLoans.add(new PurposeOfLoan("40", "Personal"));
        purposeOfLoans.add(new PurposeOfLoan("50", "Consumer Durables"));
        purposeOfLoans.add(new PurposeOfLoan("60", "CV-Refinance"));
        purposeOfLoans.add(new PurposeOfLoan("70", "Others"));
        purposeOfLoans.add(new PurposeOfLoan("102", "Term Loan"));
        purposeOfLoans.add(new PurposeOfLoan("103", "Agricultural Term Ln"));
        purposeOfLoans.add(new PurposeOfLoan("24", "Takeover of loan Seller"));
        purposeOfLoans.add(new PurposeOfLoan("25", "Takeover of loan Customer"));
        purposeOfLoans.add(new PurposeOfLoan("2", "Boat"));
        purposeOfLoans.add(new PurposeOfLoan("9", "CV-Chassis"));
        purposeOfLoans.add(new PurposeOfLoan("10", "Working Capital"));
        purposeOfLoans.add(new PurposeOfLoan("11", "Purchasing of Plant and Machin"));
        purposeOfLoans.add(new PurposeOfLoan("12", "Purchasing of Equipments"));
        purposeOfLoans.add(new PurposeOfLoan("13", "Construction of Factory shed"));
        purposeOfLoans.add(new PurposeOfLoan("14", "Export credit"));
        purposeOfLoans.add(new PurposeOfLoan("15", "CV-Body"));
        purposeOfLoans.add(new PurposeOfLoan("16", "General business purpose"));
        purposeOfLoans.add(new PurposeOfLoan("20", "Acquisition of Fixed Assets"));
        purposeOfLoans.add(new PurposeOfLoan("22", "Takeover of loan"));
        purposeOfLoans.add(new PurposeOfLoan("24", "Takeover of loan Seller"));
        purposeOfLoans.add(new PurposeOfLoan("25", "Takeover of loan Customer"));
        purposeOfLoans.add(new PurposeOfLoan("30", "Investment"));
        purposeOfLoans.add(new PurposeOfLoan("40", "Personal"));
        purposeOfLoans.add(new PurposeOfLoan("50", "Consumer Durables"));
        purposeOfLoans.add(new PurposeOfLoan("55", "Domestic Use"));
        purposeOfLoans.add(new PurposeOfLoan("56", "Personal Use"));
        purposeOfLoans.add(new PurposeOfLoan("60", "CV-Refinance"));
        purposeOfLoans.add(new PurposeOfLoan("70", "Others"));
        purposeOfLoans.add(new PurposeOfLoan("102", "Term Loan"));
        purposeOfLoans.add(new PurposeOfLoan("103", "Agricultural Term Ln"));
        purposeOfLoans.add(new PurposeOfLoan("200", "Agriculture - Dairy"));
        purposeOfLoans.add(new PurposeOfLoan("201", "Agriculture - Goatery"));
        purposeOfLoans.add(new PurposeOfLoan("202", "Agriculture - Piggery"));
        purposeOfLoans.add(new PurposeOfLoan("203", "Agriculture - Poultry"));
        purposeOfLoans.add(new PurposeOfLoan("204", "Agriculture - Fishery"));
        purposeOfLoans.add(new PurposeOfLoan("205", "Agriculture - PISCICULTURE"));
        purposeOfLoans.add(new PurposeOfLoan("206", "NA"));
        purposeOfLoans.add(new PurposeOfLoan("1", "PURCHASE"));
        purposeOfLoans.add(new PurposeOfLoan("10", "Working Capital"));
        purposeOfLoans.add(new PurposeOfLoan("20", "Acquisition of Fixed Assets"));
        purposeOfLoans.add(new PurposeOfLoan("24", "Takeover of loan Seller"));
        purposeOfLoans.add(new PurposeOfLoan("25", "Takeover of loan Customer"));
        purposeOfLoans.add(new PurposeOfLoan("30", "Investment"));
        purposeOfLoans.add(new PurposeOfLoan("40", "Personal"));
        purposeOfLoans.add(new PurposeOfLoan("50", "Consumer Durables"));
        purposeOfLoans.add(new PurposeOfLoan("60", "CV-Refinance"));
        purposeOfLoans.add(new PurposeOfLoan("70", "Others"));
        purposeOfLoans.add(new PurposeOfLoan("102", "Term Loan"));
        purposeOfLoans.add(new PurposeOfLoan("103", "Agricultural Term Ln"));
        purposeOfLoans.add(new PurposeOfLoan("1", "Purchase of Two Wheeler"));
        purposeOfLoans.add(new PurposeOfLoan("2", "Purchase of Four Wheeler"));
        purposeOfLoans.add(new PurposeOfLoan("3", "Studies Domestic"));
        purposeOfLoans.add(new PurposeOfLoan("4", "Studies Abroad"));
        purposeOfLoans.add(new PurposeOfLoan("5", "Income Generation"));
        purposeOfLoans.add(new PurposeOfLoan("6", "Consumption Needs DebtSwapping"));
        purposeOfLoans.add(new PurposeOfLoan("7", "Housing"));
        purposeOfLoans.add(new PurposeOfLoan("8", "Others"));
        purposeOfLoans.add(new PurposeOfLoan("9", "CV-Chassis"));
        purposeOfLoans.add(new PurposeOfLoan("10", "Handloom Weavers"));
        purposeOfLoans.add(new PurposeOfLoan("12", "Service Sector"));
        purposeOfLoans.add(new PurposeOfLoan("13", "Fisherman"));
        purposeOfLoans.add(new PurposeOfLoan("14", "Self Employed Person"));
        purposeOfLoans.add(new PurposeOfLoan("15", "CV-Body"));
        purposeOfLoans.add(new PurposeOfLoan("16", "Other Micro Enterprises"));
        purposeOfLoans.add(new PurposeOfLoan("17", "Working Capital"));
        purposeOfLoans.add(new PurposeOfLoan("18", "Loan against Rent (non-owner)"));
        purposeOfLoans.add(new PurposeOfLoan("19", "Loan against term deposit"));
        purposeOfLoans.add(new PurposeOfLoan("20", "Two wheeler loan"));
        purposeOfLoans.add(new PurposeOfLoan("21", "Four wheeler loan"));
        purposeOfLoans.add(new PurposeOfLoan("22", "Takeover of loan"));
        purposeOfLoans.add(new PurposeOfLoan("23", "General business purpose"));
        purposeOfLoans.add(new PurposeOfLoan("24", "Takeover of loan Seller"));
        purposeOfLoans.add(new PurposeOfLoan("25", "Takeover of loan Customer"));
        purposeOfLoans.add(new PurposeOfLoan("40", "Personal"));
        purposeOfLoans.add(new PurposeOfLoan("1", "For Purchase of House/Flat"));
        purposeOfLoans.add(new PurposeOfLoan("2", "For Construction of House"));
        purposeOfLoans.add(new PurposeOfLoan("3", "For Renovation of House/Flat"));
        purposeOfLoans.add(new PurposeOfLoan("4", "Purchase of Plot & Construction"));
        purposeOfLoans.add(new PurposeOfLoan("5", "Loan against property"));
        purposeOfLoans.add(new PurposeOfLoan("6", "Loan against term deposit"));
        purposeOfLoans.add(new PurposeOfLoan("7", "Loan against Gold Ornaments"));
        purposeOfLoans.add(new PurposeOfLoan("8", "Overdraft agaisnt Term Deposit"));
        purposeOfLoans.add(new PurposeOfLoan("15", "Personal loan to HL customer"));
        purposeOfLoans.add(new PurposeOfLoan("16", "General business purpose"));
        purposeOfLoans.add(new PurposeOfLoan("22", "Takeover of loan"));
        purposeOfLoans.add(new PurposeOfLoan("24", "Takeover of loan Seller"));
        purposeOfLoans.add(new PurposeOfLoan("25", "Takeover of loan Customer"));
        purposeOfLoans.add(new PurposeOfLoan("206", "Life Insurance Loan- Home loan"));
        purposeOfLoans.add(new PurposeOfLoan("0", "Personal"));
        purposeOfLoans.add(new PurposeOfLoan("1", "Automobile"));
        purposeOfLoans.add(new PurposeOfLoan("2", "Boat"));
        purposeOfLoans.add(new PurposeOfLoan("3", "Mobile Home"));
        purposeOfLoans.add(new PurposeOfLoan("4", "Small Aritsan"));
        purposeOfLoans.add(new PurposeOfLoan("5", "Handloom Weavers"));
        purposeOfLoans.add(new PurposeOfLoan("6", "Service Sector"));
        purposeOfLoans.add(new PurposeOfLoan("7", "Fisherman"));
        purposeOfLoans.add(new PurposeOfLoan("8", "Self employed person"));
        purposeOfLoans.add(new PurposeOfLoan("9", "CV-Chassis"));
        purposeOfLoans.add(new PurposeOfLoan("10", "Working Capital"));
        purposeOfLoans.add(new PurposeOfLoan("11", "Rickshaw owner"));
        purposeOfLoans.add(new PurposeOfLoan("12", "Loan against property"));
        purposeOfLoans.add(new PurposeOfLoan("13", "Overdraft agaisnt Term Deposit"));
        purposeOfLoans.add(new PurposeOfLoan("20", "Acquisition of Fixed Assets"));
        purposeOfLoans.add(new PurposeOfLoan("21", "Shore up of working capital"));
        purposeOfLoans.add(new PurposeOfLoan("22", "Long term working capital"));
        purposeOfLoans.add(new PurposeOfLoan("23", "Takeover of loan"));
        purposeOfLoans.add(new PurposeOfLoan("24", "Takeover of loan Seller"));
        purposeOfLoans.add(new PurposeOfLoan("25", "Takeover of loan Customer"));
        purposeOfLoans.add(new PurposeOfLoan("30", "Investment"));
        purposeOfLoans.add(new PurposeOfLoan("40", "Personal"));
        purposeOfLoans.add(new PurposeOfLoan("50", "Consumer Durables"));
        purposeOfLoans.add(new PurposeOfLoan("60", "CV-Refinance"));
        purposeOfLoans.add(new PurposeOfLoan("70", "Others"));
        purposeOfLoans.add(new PurposeOfLoan("102", "Term Loan"));
        purposeOfLoans.add(new PurposeOfLoan("103", "Agricultural Term Ln"));
    }

    private void setSpinerAdapter() {
        List<String> purposeLoan = new ArrayList();
        int position = 0;
        for (int i = 0; i <= purposeOfLoans.size() - 1; i++) {
            position = i;
            purposeLoan.add(purposeOfLoans.get(i).getPurposeOfLoan());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.spinner_view, R.id.tvText, purposeLoan);
        binding.purposeSpinner.setAdapter(adapter);
        binding.purposeSpinner.setOnItemSelectedListener(this);
        selectedPurposeLoan = binding.purposeSpinner.getSelectedItem().toString();
        loanCode = purposeOfLoans.get(position).getPurposeCode();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        selectedPurposeLoan = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}