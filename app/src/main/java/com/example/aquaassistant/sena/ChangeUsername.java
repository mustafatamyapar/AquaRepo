package com.example.aquaassistant.sena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aquaassistant.R;
import com.example.aquaassistant.kerem.ProfilePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.r0adkll.slidr.Slidr;

import es.dmoral.toasty.Toasty;

/**
 * ChangeUsername Class - the activity to change username
 * @author Fatma Sena Genç
 * @version 1.0 (May 16, 2020)
 */

public class ChangeUsername extends AppCompatActivity {

    EditText editTextUsername;
    Button saveUsername;
    String username = null;

    @Override
    /**
     * onCreate - called when the activity is started
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);
        Slidr.attach(this);

        editTextUsername = findViewById(R.id.editTextUsername);
        saveUsername = findViewById(R.id.saveUsername);
    }

    /**
     * saveNewUsername - updates username
     * @param view
     */
    public void saveNewUsername(View view) {
        username = editTextUsername.getText().toString();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest usernameRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build();
        //requests a change in user profile
        user.updateProfile(usernameRequest)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toasty.success(ChangeUsername.this, "New username saved!", Toast.LENGTH_LONG).show();
                        } else {
                            Toasty.error(ChangeUsername.this, "Username change failed. Try signing in again before making this change.", Toast.LENGTH_LONG ).show();
                        }
                    }
                });
    }
}
