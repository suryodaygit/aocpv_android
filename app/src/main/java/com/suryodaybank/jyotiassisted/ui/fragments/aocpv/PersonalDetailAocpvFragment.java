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
import android.util.Base64;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.suryodaybank.jyotiassisted.databinding.FragmentPersonalDetailAocpvBinding;
import com.suryodaybank.jyotiassisted.models.AddressItem;
import com.suryodaybank.jyotiassisted.models.CRMCustDataResponseItem;
import com.suryodaybank.jyotiassisted.models.CustomerSaveData;
import com.suryodaybank.jyotiassisted.utils.Constants;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PersonalDetailAocpvFragment extends Fragment {
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    ActivityResultLauncher<Intent> cameraLaunch;
    ActivityResultLauncher<String> galleryLaunch;
    private FragmentPersonalDetailAocpvBinding binding;
    final Calendar myCalendar = Calendar.getInstance();

    private AocpvViewModel aocpvViewModel;
    private String encoded_image;

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
        aocpvViewModel = new ViewModelProvider(requireActivity()).get(AocpvViewModel.class);
        setupViews();
        setupObserver();
        cameraLaunch = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Bundle bundle = result.getData().getExtras();
                            Bitmap bitmap = (Bitmap) bundle.get("data");
                            binding.customerImg.setImageBitmap(bitmap);
                            int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                            convertbitmaptoString(scaled);
                          
                        }
                    }
                });
        galleryLaunch = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        binding.customerImg.setImageURI(result);
                        try {

                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver() , result);
                            int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                            convertbitmaptoString(scaled);

                        }
                        catch (Exception e) {
                            //handle exception
                        }
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

    private void convertbitmaptoString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
         encoded_image = Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private void setupObserver() {


        aocpvViewModel.customerQueryLiveData.observe(getViewLifecycleOwner(), new Observer<List<CRMCustDataResponseItem>>() {
            @Override
            public void onChanged(List<CRMCustDataResponseItem> crmCustDataResponseItems) {

                if (crmCustDataResponseItems != null) {

                    CustomerSaveData customerSaveData = new CustomerSaveData();


                    binding.etCustomerId.setText(crmCustDataResponseItems.get(0).getCIFNUMBER());
                    String currentString = crmCustDataResponseItems.get(0).getDOB();
                    String[] separated = currentString.split("-");
                    String newDate =separated[2]+"-"+separated[1]+"-"+separated[0]; // the date will be in dd-mm-yyyy format in String
                    binding.etDOB.setText(newDate);
                    binding.etFirstName.setText(crmCustDataResponseItems.get(0).getnAMEMOBILEOWNER());
                    binding.etMobileNum.setText(crmCustDataResponseItems.get(0).getrEGISTEREDMOBILE());
                    int index = 2;

//                    for (int i=0 ; i< 2; i++){
//                        if(crmCustDataResponseItems.get(0).getAddressDetails().getAddressDet().get(i).getADDRESSTYPE().equalsIgnoreCase("Permanent")){
//                            index = i;
//                            Log.e("check index",String.valueOf(index));
//                        }
//
//                    }


                    binding.etAddLine1.setText(crmCustDataResponseItems.get(0).getAddressDetails().getAddressDet().get(index).getADDRESS1());
                    binding.etAddLine2.setText(crmCustDataResponseItems.get(0).getAddressDetails().getAddressDet().get(index).getADDRESS2());
                    binding.etAddLine3.setText(crmCustDataResponseItems.get(0).getAddressDetails().getAddressDet().get(index).getADDRESS3());
                    binding.etCity.setText(crmCustDataResponseItems.get(0).getAddressDetails().getAddressDet().get(index).getCITY());
                    binding.etState.setText(crmCustDataResponseItems.get(0).getAddressDetails().getAddressDet().get(index).getSTATE());
                    binding.etPincode.setText(crmCustDataResponseItems.get(0).getAddressDetails().getAddressDet().get(index).getPINCODE());
                    binding.etDistrict.setText(crmCustDataResponseItems.get(0).getAddressDetails().getAddressDet().get(index).getDISTRICT());


                } else {
                    Toast.makeText(getContext(), "Something Went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });

        aocpvViewModel.getCustomerDetails.observe(getViewLifecycleOwner(), unused -> {
                    prepareCustomerDetails();
                }
        );

//        aocpvViewModel.getMonthlyIncomeData.observe(getViewLifecycleOwner(), unused -> {
//            prepareDataAndCallApi();


    }

    private void prepareCustomerDetails() {
        AddressItem addressDetItem = new AddressItem();
        ArrayList<AddressItem> addressDetItems = new ArrayList<>();
        addressDetItem.setAddressLine1(binding.etAddLine1.getText().toString());
        addressDetItem.setAddressLine2(binding.etAddLine2.getText().toString());
        addressDetItem.setAddressLine3(binding.etAddLine3.getText().toString());
        addressDetItem.setCity(binding.etCity.getText().toString());
        addressDetItem.setPincode(binding.etPincode.getText().toString());
        addressDetItem.setDistrict(binding.etDistrict.getText().toString());
        addressDetItem.setState(binding.etState.getText().toString());
        addressDetItem.setCountry("india");
        addressDetItems.add(0, addressDetItem);

        CustomerSaveData customerSaveData = new CustomerSaveData();
        customerSaveData.setFlowStatus("PD");
        customerSaveData.setApplicationNo("12345681");
        customerSaveData.setCustomerId(binding.etCustomerId.getText().toString());
        customerSaveData.setName(binding.etFirstName.getText().toString());
        customerSaveData.setMobileNo(binding.etMobileNum.getText().toString());
        customerSaveData.setDateOfBirth(binding.etDOB.getText().toString());
        customerSaveData.setBranchId("10011");
        customerSaveData.setImage(encoded_image);
        customerSaveData.setAddress(addressDetItems);

        aocpvViewModel.callPersonalDetailAPI(customerSaveData);
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