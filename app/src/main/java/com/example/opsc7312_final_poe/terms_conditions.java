package com.example.opsc7312_final_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class terms_conditions extends AppCompatActivity {

    private Button proceed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        //Sets the visibility of the proceed button depending on if the user agrees to the terms and conditions
        CheckBox checkbox = findViewById(R.id.checkbox_agree);
        checkbox.setOnClickListener(v -> {
            if (((CheckBox) v).isChecked()) {
                proceed.setVisibility(View.VISIBLE);
            } else {
                proceed.setVisibility(View.INVISIBLE);
            }
        });
//Back to home page
        Button back = findViewById(R.id.btn_Backbutton);
        back.setOnClickListener(view -> openhome_page());
//Proceeds to the map
        proceed = findViewById(R.id.btn_Proceed);
        proceed.setOnClickListener(view -> openMapsActivity());


    }

    public void openhome_page() {
        Intent intent = new Intent(this, home_page.class);
        startActivity(intent);
    }

    public void openMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}