package com.example.opsc7312_final_poe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class fuelcalc extends AppCompatActivity {

    private Button back;
    private  Button  calculate;
    AlertDialog.Builder builder;
    EditText consumption, disancetravelled ,numppl,priceltr;
    TextView Rvalue;
    int TotalValue;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuelcalc);

        builder = new AlertDialog.Builder(this);
        calculate = (Button) findViewById(R.id.btn_calculate);

        back = (Button) findViewById(R.id.btn_Backbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openhome_page(); }
        });


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);


                priceltr=findViewById(R.id.priceltr);
                String temp=priceltr.getText().toString();
                int priceValue=0;
                if (!"".equals(temp)){
                    priceValue=Integer.parseInt(temp);
                }
                disancetravelled=findViewById(R.id.disancetravelled);
                String temp1=disancetravelled.getText().toString();
                int distanceValue=0;
                if (!"".equals(temp1)){
                    distanceValue=Integer.parseInt(temp1);
                }
                numppl=findViewById(R.id.numppl);
                String temp2=numppl.getText().toString();
                int peopleValue=0;
                if (!"".equals(temp2)){
                    peopleValue=Integer.parseInt(temp2);
                }
                consumption=findViewById(R.id.consumption);
                String temp3=consumption.getText().toString();
                int consumptionValue=0;
                if (!"".equals(temp3)){
                    consumptionValue=Integer.parseInt(temp3);
                }

               TotalValue = ((((distanceValue/100)*consumptionValue)*priceValue)/peopleValue);




               value = String.valueOf(TotalValue);
                Rvalue = findViewById(R.id.Rvalue);
                Rvalue.setVisibility(View.VISIBLE);
                Rvalue.setText("R" + value);



            }
        });
    }










    public void openhome_page() {
        Intent intent = new Intent(this, home_page.class);
        startActivity(intent);
    }
}
/*
 */