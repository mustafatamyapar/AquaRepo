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
import com.r0adkll.slidr.Slidr;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ChangePassword extends AppCompatActivity {

    TextView textView2;
    EditText editText;
    Button savePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Slidr.attach(this);

        textView2 = findViewById(R.id.textView2);
        editText = findViewById(R.id.editText);
        savePassword = findViewById(R.id.savePassword);
    }

    //the newly entered password will be saved as the new password when the button is clicked
    public void saveNewPassword(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            user.updatePassword(editText.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangePassword.this, "New password saved!", Toast.LENGTH_LONG ).show();
                            } else {
                                Toast.makeText(ChangePassword.this, "Password change failed. Try signing in again before making this change.", Toast.LENGTH_LONG ).show();
                            }

                        }
                    });
        }
    }
}
