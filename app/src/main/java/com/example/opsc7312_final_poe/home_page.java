package com.example.opsc7312_final_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home_page extends AppCompatActivity {
    private Button settings, fuelcalc, maps, distance, Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        maps =  findViewById(R.id.btn_maps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openterms_conditions();
            }
        });

        fuelcalc =  findViewById(R.id.btn_Fuelcalc);
        fuelcalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openfuelcalc();
            }
        });

        settings =  findViewById(R.id.btn_Settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensettings();
            }
        });

        distance=  findViewById(R.id.btn_DistanceCalc);
        distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDistanceCalc(); }
        });

        Logout=  findViewById(R.id.btn_Logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openlogin(); }
        });


    }

    public void opensettings() {
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
    }
    public void openDistanceCalc() {
        Intent intent = new Intent(this, distance_calc.class);
        startActivity(intent);
    }


    public void openfuelcalc() {
        Intent intent = new Intent(this, fuelcalc.class);
        startActivity(intent);
    }
    public void openterms_conditions() {
        Intent intent = new Intent(this, terms_conditions.class);
        startActivity(intent);
    }
    public void openlogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}