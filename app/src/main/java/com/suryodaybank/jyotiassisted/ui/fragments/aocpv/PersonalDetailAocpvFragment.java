package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import static android.app.Activity.RESULT_OK;
import static com.suryodaybank.jyotiassisted.utils.Constants.MY_CAMERA_PERMISSION_CODE;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.suryodaybank.jyotiassisted.databinding.FragmentPersonalDetailAocpvBinding;
import com.suryodaybank.jyotiassisted.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PersonalDetailAocpvFragment extends Fragment {
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    ActivityResultLauncher<Intent> cameraLaunch;
    ActivityResultLauncher<String> galleryLaunch;
    private FragmentPersonalDetailAocpvBinding binding;
    final Calendar myCalendar = Calendar.getInstance();

    public PersonalDetailAocpvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPersonalDetailAocpvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
        cameraLaunch = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Bundle bundle = result.getData().getExtras();
                            Bitmap bitmap = (Bitmap) bundle.get("data");
                            binding.customerImg.setImageBitmap(bitmap);
                        }
                    }
                });
        galleryLaunch = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        binding.customerImg.setImageURI(result);
                    }
                });
        binding.customerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForPermissions();
                selectImage();
            }
        });
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take Photo")) {
                    cameraIntent();
                    //openCameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    private void galleryIntent() {
        galleryLaunch.launch("image/*");
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
        intent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        cameraLaunch.launch(intent);
    }


    private void checkForPermissions() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        } else {
            Toast.makeText(getContext(), "Permission already granted", Toast.LENGTH_SHORT).show();
        }

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_CAMERA);
            } else {
                Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void setupViews() {

//        String[] titles = new String[]{"Mr.", "Mrs.", "Ms."};
//        ArrayAdapter<String> titleAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, titles);
//        binding.titleSpinner.setAdapter(titleAdapter);

//        String[] items = new String[]{"Male", "Female"};
//        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
//        binding.spinnerGender.setAdapter(genderAdapter);

        binding.etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), (view1, year, month, day) -> {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, day);
                    updateDateLabel();
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateDateLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.COMMON_DATE_FORMAT, Locale.US);
        binding.etDOB.setText(dateFormat.format(myCalendar.getTime()));
    }


}