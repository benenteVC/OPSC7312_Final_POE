package com.example.opsc7312_final_poe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class distance_calc extends AppCompatActivity {

    //INITIALIZE VARIABLES
    private Button back;
    EditText etSource, etDestination;
    TextView textView;
    String sType;
    double lat1 = 0, long1 = 0, lat2 = 0, long2 = 0;
    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_calc);

        // ASSIGN VARIABLES

        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        textView = findViewById(R.id.text_view);


        back = (Button) findViewById(R.id.btn_Backbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhome_page(); }
        });
        // INITIALIZE PLACES

        Places.initialize(getApplicationContext(), " AIzaSyCYd9DNtP8fAnic_H5XwgCef7dmqj_7vB0");

        etSource.setFocusable(false);
        etSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // define type
                sType = "source";
                // place field list
                List<Place.Field> fields = Arrays.asList(Place.Field.ADDRESS,
                        Place.Field.LAT_LNG);

                //CREATE INTENT
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields
                ).build(distance_calc.this);

                //ACT RESULTS
                startActivityForResult(intent, 100);
            }
        });

        // SET EDIT TEXT NON FOCUSABLE
        etDestination.setFocusable(false);
        etDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DEFINE TYPE
                sType = "destination";
                //PLACE FIELD TEXT
                List<Place.Field> fields = Arrays.asList(Place.Field.ADDRESS,
                        Place.Field.LAT_LNG);
                // CREATE INTENT
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY, fields).build(distance_calc.this);

                //ACT RESULTS
                startActivityForResult(intent, 100);
            }
        });

        //SET TEXT ON TEXT VIEW
        textView.setText("0.0 Km");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // CHECK CONDITION
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Place place = Autocomplete.getPlaceFromIntent(data);

            if (sType.equals("source")) {
                flag++;

                etSource.setText(place.getAddress());

                String sSource = String.valueOf(place.getLatLng());
                sSource = sSource.replaceAll("lat/lng", "");
                sSource = sSource.replace("(", "");
                sSource = sSource.replace(")", "");
                String[] spilt = sSource.split(",");
                lat1 = Double.parseDouble(spilt[0]);
                long1 = Double.parseDouble(spilt[1]);
            } else {
                flag++;

                etDestination.setText(place.getAddress());

                String sDestination = String.valueOf(place.getLatLng());
                sDestination = sDestination.replaceAll("lat/lng", "");
                sDestination = sDestination.replace("(", "");
                sDestination = sDestination.replace(")", "");
                String[] spilt = sDestination.split(",");
                lat2 = Double.parseDouble(spilt[0]);
                long2 = Double.parseDouble(spilt[1]);
            }

            if (flag >= 2) {

                distance(lat1, long1, lat2, long2);
            }
        } else if (requestCode == AutocompleteActivity.RESULT_ERROR) {

            Status status = Autocomplete.getStatusFromIntent(data);

            Toast.makeText(getApplicationContext(), status.getStatusMessage()
                    , Toast.LENGTH_SHORT).show();

        }
    }

    private  void distance( double lat1, double long1 , double lat2, double long2) {

        double longDiff = long1 - long2;

        double distance = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(longDiff));
        distance = Math.acos(distance);

        distance = rag2deg(distance);


        //Checks where the Settings switches have been checked and then sets the multipliers to chance values into km or miles
        //MILES
        if (settings.miles.isChecked() == true) {
            distance = distance * 60 * 1.1515;
        }else{
            distance = distance * 1.609344;
        }
        //KM
        if (settings.km.isChecked() == true){
            distance = distance * 1.609344;
        }else {
            distance = distance * 60 * 1.1515;
        }

        textView.setText(String.format(Locale.UK, "%2f Km", distance));

    }

    private double rag2deg(double distance) {
        return (distance * 180.0 / Math.PI);
    }

    private double deg2rad(double lat1) {
        return lat1;
    }


    public void openhome_page() {
        Intent intent = new Intent(this, home_page.class);
        startActivity(intent);
    }
}