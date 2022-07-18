package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.suryodaybank.jyotiassisted.R;
import com.suryodaybank.jyotiassisted.databinding.FragmentAocpvBinding;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AocpvFragment extends Fragment {

    private static final int TOTAL_PAGE = 5;

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
    }

    private void setupViews() {
        setupViewPager();
        binding.btnNext.setOnClickListener(view -> {
            int currentItem = binding.aocpvViewPager.getCurrentItem();
            if (currentItem < TOTAL_PAGE - 1) {
                binding.aocpvViewPager.setCurrentItem(currentItem + 1);
                aocpvViewModel.pageNoLivedata.setValue(binding.aocpvViewPager.getCurrentItem() + 1);
            } else {
                Navigation.findNavController(binding.getRoot())
                        .navigate(AocpvFragmentDirections.actionAocpvFragmentToAocpvValidationFragment());
            }
        });
        binding.btnPrevious.setOnClickListener(view -> {
            int currentItem = binding.aocpvViewPager.getCurrentItem();
            binding.aocpvViewPager.setCurrentItem(currentItem - 1);
            aocpvViewModel.pageNoLivedata.setValue(binding.aocpvViewPager.getCurrentItem() + 1);
        });
    }

    private void setupViewPager() {
        AocpvSlidePagerAdapter pagerAdapter = new AocpvSlidePagerAdapter(getActivity());
        binding.aocpvViewPager.setAdapter(pagerAdapter);
        binding.aocpvViewPager.setUserInputEnabled(false);
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
                    return new ClassificationAocpvFragment();
            }
        }

        @Override
        public int getItemCount() {
            return TOTAL_PAGE;
        }
    }
}