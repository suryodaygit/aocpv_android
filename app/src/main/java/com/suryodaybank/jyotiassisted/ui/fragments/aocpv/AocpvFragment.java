package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.suryodaybank.jyotiassisted.R;
import com.suryodaybank.jyotiassisted.databinding.FragmentAocpvBinding;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AocpvFragment extends Fragment {

    private static final int TOTAL_PAGE = 5;
    private static final String TAG = "AocpvFragment";

    private FragmentAocpvBinding binding;
    private AocpvViewModel aocpvViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAocpvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aocpvViewModel = new ViewModelProvider(requireActivity()).get(AocpvViewModel.class);
        setupViews();
        setupObserver();
    }

    private void setupObserver() {
        aocpvViewModel.pageNoLivedata.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvPageNo.setText(getString(R.string.page_no, integer, TOTAL_PAGE));
            }
        });
        aocpvViewModel.nextPage.observe(getViewLifecycleOwner(), new Observer<Void>() {
            @Override
            public void onChanged(Void moveNext) {
                moveToNextPage();
            }
        });
        aocpvViewModel.messageLiveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupViews() {
        setupViewPager();
        binding.btnNext.setOnClickListener(view -> {
            showConfirmationDialog();
        });
        binding.btnPrevious.setOnClickListener(view -> {
            moveToPreviousPage();
        });
    }

    private void setupViewPager() {
        AocpvSlidePagerAdapter pagerAdapter = new AocpvSlidePagerAdapter(getActivity());
        binding.aocpvViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                aocpvViewModel.pageNoLivedata.setValue(binding.aocpvViewPager.getCurrentItem() + 1);
//                Log.d(TAG, "onPageScrolled: " + position);
            }
        });
        binding.aocpvViewPager.setAdapter(pagerAdapter);
        binding.aocpvViewPager.setUserInputEnabled(false);
    }

    private void showConfirmationDialog() {
        int currentItem = binding.aocpvViewPager.getCurrentItem();
        int message = R.string.save_confirmation_dialog;
        if (currentItem == 0) {
            message = R.string.check_message;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.confirm);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                aocpvViewModel.saveData(currentItem);
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (currentItem == 0) {
                    //Go back if personal details are wrong
                    NavHostFragment.findNavController(AocpvFragment.this).navigateUp();
                }
            }
        });
        builder.show();
    }

    private void moveToNextPage() {
        int currentItem = binding.aocpvViewPager.getCurrentItem();
        if (currentItem < TOTAL_PAGE - 1) {
            binding.aocpvViewPager.setCurrentItem(currentItem + 1);
            aocpvViewModel.pageNoLivedata.setValue(binding.aocpvViewPager.getCurrentItem() + 1);
        } else {
            Navigation.findNavController(binding.getRoot())
                    .navigate(AocpvFragmentDirections.actionAocpvFragmentToAocpvValidationFragment());
        }
    }

    private void moveToPreviousPage() {
        int currentItem = binding.aocpvViewPager.getCurrentItem();
        binding.aocpvViewPager.setCurrentItem(currentItem - 1);
        aocpvViewModel.pageNoLivedata.setValue(binding.aocpvViewPager.getCurrentItem() + 1);
    }

    private static class AocpvSlidePagerAdapter extends FragmentStateAdapter {
        public AocpvSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new PersonalDetailAocpvFragment();
                case 1:
                    return new OwnershipDetailsAocpvFragment();
                case 2:
                    return new MonthlyIncomeAocpvFragment();
                case 3:
                    return new MonthlyExpenseAocpvFragment();
                default:
                    return new MfiClassificationAocpvFragment();
            }
        }

        @Override
        public int getItemCount() {
            return TOTAL_PAGE;
        }
    }
}