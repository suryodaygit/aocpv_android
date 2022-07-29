package com.suryodaybank.jyotiassisted.ui.fragments.aocpv;

import static android.app.Activity.RESULT_OK;
import static com.suryodaybank.jyotiassisted.utils.Constants.ADDRESS;
import static com.suryodaybank.jyotiassisted.utils.Constants.LATITUDE;
import static com.suryodaybank.jyotiassisted.utils.Constants.LONGITUDE;

import static com.suryodaybank.jyotiassisted.utils.Constants.MY_CAMERA_PERMISSION_CODE;
import static com.suryodaybank.jyotiassisted.utils.Constants.RESIDENT_STABILITY;
import static com.suryodaybank.jyotiassisted.utils.Constants.ROOF_TYPE;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import com.suryodaybank.jyotiassisted.databinding.FragmentOwnershipDetailsAocpvBinding;
import com.suryodaybank.jyotiassisted.models.UtilityAddressItem;
import com.suryodaybank.jyotiassisted.models.UtilityDataRequest;
import com.suryodaybank.jyotiassisted.ui.LoginActivity;
import com.suryodaybank.jyotiassisted.utils.SharedPreferenceUtils;
import com.suryodaybank.jyotiassisted.utils.Utils;
import com.suryodaybank.jyotiassisted.viewmodels.AocpvViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OwnershipDetailsAocpvFragment extends Fragment {
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    private FragmentOwnershipDetailsAocpvBinding binding;
    ActivityResultLauncher<Intent> cameraLaunchUtility;
    ActivityResultLauncher<Intent> cameraLaunchBusiness;
    ActivityResultLauncher<String> galleryLaunchUtility;
    ActivityResultLauncher<String> galleryLaunchBusiness;
    private String lat = "";
    private String lang = "";
    private String address = "";

    private String userChoosenTask;

    private AocpvViewModel aocpvViewModel;
    private String encoded_image_string;
    private String utilityImageEncoded;
    private String businessImageEncoded;
    private RadioButton selectedRadioButton;
    private String selectedRadioButtonText = "";
    public OwnershipDetailsAocpvFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOwnershipDetailsAocpvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aocpvViewModel = new ViewModelProvider(requireActivity()).get(AocpvViewModel.class);
        lat = SharedPreferenceUtils.getInstance(getContext()).getString(LATITUDE);
        lang = SharedPreferenceUtils.getInstance(getContext()).getString(LONGITUDE);
        address = SharedPreferenceUtils.getInstance(getContext()).getString(ADDRESS);
        setupViews();
        setUpObserver();

        cameraLaunchUtility = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK && result.getData()!=null){
                            Bundle bundle = result.getData().getExtras();
                            Bitmap bitmap = (Bitmap) bundle.get("data");
                            binding.billImg.setImageBitmap(bitmap);
                            int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                            utilityImageEncoded = convertbitmaptoString(scaled);
                        }
                    }
                });

        cameraLaunchBusiness = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK && result.getData()!=null){
                            Bundle bundle = result.getData().getExtras();
                            Bitmap bitmap = (Bitmap) bundle.get("data");
                            binding.businessImg.setImageBitmap(bitmap);
                            int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                            businessImageEncoded = convertbitmaptoString(scaled);
                        }
                    }
                });

        galleryLaunchUtility = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        binding.billImg.setImageURI(result);

                        try {

                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver() , result);
                            int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                            utilityImageEncoded = convertbitmaptoString(scaled);

                        }
                        catch (Exception e) {
                            //handle exception
                        }
                    }
                });

        galleryLaunchBusiness = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        binding.businessImg.setImageURI(result);
                        try {

                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver() , result);
                            int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                            businessImageEncoded = convertbitmaptoString(scaled);

                        }
                        catch (Exception e) {
                            //handle exception
                        }
                    }
                });

        
        binding.billImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForPermissions();
                selectImage("utilityPhoto");
            }
        });
        binding.businessImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForPermissions();
                selectImage("businessPhoto");
            }
        });
    }

    private String convertbitmaptoString(Bitmap scaled) {
        Bitmap converetdImage = getResizedBitmap(scaled, 312);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        converetdImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        encoded_image_string = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return encoded_image_string;
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void setUpObserver() {
        aocpvViewModel.getUtilityDetails.observe(getViewLifecycleOwner(), new Observer<Void>() {
            @Override
            public void onChanged(Void unused) {
                if(selectedRadioButtonText.equals("") ||
                        binding.etAddLine1.getText().toString().equals("")
                        || binding.etPincode.getText().equals("") || binding.etResidenceStability.getText().equals("")) {
                    Utils.showFinalSuccessMessage(getActivity(),"Please set roof type,residence stability,address line1,pincode");
                }else {
                    prepareOwnershipDetails();
                }
            }
        });
    }

    private void prepareOwnershipDetails() {
        SharedPreferenceUtils.getInstance(getActivity()).putString(RESIDENT_STABILITY, binding.etResidenceStability.getText().toString());
        UtilityDataRequest utilityDataRequest = new UtilityDataRequest();
        utilityDataRequest.setFlowStatus("UD");
        utilityDataRequest.setApplicationNo("12345681");
        utilityDataRequest.setHouseOwnership(binding.ownershipSpinner.getSelectedItem().toString());
        utilityDataRequest.setRoofType(selectedRadioButtonText);
        utilityDataRequest.setResidenceStability(binding.etResidenceStability.getText().toString());
        utilityDataRequest.setUtilityBill(binding.utilitySpinner.getSelectedItem().toString());
        utilityDataRequest.setRelationshipWithOwner(binding.ownerSpinner.getSelectedItem().toString());
        utilityDataRequest.setUtilityBillPhoto(utilityImageEncoded);
        utilityDataRequest.setbLong(lang);
        utilityDataRequest.setbLat(lat);
        utilityDataRequest.setbAddress(address);
        ArrayList<UtilityAddressItem> addressItems = new ArrayList<>();
        UtilityAddressItem utilityAddressItem = new UtilityAddressItem();
        utilityAddressItem.setAddressLine1(binding.etAddLine1.getText().toString());
        utilityAddressItem.setAddressLine2(binding.etAddLine2.getText().toString());
        utilityAddressItem.setAddressLine3(binding.etAddLine3.getText().toString());
        utilityAddressItem.setPincode(binding.etPincode.getText().toString());
        utilityAddressItem.setCity(binding.etCity.getText().toString());
        utilityAddressItem.setState(binding.etState.getText().toString());

        addressItems.add(0,utilityAddressItem);
        utilityDataRequest.setAddress(addressItems);
        ArrayList<String> otherAsset = new ArrayList<>();


        if(binding.checkBox1.isChecked()){
            otherAsset.add(binding.checkBox1.getText().toString());
        }else if(binding.checkBox2.isChecked()){
            otherAsset.add(binding.checkBox2.getText().toString());
        }else if(binding.checkBox3.isChecked()){
            otherAsset.add(binding.checkBox3.getText().toString());
        }else if(binding.checkBox4.isChecked()){
            otherAsset.add(binding.checkBox4.getText().toString());
        }

        utilityDataRequest.setOtherAssets(otherAsset);
        utilityDataRequest.setVintage(binding.etVintage.getText().toString());
        utilityDataRequest.setBuisnessPhoto(businessImageEncoded);

        aocpvViewModel.callOwnershipDetailAPI(utilityDataRequest);
    }

    private void checkForPermissions() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_CAMERA);
            } else {
                Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void selectImage(String cameraPhoto) {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    cameraIntent(cameraPhoto);
                    //openCameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    galleryIntent(cameraPhoto);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent(String cameraPhoto) {
        if(cameraPhoto.equalsIgnoreCase("utilityPhoto")){
       galleryLaunchUtility.launch("image/*");
        }else {
            galleryLaunchBusiness.launch("image/*");
        }
    }

    private void cameraIntent(String cameraPhoto) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
        intent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);

        if(cameraPhoto.equalsIgnoreCase("utilityPhoto")){
            cameraLaunchUtility.launch(intent);

        }else {
            cameraLaunchBusiness.launch(intent);
        }
    }



    private String getPathFromURI(Uri selectedImageUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContext().getContentResolver().query(selectedImageUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
    private void onCaptureImageResult(Intent data, String uPhoto) {
        if(uPhoto.equalsIgnoreCase("utilityPhoto")){
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            binding.billImg.setImageBitmap(thumbnail);
            // saveProfilePic(thumbnail);

            Uri selectedImageUri = data.getData();
            // Get the path from the Uri
            final String path = getPathFromURI(selectedImageUri);

            if (path != null) {
                File f = new File(path);
                selectedImageUri = Uri.fromFile(f);
            }

            binding.billImg.setImageURI(selectedImageUri);
        }else {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            binding.businessImg.setImageBitmap(thumbnail);
            // saveProfilePic(thumbnail);

            Uri selectedImageUri = data.getData();
            // Get the path from the Uri
            final String path = getPathFromURI(selectedImageUri);

            if (path != null) {
                File f = new File(path);
                selectedImageUri = Uri.fromFile(f);
            }

            binding.businessImg.setImageURI(selectedImageUri);
        }


    }


    private void setupViews() {
        binding.cgRoofType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedButtonId != -1) {
                    selectedRadioButton = radioGroup.findViewById(selectedButtonId);
                    selectedRadioButtonText = selectedRadioButton.getText().toString();
                    SharedPreferenceUtils.getInstance(getActivity()).putString(ROOF_TYPE, selectedRadioButtonText);
                }
            }
        });

        List<String> ownership = new ArrayList<>();
        ownership.add("Own");
        ownership.add("Rented");
        ownership.add("Ancestral");
        ArrayAdapter<String> ownershipAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ownership);
        ownershipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ownershipSpinner.setAdapter(ownershipAdapter);

        List<String> relationShip = new ArrayList<>();
        relationShip.add("Self");
        relationShip.add("Father");
        relationShip.add("Mother");
        relationShip.add("Husband");
        relationShip.add("Son");
        relationShip.add("Daughter");
        relationShip.add("Brother");
        relationShip.add("Sister");
        ArrayAdapter<String> relationShipAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, relationShip);
        relationShipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ownerSpinner.setAdapter(relationShipAdapter);

        List<String> utilityBill = new ArrayList<>();
        utilityBill.add(0, "Please select");
        utilityBill.add("ELECTRICITY BILL");
        utilityBill.add("GAS BILL");
        utilityBill.add("WATER BILL");
        utilityBill.add("MUNICIPAL TAX BILL");
        ArrayAdapter<String> utilityAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, utilityBill);
        utilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.utilitySpinner.setAdapter(utilityAdapter);
    }


}