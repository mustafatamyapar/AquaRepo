package com.example.aquaassistant.sena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aquaassistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.r0adkll.slidr.Slidr;

import es.dmoral.toasty.Toasty;

/**
 * ChangeEmail Class - the activity to change user e-mail
 * @author Fatma Sena Genç
 * @version 1.0 (April 30, 2020)
 * @version 2.0 (
 */

public class ChangeEmail extends AppCompatActivity {

    EditText editText2;
    EditText oldEmail;
    EditText password;
    Button saveEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        Slidr.attach(this);

        editText2 = findViewById(R.id.editText2);
        saveEmail = findViewById(R.id.button5);
        oldEmail = findViewById(R.id.editText5);
        password = findViewById(R.id.editText6);
    }

    //user's registered e-mail adress is being updated
    public void saveNewEmail(View view) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userPassword = password.getText().toString();
        String currentEMail = oldEmail.getText().toString();
        AuthCredential credential = EmailAuthProvider.getCredential(currentEMail, userPassword);
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updateEmail(editText2.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toasty.success(ChangeEmail.this, "New e-mail saved!", Toast.LENGTH_LONG ).show();
                                            }
                                        }
                                    });

                        } else {
                            Toasty.error(ChangeEmail.this, "E-mail change failed. Reauthentication failed.", Toast.LENGTH_LONG ).show();
                        }
                    }
                });

    }
}
