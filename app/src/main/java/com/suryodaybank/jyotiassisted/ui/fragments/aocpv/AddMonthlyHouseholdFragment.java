package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.suryodaybank.jyotiassisted.R;
import com.suryodaybank.jyotiassisted.databinding.FragmentAddMonthlyHouseholdBinding;
import com.suryodaybank.jyotiassisted.models.MonthlyIncome;
import com.suryodaybank.jyotiassisted.models.Occupation;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddMonthlyHouseholdFragment extends Fragment implements AdapterView.OnItemSelectedListener  {

    private FragmentAddMonthlyHouseholdBinding binding;
    private AocpvViewModel aocpvViewModel;
    private String selectedMember;
    private List<Occupation> occupation = new ArrayList<>();
    private String selectedOccupation="";
    private String occCode="";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddMonthlyHouseholdBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aocpvViewModel = new ViewModelProvider(requireActivity()).get(AocpvViewModel.class);
        setOccupationList();
        setSpinnerAdapter();
        setupViews();
    }

    private void setupViews() {
        List<String> relationShip = new ArrayList<>();
        relationShip.add("Self");
        relationShip.add("Father");
        relationShip.add("Mother");
        relationShip.add("Husband");
        relationShip.add("Son");
        relationShip.add("Daughter");
        relationShip.add("Brother");
        relationShip.add("Sister");
        ArrayAdapter<String> memberAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, relationShip);
        memberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerMember.setAdapter(memberAdapter);
        binding.spinnerMember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMember = relationShip.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MonthlyIncome monthlyIncome = new MonthlyIncome();
                monthlyIncome.setFamilyMember(selectedMember);
                if (binding.rbYes.isChecked())
                    monthlyIncome.setEarningMember("Yes");
                else
                monthlyIncome.setEarningMember("No");
                monthlyIncome.setOccupation(selectedOccupation);
                monthlyIncome.setSourceOfIncome(binding.etSourceOfIncome.getText().toString());
                monthlyIncome.setSecuredLoan(binding.etSecuredLoan.getText().toString());
                monthlyIncome.setUnsecuredLoan(binding.etUnsecuredLoan.getText().toString());
                monthlyIncome.setMonthlyIncome(binding.etMonthlyIncome.getText().toString());
                monthlyIncome.setMonthlyLoanEmi(binding.etMonthlyLoanEMI.getText().toString());
                monthlyIncome.setOccCode(occCode);
                aocpvViewModel.addMonthlyIncome(monthlyIncome);

                NavController navController = Navigation.findNavController(binding.getRoot());
                navController.navigateUp();
            }
        });
    }

    private void setOccupationList(){
        occupation.add(new Occupation("Occ","DESCR"));
        occupation.add(new Occupation("A1","ACCOUNTANTS"));
        occupation.add(new Occupation("A2","ADMINISTRATOR"));
        occupation.add(new Occupation("A3","ADVISOR"));
        occupation.add(new Occupation("A","AGRICULTURE"));
        occupation.add(new Occupation("A4","ARCHITECT"));
        occupation.add(new Occupation("A5","ARTISAN"));
        occupation.add(new Occupation("A6","ATTENDANT"));
        occupation.add(new Occupation("A7","AUCTIONEERS"));
        occupation.add(new Occupation("A8","BAKER"));
        occupation.add(new Occupation("EB","BANK EMPLOYEE"));
        occupation.add(new Occupation("A9","BROKERS"));
        occupation.add(new Occupation("B","BUSINESS - RETAIL"));
        occupation.add(new Occupation("T1","BUSINESS SECTOR"));
        occupation.add(new Occupation("BW","BUSINESS WHOLESALE"));
        occupation.add(new Occupation("A10","CABINET MAKER"));
        occupation.add(new Occupation("A11","CARETAKER"));
        occupation.add(new Occupation("A12","CARPENTER"));
        occupation.add(new Occupation("A13","CHARTERED ACCOUNTANT"));
        occupation.add(new Occupation("A14","CLERK"));
        occupation.add(new Occupation("CS","COMPANY SECRETARY"));
        occupation.add(new Occupation("SCN","CONSULTANT"));
        occupation.add(new Occupation("A15","CONTRACTOR"));
        occupation.add(new Occupation("A16","COOK"));
        occupation.add(new Occupation("A17","CULTURAL ASSISTANT"));
        occupation.add(new Occupation("A18","DEFENCE PERSONNEL"));
        occupation.add(new Occupation("A19","DEPUTY COMMISSIONER"));
        occupation.add(new Occupation("A20","DISPENSER"));
        occupation.add(new Occupation("ED","DOCTOR"));
        occupation.add(new Occupation("A21","DRIVER"));
        occupation.add(new Occupation("A22","ECONOMIST"));
        occupation.add(new Occupation("A23","ELECTRICIAN"));
        occupation.add(new Occupation("EE","ENGINEER"));
        occupation.add(new Occupation("A24","EXECUTIVE ASSISTANT"));
        occupation.add(new Occupation("A25","FACTORY WORKER"));
        occupation.add(new Occupation("A26","FARM ACTIVITY"));
        occupation.add(new Occupation("A27","FILLING STATION WORKER"));
        occupation.add(new Occupation("A28","FIREMAN"));
        occupation.add(new Occupation("A29","FISHERMAN"));
        occupation.add(new Occupation("A30","FISHMONGER"));
        occupation.add(new Occupation("A31","FUND MANAGERS"));
        occupation.add(new Occupation("A32","GAMBLING / GAMING"));
        occupation.add(new Occupation("A33","GANGMAN"));
        occupation.add(new Occupation("A34","GARAGIST"));
        occupation.add(new Occupation("A35","GENERAL RETAILER"));
        occupation.add(new Occupation("A36","GOVERNMENT COMPANIES"));
        occupation.add(new Occupation("T2","GOVT SECTOR"));
        occupation.add(new Occupation("EG","GOVT. EMPLOYEE"));
        occupation.add(new Occupation("A37","HAIRDRESSER"));
        occupation.add(new Occupation("A38","HELPER"));
        occupation.add(new Occupation("HW","HOUSE WIFE"));
        occupation.add(new Occupation("T3","HOUSEWIFE"));
        occupation.add(new Occupation("IM","INDUSTRY MANUFACTURING"));
        occupation.add(new Occupation("IP","INDUSTRY PROCESSING"));
        occupation.add(new Occupation("EI","INFORMATION TECHNOLOGY"));
        occupation.add(new Occupation("A39","JUDGE"));
        occupation.add(new Occupation("A40","LABOURER"));
        occupation.add(new Occupation("EL","LAWYER"));
        occupation.add(new Occupation("A41","LECTURER"));
        occupation.add(new Occupation("A42","LIBRARIAN"));
        occupation.add(new Occupation("A43","LORRY DRIVER"));
        occupation.add(new Occupation("A44","MAID"));
        occupation.add(new Occupation("A45","MANAGER"));
        occupation.add(new Occupation("A46","MANUAL LABOURER"));
        occupation.add(new Occupation("A47","MARKET EMPLOYEE"));
        occupation.add(new Occupation("A48","MARKETING ASSISTANT"));
        occupation.add(new Occupation("A49","MASON"));
        occupation.add(new Occupation("A50","MESSENGER"));
        occupation.add(new Occupation("A51","MINOR"));
        occupation.add(new Occupation("A52","MONEYLENDERS"));
        occupation.add(new Occupation("0","N/A"));
        occupation.add(new Occupation("A53","NON-FARM ACTIVITY"));
        occupation.add(new Occupation("A54","NOTARIES (SMALL, LITTLE KNOWN)"));
        occupation.add(new Occupation("A55","NURSE"));
        occupation.add(new Occupation("A56","OFFICER"));
        occupation.add(new Occupation("A57","OPERATOR"));
        occupation.add(new Occupation("A58","OTHER - PROFESSIONAL"));
        occupation.add(new Occupation("EO","OTHERS"));
        occupation.add(new Occupation("A59","OTHERS - AGRICULTURE"));
        occupation.add(new Occupation("A60","OVERSEER"));
        occupation.add(new Occupation("A61","PAWN BROKERS"));
        occupation.add(new Occupation("A62","PENSIONER"));
        occupation.add(new Occupation("A63","PHARMACIST"));
        occupation.add(new Occupation("A64","PLANTER"));
        occupation.add(new Occupation("A65","PLUMBER"));
        occupation.add(new Occupation("A66","POLICEMAN"));
        occupation.add(new Occupation("A67","POST GRADUATE"));
        occupation.add(new Occupation("A68","POSTMAN"));
        occupation.add(new Occupation("A69","PRIEST"));
        occupation.add(new Occupation("T4","PRIVATE SECTOR"));
        occupation.add(new Occupation("A70","PRIVATE TAILOR"));
        occupation.add(new Occupation("T5","PROFESSIONAL"));
        occupation.add(new Occupation("PA","PROFESSIONAL - ARCHITECT"));
        occupation.add(new Occupation("PCA","PROFESSIONAL - CA/CS"));
        occupation.add(new Occupation("PD","PROFESSIONAL - DOCTOR"));
        occupation.add(new Occupation("PRE","PROFESSIONAL - ENGINEER"));
        occupation.add(new Occupation("PL","PROFESSIONAL - LAWER"));
        occupation.add(new Occupation("A71","PROFESSOR"));
        occupation.add(new Occupation("A72","PROGRAMMER"));
        occupation.add(new Occupation("A73","PROOF READER"));
        occupation.add(new Occupation("PRC","PROSSIONAL - CONSULTANT"));
        occupation.add(new Occupation("T6","PUBLIC SECTOR"));
        occupation.add(new Occupation("A74","QUANTITY SURVEYOR"));
        occupation.add(new Occupation("A75","REAL ESTATE PROMOTERS"));
        occupation.add(new Occupation("A76","RECTOR"));
        occupation.add(new Occupation("A77","RELIEF WORKER"));
        occupation.add(new Occupation("T7","RETIRED"));
        occupation.add(new Occupation("RS","RETIRED SERVICE"));
        occupation.add(new Occupation("SAL","SALARIED"));
        occupation.add(new Occupation("SAG","SALARY - GOVERNMENT"));
        occupation.add(new Occupation("SAM","SALARY - MNC"));
        occupation.add(new Occupation("SAO","SALARY - OTHERS"));
        occupation.add(new Occupation("SPA","SALARY - PARTNERSHIP"));
        occupation.add(new Occupation("SPR","SALARY - PROPRIETORSHIP"));
        occupation.add(new Occupation("SPU","SALARY - PUBLIC LTD"));
        occupation.add(new Occupation("SPL","SALARY - PVT LTD"));
        occupation.add(new Occupation("A78","SALESMAN"));
        occupation.add(new Occupation("A79","SECRETARY"));
        occupation.add(new Occupation("T8","SELF EMPLOYED"));
        occupation.add(new Occupation("SE","SELF EMPLOYED"));
        occupation.add(new Occupation("SEA","SELF EMPLOYED - AGRICULTURE"));
        occupation.add(new Occupation("SEM","SELF EMPLOYED - MANUFACTURING"));
        occupation.add(new Occupation("SEO","SELF EMPLOYED - OTHERS"));
        occupation.add(new Occupation("SRE","SELF EMPLOYED - REAL ESTATE"));
        occupation.add(new Occupation("SER","SELF EMPLOYED - RETAIL"));
        occupation.add(new Occupation("SES","SELF EMPLOYED - SERVICES"));
        occupation.add(new Occupation("SEB","SELF EMPLOYED - STOCK BROKING"));
        occupation.add(new Occupation("SET","SELF EMPLOYED - TRADING"));
        occupation.add(new Occupation("SEC","SELF EMPLOYED CHARTED ACCT."));
        occupation.add(new Occupation("SED","SELF EMPLOYED DOCTOR"));
        occupation.add(new Occupation("SEE","SELF EMPLOYED ENGINEER"));
        occupation.add(new Occupation("SEL","SELF EMPLOYED LAWYER"));
        occupation.add(new Occupation("SEP","SELF EMPLOYED PROFESSION"));
        occupation.add(new Occupation("A80","SERVICE - PRIVATE SECTOR"));
        occupation.add(new Occupation("A81","SERVICE - PUBLIC SECTOR"));
        occupation.add(new Occupation("SM","SERVICE MAN"));
        occupation.add(new Occupation("SP","SERVICE PROVIDER"));
        occupation.add(new Occupation("A82","SERVICES - OTHERS"));
        occupation.add(new Occupation("A83","SMALL ROAD TRANSPORT OPERATOR"));
        occupation.add(new Occupation("A84","SOFTWARE ENGINEER"));
        occupation.add(new Occupation("A85","STOREKEEPER"));
        occupation.add(new Occupation("ST","STUDENT"));
        occupation.add(new Occupation("A86","SUPERVISOR"));
        occupation.add(new Occupation("A87","SYSTEMS ANALYST"));
        occupation.add(new Occupation("A88","TAILOR"));
        occupation.add(new Occupation("A89","TAXI DRIVER"));
        occupation.add(new Occupation("ET","TEACHER/LECTURER/PROFESSOR"));
        occupation.add(new Occupation("A90","TECHNICIAN"));
        occupation.add(new Occupation("A91","TIMEKEEPER"));
        occupation.add(new Occupation("A92","TRADER - RETAIL / WHOLESALE"));
        occupation.add(new Occupation("A93","TRADER - STREET VENDOR"));
        occupation.add(new Occupation("A94","TRAVEL AGENT"));
        occupation.add(new Occupation("A95","TRUSTEES / CUSTODIANS"));
        occupation.add(new Occupation("A96","TYPIST"));
        occupation.add(new Occupation("A97","UNEMPLOYED"));
        occupation.add(new Occupation("A98","WAITER"));
        occupation.add(new Occupation("A99","WATCHMAKER"));
        occupation.add(new Occupation("A100","WATCHMAN"));
        occupation.add(new Occupation("A101","WELDER"));
        occupation.add(new Occupation("B1","ADVOCATE"));
        occupation.add(new Occupation("B2","AUTO DRIVER CUM OWNER"));
        occupation.add(new Occupation("B3","COLLECTION AGENCY OWNER"));
        occupation.add(new Occupation("B4","COLLECTION AGENT"));
        occupation.add(new Occupation("B5","DRIVING SCHOOL EMPLOYEES"));
        occupation.add(new Occupation("B6","FUNDS / SMALL TIME INVESTMENT COMPANY"));
        occupation.add(new Occupation("B7","LIQUOR/WINE SHOP OWNER"));
        occupation.add(new Occupation("B8","LOCAL MONEY LENDER / PAWN BROKER"));
        occupation.add(new Occupation("B9","MULTI LEVEL MARKETING AGENTS/EMPLOYEES"));
        occupation.add(new Occupation("B10","NRI / NRE"));
        occupation.add(new Occupation("B11","OFF ROLLS EXECUTIVE"));
        occupation.add(new Occupation("B12","POLITICIAN / POLITICAL INFLUENCED PERSON"));
        occupation.add(new Occupation("B13","REPOSSESSION AGENT"));
        occupation.add(new Occupation("B14","SECURITY AGENCY"));
        occupation.add(new Occupation("B15","SECURITY GUARD"));
        occupation.add(new Occupation("B16","SMALL INVESTMENTS COMPANY – LOCAL CHIT"));
        occupation.add(new Occupation("B17","STD_BOOTH STAND ALONE"));
        occupation.add(new Occupation("B18","TWO-WHEELER MECHANICS / CONSULTANT"));
        occupation.add(new Occupation("B19","Any other Profiles under Daily / Weekly / Bi-Monthly Income Earners"));
        occupation.add(new Occupation("B20","Construction Workers"));
        occupation.add(new Occupation("B21","Contract Laborer’s"));
        occupation.add(new Occupation("B22","Daily Wage"));
        occupation.add(new Occupation("B23","Employees of Hotel / Small Time Restaurants / Fast Food Centre"));
        occupation.add(new Occupation("B24","Finance Company Employees – Collection and Seizure Activities"));
        occupation.add(new Occupation("B25","Garage Mechanics"));
        occupation.add(new Occupation("B26","JOB WORKER"));
        occupation.add(new Occupation("B27","Loan Man"));
        occupation.add(new Occupation("B28","Provision Store Employees"));
        occupation.add(new Occupation("B29","Puncture Shop Owners and Employees (Not New Tyre Shop)"));
        occupation.add(new Occupation("B30","Railway Porter"));
        occupation.add(new Occupation("B31","Scrap Merchants"));
        occupation.add(new Occupation("B32","Share Brokers"));
        occupation.add(new Occupation("B33","TEMPORAY STALL / PLATFORM VENDORS"));
    }

    private void setSpinnerAdapter(){
        List<String> occupationName = new ArrayList();
        int position =0;
        for (int i=0;i<=occupation.size()-1;i++){
            position = i;
            occupationName.add(occupation.get(i).getOccupation());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_view,R.id.tvText, occupationName);
        binding.occupationSpinner.setAdapter(adapter);
        binding.occupationSpinner.setOnItemSelectedListener(this);
        selectedOccupation = binding.occupationSpinner.getSelectedItem().toString();
        occCode = occupation.get(position).getId();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        selectedOccupation = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}