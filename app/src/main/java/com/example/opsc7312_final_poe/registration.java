package com.example.opsc7312_final_poe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registration extends AppCompatActivity {
    private Button back;
    public static final String TAG = "TAG";
    EditText edt_firstname,edt_surname, edt_emailaddress, edt_password;
    Button reg_button;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore FireStore;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        progressBar = findViewById(R.id.progressBar);
        edt_firstname  = findViewById(R.id.edt_firstname);
        edt_surname    = findViewById(R.id.edt_surname);
        edt_emailaddress = findViewById(R.id.edt_emailaddress);
        edt_password = findViewById(R.id.edt_password);
        reg_button = findViewById(R.id.btn_Regbutton);

        //Firebase Connection
        fAuth = FirebaseAuth.getInstance();
        FireStore = FirebaseFirestore.getInstance();


        back =findViewById(R.id.btn_Backbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin(); }
        });

// RETURNING USERS
        if(fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),login.class));
            finish();



        }

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_emailaddress.getText().toString().trim();
                String password =edt_password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    edt_emailaddress.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    edt_password.setError("Password is required");
                    return;
                }

                if(password.length() < 6){
                    edt_password.setError("Password must contain 6 or more characters");
                    return;

                }

                progressBar.setVisibility(View.VISIBLE);

                //REGISTER USER IN FIREBASE

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            userId = fAuth.getCurrentUser().getUid();
                            DocumentReference docRef = FireStore.collection("users").document(userId);
                            Map<String, Object> user = new HashMap<>();
                            user.put("first", edt_firstname);
                            user.put("surname", edt_surname);
                            user.put("email", edt_emailaddress);

                            docRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "OnSuccess: user profile is created for " + edt_firstname + " " + edt_surname);
                                    Toast.makeText(registration.this, "User Created", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), login.class));
                                }
                            });

                        } else{
                            Toast.makeText(registration.this,"ERROR!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
            }
        });
    }


    public void openlogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}