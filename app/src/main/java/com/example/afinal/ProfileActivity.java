package com.example.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class ProfileActivity extends AppCompatActivity {

    private ImageView cameraButton;
    private ImageView profileImage;
    private RecyclerView rv_item;
    private ItemAdapter adapter;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final String PREFS = "profile_prefs";
    private static final String KEY_PROFILE_URI = "profile_image_uri";
    private Uri currentImageUri;

    private final ActivityResultLauncher<Uri> takePictureLauncher =
            registerForActivityResult(new ActivityResultContracts.TakePicture(), success -> {
                if (success && currentImageUri != null) {
                    profileImage.setImageURI(currentImageUri);
                    saveImageUri(currentImageUri);
                } else {
                    Toast.makeText(this, "Capture cancelled", Toast.LENGTH_SHORT).show();
                }
            });

    private final ActivityResultLauncher<PickVisualMediaRequest> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                if (uri != null) {
                    profileImage.setImageURI(uri);
                    saveImageUri(uri);
                } else {
                    Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profile_activity);

        TextView tv_fullname = findViewById(R.id.tv_fullname);
        profileImage = findViewById(R.id.profile_image);
        cameraButton = findViewById(R.id.camera_ic);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            tv_fullname.setText(account.getDisplayName());
        }

        rv_item = findViewById(R.id.AllItemView);
        rv_item.setHasFixedSize(false);
        rv_item.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ItemAdapter();
        rv_item.setAdapter(adapter);

        cameraButton.setOnClickListener(v -> showPickDialog());

        loadSavedImageUriIfAny();
    }

    private void showPickDialog() {
        String[] options = {"Take photo", "Choose from gallery"};
        new AlertDialog.Builder(this)
                .setTitle("Profile picture")
                .setItems(options, (d, which) -> {
                    if (which == 0) {
                        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                                == PackageManager.PERMISSION_GRANTED) {
                            captureImage();
                        } else {
                            ActivityCompat.requestPermissions(
                                    this,
                                    new String[]{android.Manifest.permission.CAMERA},
                                    CAMERA_PERMISSION_CODE
                            );
                        }
                    } else {
                        pickImageLauncher.launch(
                                new PickVisualMediaRequest.Builder()
                                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                                        .build()
                        );
                    }
                })
                .show();
    }

    private void captureImage() {
        Uri imageUri = createImageUri();
        if (imageUri != null) {
            currentImageUri = imageUri;
            takePictureLauncher.launch(imageUri);
        } else {
            Toast.makeText(this, "Unable to open camera", Toast.LENGTH_SHORT).show();
        }
    }

    private Uri createImageUri() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    private void saveImageUri(Uri uri) {
        getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_PROFILE_URI, uri.toString())
                .apply();
    }

    private void loadSavedImageUriIfAny() {
        SharedPreferences sp = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String s = sp.getString(KEY_PROFILE_URI, null);
        if (s != null) {
            try {
                Uri uri = Uri.parse(s);
                profileImage.setImageURI(uri);
            } catch (Exception ignored) { }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                captureImage();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}