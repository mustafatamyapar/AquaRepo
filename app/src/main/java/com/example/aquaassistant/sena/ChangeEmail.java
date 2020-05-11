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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.r0adkll.slidr.Slidr;

public class ChangeEmail extends AppCompatActivity {

    EditText editText2;
    Button saveEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        Slidr.attach(this);

        editText2 = findViewById(R.id.editText2);
        saveEmail = findViewById(R.id.saveEmail);
    }

    //user's registered e-mail adress is being updated
    public void saveNewEmail(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            user.updateEmail(editText2.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangeEmail.this, "New e-mail saved!", Toast.LENGTH_LONG ).show();
                            } else {
                                Toast.makeText(ChangeEmail.this, "E-mail change failed. Try signing in again before making this change.", Toast.LENGTH_LONG ).show();
                            }

                        }
                    });
        }
    }
}
