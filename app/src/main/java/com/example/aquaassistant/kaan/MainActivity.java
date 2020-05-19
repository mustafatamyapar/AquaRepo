package com.example.aquaassistant.kaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
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
import com.r0adkll.slidr.Slidr;



import org.w3c.dom.Text;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    Button sign_in;
    Button sign_up;
    EditText user_name;
    EditText user_password;
    String email;
    String password;
    TextView link;
    private long backPressedTime;
    private Toast backToast;



    public static FirebaseAuth firebaseauth;
    public static FirebaseUser firebaseuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Slidr.attach(this);
        setContentView(R.layout.activity_main);


        FirebaseApp.initializeApp(MainActivity.this);
        sign_in = findViewById(R.id.sign_in);
        sign_up = findViewById(R.id.sign_up);
        user_name = findViewById(R.id.user_name);
        user_password = findViewById(R.id.user_password);
        firebaseauth = FirebaseAuth.getInstance();
        firebaseuser = firebaseauth.getCurrentUser();
        user_name.addTextChangedListener(buttonTextWatcher);
        user_password.addTextChangedListener(buttonTextWatcher);
        link = findViewById(R.id.link);
        link.setPaintFlags(link.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);
        if (firebaseuser != null) {
            Intent intent = new Intent(MainActivity.this, MainPage.class);
            startActivity(intent);
            finish();
        }

        password = user_password.getText().toString();
        email = user_name.getText().toString();


    }
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }


    private TextWatcher buttonTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = user_name.getText().toString().trim();
            String passwordInput = user_password.getText().toString().trim();

            sign_in.setEnabled(!usernameInput.isEmpty()&& !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    public void openActivity2(View view) {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    public void signIn(View view) {
        String email = user_name.getText().toString();
        String password = user_password.getText().toString();
        firebaseauth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent = new Intent(MainActivity.this, MainPage.class);
                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(MainActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
    public void goSignUp(View view){
        Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent1);
    }

}



