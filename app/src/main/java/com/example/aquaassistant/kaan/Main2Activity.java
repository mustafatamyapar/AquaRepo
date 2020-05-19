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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import es.dmoral.toasty.Toasty;

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
                Toasty.success(Main2Activity.this, "User Created", Toast.LENGTH_SHORT).show();
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
                Toasty.error(Main2Activity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });
        }
        else {
            Toasty.warning(this, "Incorrect password!", Toast.LENGTH_SHORT).show();
        }
    }
    public void sendEmailVerification() {
        // [START send_email_verification]
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toasty.success(Main2Activity.this, "E-mail verification link sent!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        // [END send_email_verification]
    }

}

