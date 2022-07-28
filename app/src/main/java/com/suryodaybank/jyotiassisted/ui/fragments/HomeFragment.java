package com.suryodaybank.jyotiassisted.ui.fragments;

import static com.suryodaybank.jyotiassisted.utils.Constants.BRANCH_CODE;
import static com.suryodaybank.jyotiassisted.utils.Constants.CUSTOMER_MOBILE;
import static com.suryodaybank.jyotiassisted.utils.Constants.CUSTOMER_NAME;
import static com.suryodaybank.jyotiassisted.utils.Constants.IMAGE_PATH;
import static com.suryodaybank.jyotiassisted.utils.Constants.MY_CAMERA_PERMISSION_CODE;
import static com.suryodaybank.jyotiassisted.utils.Constants.UID1;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.suryodaybank.jyotiassisted.databinding.FragmentHomeBinding;
import com.suryodaybank.jyotiassisted.utils.SharedPreferenceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    String userId, branchName;
    private String userChoosenTask;
    File selectedFilePath;
    File pictureFile;
    ProgressDialog dialog;
    private String imageFilePath = "";
    File imageFile;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String userId = SharedPreferenceUtils.getInstance(getContext()).getString(UID1);
        String userName = SharedPreferenceUtils.getInstance(getContext()).getString(CUSTOMER_NAME);
        String userMobile = SharedPreferenceUtils.getInstance(getContext()).getString(CUSTOMER_MOBILE);
        String profile_pic = SharedPreferenceUtils.getInstance(getContext()).getString(IMAGE_PATH);
        if (!profile_pic.equalsIgnoreCase("")) {
            byte[] b = Base64.decode(profile_pic, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            binding.shapeableImageView.setImageBitmap(bitmap);
        }
        String branch_code = SharedPreferenceUtils.getInstance(getContext()).getString(BRANCH_CODE);
        Log.d("branch Code", branch_code);
        binding.branchCode.setText(branch_code);
        binding.tvName.setText(userName);
        binding.tvMobileNum.setText(userMobile);

        binding.shapeableImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForPermissions();
                selectImage();
            }
        });

    }

    private void checkForPermissions() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        } else {
            Toast.makeText(getContext(), "Permission already granted", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
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

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";

                    cameraIntent();
                    //openCameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";

                    galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
        intent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        startActivityForResult(intent, REQUEST_CAMERA);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == SELECT_FILE) {
                    Uri selectedImageUri = data.getData();
                    Bitmap photoBmp = null;
                    try {
                        if(  selectedImageUri!=null   ){
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver() , selectedImageUri);
                            saveProfilePic(bitmap);
                        }
                    }
                    catch (Exception e) {
                        //handle exception
                    }
                    // Get the path from the Uri
                    final String path = getPathFromURI(selectedImageUri);
                    //  onSelectFromGalleryResult(data);
                    if (path != null) {
                        File f = new File(path);
                        selectedImageUri = Uri.fromFile(f);
                    }
                    binding.shapeableImageView.setImageURI(selectedImageUri);
                } else if (requestCode == REQUEST_CAMERA)
                    onCaptureImageResult(data);
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
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


    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        binding.shapeableImageView.setImageBitmap(thumbnail);
        saveProfilePic(thumbnail);

        Uri selectedImageUri = data.getData();
        // Get the path from the Uri
        final String path = getPathFromURI(selectedImageUri);

        if (path != null) {
            File f = new File(path);
            selectedImageUri = Uri.fromFile(f);
        }
        binding.shapeableImageView.setImageURI(selectedImageUri);


        SharedPreferenceUtils.getInstance(getContext()).putString(IMAGE_PATH, selectedImageUri.toString());


    }

    private void saveProfilePic(Bitmap thumbnail) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        SharedPreferenceUtils.getInstance(getContext()).putString(IMAGE_PATH, encodedImage);


    }
}