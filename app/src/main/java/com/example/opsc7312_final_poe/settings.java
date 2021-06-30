package com.example.opsc7312_final_poe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.assist.AssistStructure;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class settings extends AppCompatActivity {

    private Button back;
    static SwitchCompat km;
    static SwitchCompat miles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        km = findViewById(R.id.km);
        miles = findViewById(R.id.miles);

        //Save Switch settings
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        km.setChecked(sharedPreferences.getBoolean("value", true));
        miles.setChecked(sharedPreferences.getBoolean("value", true));

        km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(km.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    km.setChecked(true);
                }else{
                    //when switch is unchecked
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    km.setChecked(false);
                }
            }
        });

        miles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(miles.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    miles.setChecked(true);
                }else{
                    //when switch is unchecked
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    miles.setChecked(false);
                }
            }
        });





        back = (Button) findViewById(R.id.btn_Backbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhome_page(); }
        });
    }

    public void openhome_page() {
        Intent intent = new Intent(this, home_page.class);
        startActivity(intent);
    }
}