package com.example.aquaassistant.sena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aquaassistant.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.r0adkll.slidr.Slidr;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

/**
 * ChangePassword Class - the activity to change password
 * @author Fatma Sena Genç
 * @version 1.0 (May 12, 2020)
 * @version 2.0 (May 16, 2020) - reauthentication added
 * @version 3.0 (May 17, 2020) - completed, reauthentication working fine
 */

public class ChangePassword extends AppCompatActivity {

    TextView textView2;
    EditText userEmail;
    EditText newPassword;
    EditText oldPassword;
    Button savePassword;

    @Override
    /**
     * onCreate - called when the activity is started
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Slidr.attach(this);

        textView2 = findViewById(R.id.textView2);
        newPassword = findViewById(R.id.editText);
        savePassword = findViewById(R.id.savePassword);
        userEmail = findViewById(R.id.editText3);
        oldPassword = findViewById(R.id.editText4);
    }

    /**
     * saveNewPassword - updates user's password
     * @param view
     */
    public void saveNewPassword(View view) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentPassword = oldPassword.getText().toString();
        String eMail = userEmail.getText().toString();
        AuthCredential credential = EmailAuthProvider.getCredential(eMail, currentPassword);
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(newPassword.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toasty.success(ChangePassword.this, "New password saved!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        } else {
                            Toasty.error(ChangePassword.this, "Password change failed. Reauthentication failed.", Toast.LENGTH_LONG ).show();
                        }
                    }
                });

    }
}
