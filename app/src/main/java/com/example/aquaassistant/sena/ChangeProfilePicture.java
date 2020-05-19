package com.example.aquaassistant.sena;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aquaassistant.R;
import com.example.aquaassistant.kerem.MainPage;
import com.example.aquaassistant.kerem.ProfilePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.r0adkll.slidr.Slidr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import es.dmoral.toasty.Toasty;

public class ChangeProfilePicture extends AppCompatActivity {
    Bitmap chosenImageBitmap;
    Uri chosenImageUri;
    ImageView imageView;
    Button choosePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile_picture);
        imageView = findViewById(R.id.imageView);
        choosePicture = findViewById(R.id.choosePicture);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.getPhotoUrl() != null) {
            String uid = user.getUid();
            StorageReference ref = FirebaseStorage.getInstance().getReference()
                    .child("profilePictures/" + uid + ".jpeg");
            ref.getDownloadUrl()
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Glide.with(getApplicationContext()).load(task.getResult()).into(imageView);
                            }
                        }
                    });
        } else {
            imageView.setImageResource(R.drawable.profilepicture);
        }

        Slidr.attach(this);
    }

    public void chooseNewPicture(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            chosenImageUri = data.getData();
            try {
                if (Build.VERSION.SDK_INT >= 28) {
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), chosenImageUri);
                    chosenImageBitmap = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(chosenImageBitmap);
                } else {
                    chosenImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), chosenImageUri);
                    imageView.setImageBitmap(chosenImageBitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            StorageReference reference = FirebaseStorage.getInstance().getReference()
                    .child("profilePictures")
                    .child( userId + ".jpeg");
            reference.putFile(chosenImageUri);
            UserProfileChangeRequest profilePictureChangeRequest = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(chosenImageUri)
                    .build();

            user.updateProfile(profilePictureChangeRequest)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toasty.success(ChangeProfilePicture.this, "New profile picture saved!", Toast.LENGTH_LONG).show();
                            } else {
                                Toasty.error(ChangeProfilePicture.this, "Profile picture change failed. Try signing in again before making this change.", Toast.LENGTH_LONG ).show();
                            }
                        }
                    });

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
