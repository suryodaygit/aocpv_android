package com.suryodaybank.jyotiassisted.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.suryodaybank.jyotiassisted.R;
import com.suryodaybank.jyotiassisted.databinding.ActivityLoginBinding;
import com.suryodaybank.jyotiassisted.viewmodels.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.scopes.ActivityScoped;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViewModel();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(binding.getRoot().getContext(), MainActivity.class);
//                binding.getRoot().getContext().startActivity(intent);







            }


        });

    }

    private void initViewModel() {
        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.getLivedata().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Toast.makeText(LoginActivity.this, "Check", Toast.LENGTH_SHORT).show();
            }
        });
        loginViewModel.makeVersionCall();
    }
}