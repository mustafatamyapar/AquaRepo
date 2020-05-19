package com.example.aquaassistant.sena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aquaassistant.R;
import com.example.aquaassistant.kaan.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.r0adkll.slidr.Slidr;

import es.dmoral.toasty.Toasty;

public class DeleteAccount extends AppCompatActivity {
    Button deleteButton;
    EditText getEmail;
    EditText getPassword;
    TextView textView11;
    TextView textView12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        Slidr.attach(this);

        deleteButton = findViewById(R.id.button7);
        getEmail = findViewById(R.id.editText7);
        getPassword = findViewById(R.id.editText9);
        textView11 = findViewById(R.id.textView11);
        textView12 = findViewById(R.id.textView12);
    }

    public void deleteAccount(View view) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();
        String userPassword = getPassword.getText().toString();
        String userEMail = getEmail.getText().toString();
        AuthCredential credential = EmailAuthProvider.getCredential(userEMail, userPassword);
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            AlertDialog.Builder alert = new AlertDialog.Builder(DeleteAccount.this);
                            alert.setTitle("Delete Account");
                            alert.setMessage("Are you sure?");
                            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(final DialogInterface dialog, int which) {
                                            user.delete()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Toasty.success(getApplicationContext(), "Account deleted.", Toast.LENGTH_LONG).show();
                                                                Intent intentToMain = new Intent(DeleteAccount.this, MainActivity.class);
                                                                startActivity(intentToMain);
                                                                StorageReference ref = FirebaseStorage.getInstance().getReference()
                                                                        .child("profilePictures/" + uid + ".jpeg");
                                                                ref.delete()
                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                if (task.isSuccessful()) {
                                                                                    Toasty.success(getApplicationContext(), "User data deleted.", Toast.LENGTH_LONG).show();
                                                                                }
                                                                            }
                                                                        });
                                                            } else {
                                                                dialog.cancel();
                                                                Toasty.error(getApplicationContext(), "An error eccurred.", Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                        }
                                    }
                            );
                            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            alert.show();
                        } else {
                            Toasty.error(getApplicationContext(), "Account cannot be deleted. Authentication failed.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
