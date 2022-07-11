package com.suryodaybank.jyotiassisted.ui.fragments;

import static com.suryodaybank.jyotiassisted.utils.Constants.BRANCH_CODE;
import static com.suryodaybank.jyotiassisted.utils.Constants.UID1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.suryodaybank.jyotiassisted.databinding.FragmentHomeBinding;
import com.suryodaybank.jyotiassisted.utils.Constants;
import com.suryodaybank.jyotiassisted.utils.FilePath;
import com.suryodaybank.jyotiassisted.utils.SharedPreferenceUtils;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
        String branch_code = SharedPreferenceUtils.getInstance(getContext()).getString(BRANCH_CODE);
        Log.d("branch Code", branch_code);
        binding.branchCode.setText(branch_code);
        binding.shapeableImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library","Cancel"};

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
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
        intent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        startActivityForResult(intent, REQUEST_CAMERA);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void onSelectFromGalleryResult(Intent data) {
        imageFilePath = String.valueOf(data.getData());
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), Uri.parse(imageFilePath));
            binding.shapeableImageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Bitmap bm = null;
        Uri selectedFileUri = data.getData();
        selectedFilePath = new File(FilePath.getPath(getContext(), selectedFileUri));
        imageFilePath = String.valueOf(selectedFilePath);
        System.out.println("sixjik++" + selectedFilePath);
        imageFile = new File(imageFilePath);


    }


    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        binding.shapeableImageView.setImageBitmap(thumbnail);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.PNG, 90, bytes);
        File selectedFilePath = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".png");


        FileOutputStream fo;
        try {
            selectedFilePath.createNewFile();
            fo = new FileOutputStream(selectedFilePath);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageFilePath= selectedFilePath.getAbsolutePath();
        System.out.println("Selectedfromcamera==="+imageFilePath);

        imageFile= new File(imageFilePath);


    }
}