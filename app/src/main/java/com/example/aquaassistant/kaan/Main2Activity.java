package com.example.aquaassistant.kaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aquaassistant.R;
import com.example.aquaassistant.kerem.MainPage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Main2Activity extends AppCompatActivity {
    EditText signUpEmail;
    EditText signUpPass;
    EditText signUpConf;
    String password;
    String email;

    public static FirebaseAuth firebaseauth;
    public static FirebaseUser firebaseuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        signUpEmail = findViewById(R.id.signUpEmail);
        signUpPass = findViewById(R.id.signUpPassword);
        signUpConf = findViewById(R.id.signUpConf);
        FirebaseApp.initializeApp(Main2Activity.this);
        firebaseauth = FirebaseAuth.getInstance();

        if (firebaseuser != null) {

                Intent intent = new Intent(Main2Activity.this,MainPage.class);
            startActivity(intent);
            finish();

        }
        }

    public void signUp(View view) {
        if (signUpPass.getText().toString().matches(signUpConf.getText().toString())) {
        password = signUpPass.getText().toString();
        email = signUpEmail.getText().toString();
        firebaseauth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Main2Activity.this,"User Created",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Main2Activity.this, MainPage.class);
                startActivity(intent);
                finish();
                firebaseuser = firebaseauth.getCurrentUser();
                String userId = firebaseauth.getUid();
                Uri uri = Uri.parse("0,Beginner");
                StorageReference reference = FirebaseStorage.getInstance().getReference()
                        .child("userInfo")
                        .child( userId + ".txt");
                reference.putFile(uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Main2Activity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });
        }
        else {
            Toast.makeText(Main2Activity.this, "Incorrect Password", Toast.LENGTH_LONG).show();
        }
    }

}

