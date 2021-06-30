package com.example.opsc7312_final_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class login extends AppCompatActivity {

    Button register,login;
    ProgressBar progressBar;
    EditText edit_emailaddress,edit_Password;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_emailaddress = findViewById(R.id.edit_emailaddress);
        edit_Password = findViewById(R.id.edit_Password);

        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();


        register =  findViewById(R.id.btn_Regbutton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), registration.class));
            }
        });


            login = findViewById(R.id.btn_Logbutton);
        login.setOnClickListener(view -> {


            String email = edit_emailaddress.getText().toString().trim();
            String password =edit_Password.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                edit_emailaddress.setError("Email is required");
                return;
            }

            if(TextUtils.isEmpty(password)){
                edit_Password.setError("Password is required");
                return;
            }

            if(password.length() < 6){
                edit_Password.setError("Password must contain 6 or more characters");
                return;

            }

            progressBar.setVisibility(View.VISIBLE);

            // AUTHENTICATE THE USER

            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(login.this,"User Logged in Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),home_page.class));
                } else
                {
                    Toast.makeText(login.this,"ERROR! - Email or Password incorrect.", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        });

    }
}