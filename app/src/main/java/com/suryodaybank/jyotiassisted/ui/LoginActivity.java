package com.suryodaybank.jyotiassisted.ui;

import static com.suryodaybank.jyotiassisted.utils.Constants.BRANCH_CODE;
import static com.suryodaybank.jyotiassisted.utils.Constants.UID1;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.suryodaybank.jyotiassisted.R;
import com.suryodaybank.jyotiassisted.databinding.ActivityLoginBinding;
import com.suryodaybank.jyotiassisted.models.Data;
import com.suryodaybank.jyotiassisted.utils.SharedPreferenceUtils;
import com.suryodaybank.jyotiassisted.viewmodels.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.scopes.ActivityScoped;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        callVersionApi();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getUserLogin();

//

            }


        });

    }

    private void getUserLogin() {
        setupLoginObserver();
        setupErrorObserver();
        loginViewModel.getLoginApicall(binding.txtUsername.getText().toString(), binding.txtPassword.getText().toString());
    }

    private void setupErrorObserver() {
        loginViewModel.errorlivedata.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setupLoginObserver() {
        loginViewModel.getLoginLivedata().observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {

                 if(data!=null){

                    SharedPreferenceUtils.getInstance(LoginActivity.this).putString(UID1, data.getUID1());
                    SharedPreferenceUtils.getInstance(LoginActivity.this).putString(BRANCH_CODE, data.getBRCD());

                    Intent intent = new Intent(binding.getRoot().getContext(), MainActivity.class);
                    binding.getRoot().getContext().startActivity(intent);}
                }



        });
    }


    private void callVersionApi() {
        setupObserver();
        loginViewModel.makeVersionCall();
    }

    private void setupObserver() {
        loginViewModel.getLivedata().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                createAlertDialog(o.toString());
            }
        });

    }

    private void createAlertDialog(String s) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage(s)
                .setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        LoginActivity.this.finish();
                    }
                });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();


        // show it
        alertDialog.show();
    }
}