package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientHome extends AppCompatActivity {

    private Button btCal,btnphar;
    private Button btLab;
    private Button btDoc;
    private Button btprofile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

         btLab= (Button)findViewById(R.id.btnLab);
         btDoc=findViewById(R.id.btnDoc);
         btprofile=findViewById(R.id.btnprofile);
         btnphar=findViewById(R.id.pharmacy);


        btnphar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent =new Intent(getApplicationContext(),Pharmacy_List.class);
                 startActivity(intent);

             }
         });

        btprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),customerHome.class);
                startActivity(intent);

            }
        });


        btDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),Doctor_List.class);
                startActivity(intent);

            }
        });

        btLab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 openLab();
             }
         });

        btCal = (Button)findViewById(R.id.btnCal);
        btCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHealCal();
            }
        });
    }
    public void openHealCal(){
        Intent intent = new Intent(this, HealthCal.class);
        startActivity(intent);

    }

    public void openLab(){
        Intent intent = new Intent(this, LabTestList.class);
        startActivity(intent);

    }
}