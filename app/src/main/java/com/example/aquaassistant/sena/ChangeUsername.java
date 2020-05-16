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

public class ChangeUsername extends AppCompatActivity {

    EditText editTextUsername;
    Button saveUsername;
    String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);
        Slidr.attach(this);

        editTextUsername = findViewById(R.id.editTextUsername);
        saveUsername = findViewById(R.id.saveUsername);
    }

    //this method updates the username
    public void saveNewUsername(View view) {
        username = editTextUsername.getText().toString();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest usernameRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build();

        user.updateProfile(usernameRequest)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChangeUsername.this, "New username saved!", Toast.LENGTH_LONG ).show();
                        } else {
                            Toast.makeText(ChangeUsername.this, "Username change failed. Try signing in again before making this change.", Toast.LENGTH_LONG ).show();
                        }
                    }
                });
    }
}
