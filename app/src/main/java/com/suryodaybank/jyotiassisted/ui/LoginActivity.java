package com.suryodaybank.jyotiassisted.ui;

import static com.suryodaybank.jyotiassisted.utils.Constants.BRANCH_CODE;
import static com.suryodaybank.jyotiassisted.utils.Constants.UID1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.suryodaybank.jyotiassisted.R;
import com.suryodaybank.jyotiassisted.databinding.ActivityLoginBinding;
import com.suryodaybank.jyotiassisted.models.LoginResponse;
import com.suryodaybank.jyotiassisted.utils.GPSTracker;
import com.suryodaybank.jyotiassisted.utils.SharedPreferenceUtils;
import com.suryodaybank.jyotiassisted.viewmodels.LoginViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    private List<Address> addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        setupObserver();
        callVersionApi();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserLogin();
            }
        });
    }

    private void getUserLogin() {
        loginViewModel.getLoginApicall(binding.txtUsername.getText().toString(), binding.txtPassword.getText().toString());
    }

    private void callVersionApi() {
        loginViewModel.makeVersionCall();
        checkLocationPermission();
    }

    private void checkLocationPermission() {

        GPSTracker gpsTracker = new GPSTracker(LoginActivity.this);


        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                //showLocation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            Toast.makeText(LoginActivity.this, String.valueOf(latitude), Toast.LENGTH_SHORT).show();
            Toast.makeText(LoginActivity.this, String.valueOf(longitude), Toast.LENGTH_SHORT).show();
            try {
                Geocoder geocoder = new Geocoder(LoginActivity.this, Locale.getDefault());

                    addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                Toast.makeText(LoginActivity.this, addresses.get(0).getAddressLine(0), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            gpsTracker.showSettingsAlert();
        }
    }



    private void setupObserver() {
        loginViewModel.livedata.observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                createAlertDialog(o.toString());
            }
        });
        loginViewModel.loginlivedata.observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse data) {
                if (data != null) {
                    SharedPreferenceUtils.getInstance(LoginActivity.this).putString(UID1, data.getUID1());
                    SharedPreferenceUtils.getInstance(LoginActivity.this).putString(BRANCH_CODE, data.getBRCD());

                    Intent intent = new Intent(binding.getRoot().getContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        loginViewModel.errorlivedata.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
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